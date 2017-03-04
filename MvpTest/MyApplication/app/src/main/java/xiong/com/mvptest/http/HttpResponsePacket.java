package xiong.com.mvptest.http;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import xiong.com.mvptest.util.ToastUtil;

public class HttpResponsePacket {
	private static final Gson gson = new Gson();
	public int code;
	public String message;
	public String data;

	public <T> T getData(Type type) {
		try {
			if (code != 0) {
				ToastUtil.showToast(message);
				return null;
			}
			return gson.fromJson(data, type);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
}
