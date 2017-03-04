package xiong.com.mvptest.tcp;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {	
	private static final String HEARTBEAT_REQUEST = "REQ";
	private static final String HEARTBEAT_RESPONSE = "RES";
	@Override
	public Object getRequest(IoSession arg0) {
		return HEARTBEAT_REQUEST;
	}

	@Override
	public Object getResponse(IoSession arg0, Object arg1) {
		return null;
	}

	@Override
	public boolean isRequest(IoSession arg0, Object arg1) {
		if (arg1.equals(HEARTBEAT_REQUEST)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isResponse(IoSession arg0, Object arg1) {
		if (arg1.equals(HEARTBEAT_RESPONSE)) {
			return true;
		}
		return false;
	}

}
