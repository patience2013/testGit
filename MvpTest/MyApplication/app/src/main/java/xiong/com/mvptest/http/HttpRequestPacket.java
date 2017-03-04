package xiong.com.mvptest.http;

import java.io.Serializable;
import java.lang.reflect.Type;

public class HttpRequestPacket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String action; // 调用方法名

	public HttpRequestPacket() {

	}

	public HttpRequestPacket(String action) {
		this.action = action;
	}

	public Type getResponseType() {
		return Object.class;
	}
}
