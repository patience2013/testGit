package xiong.com.mvptest.tcp;

import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

import xiong.com.mvptest.packet.RoomStateSerializer;
import xiong.com.mvptest.packet.TCPStruct;
import xiong.com.mvptest.util.UIHandlerUtil;

/**
 * tcp 网络模块
 * 
 * @author 卢明渊
 * 
 */
public class TCPNetwork {
	private static TCPNetwork instance;

	public static TCPNetwork getInstance() {
		return instance;
	}

	private static final Gson gson = new GsonBuilder().registerTypeAdapter(
			TCPStruct.RoomState.class, new RoomStateSerializer()).create();

	private int port;
	private String host;

	private SocketConnector connector;
	private IoSession session;
	private static final String CONN_LISTENERS = "Connection Listners";
	private Set<TCPConnectionListener> mConnectionListeners = new HashSet<TCPConnectionListener>();
	private Set<TCPPacketListener> mPacketListeners = new HashSet<TCPPacketListener>();
	private boolean activeClosed = false;
	private boolean isReconnecting = false;
	private Thread reconnectThread = null;
	private TCPWriter writer = null;
	private boolean isDisconnect = false;

	public TCPNetwork(String host, int port) {
		this.host = host;
		this.port = port;
		instance = this;
		writer = new TCPWriter();
	}

	// 是否已经连接过
	public boolean isConnected() {
		if (session != null && session.isConnected()) {
			return true;
		}
		return false;
	}

	public boolean connect() {
		try {
			// 创建一个socket连接
			connector = new NioSocketConnector();
			// 设置链接超时时间
			connector.setConnectTimeoutMillis(3000);
			connector.getSessionConfig().setWriterIdleTime(15);
			// 获取过滤器链
			DefaultIoFilterChainBuilder filterChain = connector
					.getFilterChain();

			// 添加编码过滤器 处理乱码、编码问题
			filterChain.addLast("codec", new ProtocolCodecFilter(
					new PacketProtocol()));

			// 心跳包filter
			KeepAliveFilter aliveFilter = new KeepAliveFilter(
					new KeepAliveMessageFactoryImpl(), IdleStatus.WRITER_IDLE);
			aliveFilter.setForwardEvent(false);
			aliveFilter.setRequestInterval(50);
			aliveFilter.setRequestTimeout(30);
			aliveFilter
					.setRequestTimeoutHandler(new KeepAliveRequestTimeoutHandler() {

						@Override
						public void keepAliveRequestTimedOut(
								KeepAliveFilter arg0, IoSession arg1)
								throws Exception {
							System.out.println("心跳包请求无返回，超时了。。。");
						}
					});
			connector.getFilterChain().addLast("heartbeat", aliveFilter);
			// 消息核心处理器
			connector.setHandler(new ConnectionHandler());
			// 连接服务器，知道端口、地址
			connector
					.setDefaultRemoteAddress(new InetSocketAddress(host, port));
			connector.connect();
			isDisconnect = false;
			return true;
		} catch (Exception e) {
			Log.d("gaga", "连接失败", e);
			return false;
		}
	}

	// 重连
	public void reconnect() {
		if (isReconnecting || isDisconnect)
			return;
		fireConnectEvent(TCPConnectionListener.RECONNECTING);

		isReconnecting = true;
		reconnectThread = new Thread(new Runnable() {

			@Override
			public void run() {
				boolean fail = false;
				long start = System.currentTimeMillis();
				while (true) {
					if (reconnectThread.isInterrupted()
							|| System.currentTimeMillis() - start > 10 * 1000) {
						fail = true;
						break;
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						fail = true;
						break;
					}

					if (session != null) {
						session.close(true);
					}

					if (connector == null) {
						isReconnecting = false;
						fail = true;
						break;
					}
					try {
						ConnectFuture futrue = connector.connect();
						futrue.awaitUninterruptibly();
						session = futrue.getSession();
						if (session != null && session.isConnected()) {
							fail = false;
							break;
						} else {
							// do noting continue
						}
					} catch (Exception e) {
					}
				}
				isReconnecting = false;
				fireConnectEvent(fail ? TCPConnectionListener.RECONNECTFAILED
						: TCPConnectionListener.RECONNECTSUCCESS);
			}
		});
		reconnectThread.setDaemon(true);
		reconnectThread.start();
	}

	// 断开
	public void disconnect() {
		if (session != null && session.isConnected()) {
			session.close(true);
		}
		activeClosed = true;
		writer.close();
		isDisconnect = true;
	}

	private void fireConnectEvent(final int event) {
		UIHandlerUtil.sendEmptyMessage(3, new Callback() {

			@Override
			public boolean handleMessage(Message arg0) {
				synchronized (CONN_LISTENERS) {
					for (TCPConnectionListener listener : mConnectionListeners) {
						listener.onConnectionEvent(event);
					}
					return true;
				}
			}
		});
	}

	/**
	 * 发送包。
	 * 
	 * @param packet
	 * @return
	 */
	public boolean sendPacket(BasicPacket packet) {
		if (session != null && session.isConnected()) {
			writer.addPacket(packet.createPacket());
			return true;
		}
		reconnect();
		return false;
	}

	// 删除包
	public boolean removePacket(BasicPacket packet) {
		if (session != null && session.isConnected()) {

			writer.removePacket(packet.createPacket());
			return true;
		}
		reconnect();
		return false;
	}

	public void addConnectionListener(TCPConnectionListener listener) {
		synchronized (CONN_LISTENERS) {
			mConnectionListeners.add(listener);
		}
	}

	public void removeConnectionListener(TCPConnectionListener listener) {
		synchronized (CONN_LISTENERS) {
			mConnectionListeners.remove(listener);
		}
	}

	public void addPacketListener(TCPPacketListener listener) {
		synchronized (CONN_LISTENERS) {
			mPacketListeners.add(listener);
		}
	}

	public void removePacketListener(TCPPacketListener listener) {
		synchronized (CONN_LISTENERS) {
			mPacketListeners.remove(listener);
		}
	}

	private class ConnectionHandler extends IoHandlerAdapter {

		@Override
		public void sessionOpened(IoSession session) throws Exception {
			TCPNetwork.this.session = session;
			writer.start();
			fireConnectEvent(TCPConnectionListener.OPENED);
		}

		@Override
		public void sessionClosed(IoSession session) throws Exception {
			fireConnectEvent(TCPConnectionListener.COLSED);
			if (activeClosed) {
				session.close(true);
			} else {
				reconnect();
			}
		}

		@Override
		public void sessionCreated(IoSession session) throws Exception {
			writer.setSession(session);
			fireConnectEvent(TCPConnectionListener.CREATED);
		}

		@Override
		public void sessionIdle(IoSession session, IdleStatus status)
				throws Exception {
			session.close(true);
			writer.close();
		}

		@Override
		public void exceptionCaught(IoSession session, Throwable cause)
				throws Exception {
			cause.printStackTrace();
			if (activeClosed) {
				session.close(true);
			} else {
				reconnect();
			}
		}

		@Override
		public void inputClosed(IoSession session) throws Exception {
			if (activeClosed) {
				session.close(true);
				writer.close();
			} else {
				reconnect();
			}
		}

		@Override
		public void messageSent(IoSession session, Object message)
				throws Exception {
			UIHandlerUtil.sendMessage(0, message, packetCallback);
		}

		@Override
		public void messageReceived(IoSession session, Object message)
				throws Exception {
			UIHandlerUtil.sendMessage(1, message, packetCallback);
		}
	}

	private Callback packetCallback = new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			if (msg.what == 0) {
				// 已发送消息包
				Packet pack = gson.fromJson((String) msg.obj, Packet.class);
				synchronized (mPacketListeners) {
					for (TCPPacketListener listener : mPacketListeners) {
						listener.onPacketSent(pack, gson);
					}
				}
			} else if (msg.what == 1) {
				// 收到消息包
				Packet pack = gson.fromJson((String) msg.obj, Packet.class);
				synchronized (mPacketListeners) {
					for (TCPPacketListener listener : mPacketListeners) {
						listener.onPacketRecviced(pack, gson);
					}
				}
			}
			return false;
		}
	};
}
