package xiong.com.mvptest.tcp;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class PacketProtocol implements ProtocolCodecFactory{
	
	private final PacketProtocolDecoder decoder;
	private final PacketProtocolEncoder encoder;
	public PacketProtocol() {
		this.decoder = new PacketProtocolDecoder();
		this.encoder = new PacketProtocolEncoder();
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return this.decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return this.encoder;
	}

}
