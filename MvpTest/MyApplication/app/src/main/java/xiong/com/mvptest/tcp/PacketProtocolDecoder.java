package xiong.com.mvptest.tcp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;

import xiong.com.mvptest.packet.RoomStateSerializer;
import xiong.com.mvptest.packet.TCPStruct;

public class PacketProtocolDecoder extends CumulativeProtocolDecoder {
	private static final Charset charset = Charset.forName("UTF-8");
	private static final Gson s_gson = new GsonBuilder().registerTypeAdapter(
			TCPStruct.RoomState.class, new RoomStateSerializer()).create();

	@Override
	protected boolean doDecode(IoSession session, IoBuffer ioBuffer,
			ProtocolDecoderOutput out) throws Exception {
		if (ioBuffer.remaining() < 4)// 这里很关键，网上很多代码都没有这句，是用来当拆包时候剩余长度小于4的时候的保护，不加就出错咯
		{
			return false;
		}
		if (ioBuffer.remaining() > 1) {
			ioBuffer.mark();// 标记当前位置，以便reset
			int length = ioBuffer.getInt(ioBuffer.position());
			if (length > ioBuffer.remaining() - 4) {// 如果消息内容不够，则重置，相当于不读取size
				System.out.println("package no enough " + ioBuffer.remaining() + "/" + length + "byte");
				ioBuffer.reset();
				return false;// 接收新数据，以拼凑成完整数据
			} else {
				ioBuffer.getInt();
				byte[] bytes = new byte[length];
				ioBuffer.get(bytes, 0, length);
				String str = new String(bytes, charset);
				if (null != str && str.length() > 0) {
					out.write(str);
				} else {
					return false;
				}
				return true;// 这里有两种情况1：没数据了，那么就结束当前调用，有数据就再次调用
			}
		}
		return false;// 处理成功，让父类进行接收下个包
	}

}
