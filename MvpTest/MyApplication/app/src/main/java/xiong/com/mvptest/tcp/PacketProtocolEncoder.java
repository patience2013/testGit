package xiong.com.mvptest.tcp;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;

public class PacketProtocolEncoder extends ProtocolEncoderAdapter {
	private static final Charset charset = Charset.forName("UTF-8");

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
			throws Exception {
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
		String strOut = (String)message;
		buf.putInt(strOut.getBytes(charset).length);
		buf.putString(strOut, charset.newEncoder());
		buf.flip();
		out.write(buf);
	}

}
