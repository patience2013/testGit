package xiong.com.mvptest.tcp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.mina.core.session.IoSession;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import xiong.com.mvptest.packet.RoomStateSerializer;
import xiong.com.mvptest.packet.TCPStruct;

public class TCPWriter {
	private static final Gson gson = new GsonBuilder().registerTypeAdapter(
			TCPStruct.RoomState.class, new RoomStateSerializer()).create();
	BlockingQueue<Packet> prepareSendPackets = new LinkedBlockingDeque<Packet>();
	IoSession session = null;
	WriteThread thread = new WriteThread();

	public void addPacket(Packet pack) {
		if (pack == null)
			return;
		Log.d("5", "555555");
		prepareSendPackets.add(pack);
	}

	// 删除聊天日志
	public void removePacket(Packet pack) {
		if (pack == null)
			return;
		prepareSendPackets.remove(pack);
	}

	void setSession(IoSession session) {
		this.session = session;
	}

	void start() {
		if (thread != null) {
			thread.interrupt();
		}
		thread = new WriteThread();
		thread.start();
	}

	void close() {
		if (thread != null) {
			thread.interrupt();
			thread = null;
		}
	}

	private class WriteThread extends Thread {

		@Override
		public void run() {
			while (true) {
				if (isInterrupted())
					break;

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					break;
				}

				if (session == null)
					continue;
				while (prepareSendPackets.size() > 0) {
					Packet pack = prepareSendPackets.poll();
					session.write(gson.toJson(pack));
				}
			}
		}
	}
}
