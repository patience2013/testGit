package xiong.com.mvptest.util;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;

public class UIHandlerUtil {
	public static void sendEmptyMessage(int what, Callback callback) {
		Handler handler = new Handler(Looper.getMainLooper(), callback);
		handler.sendEmptyMessage(what);
	}
   
	public static void sendEmptyMessageDelayed(int what, int delay,
			Callback callback) {
		Handler handler = new Handler(Looper.getMainLooper(), callback);
		handler.sendEmptyMessageDelayed(what, delay);
	}
	
	public static void sendMessage(int what, Object obj, Callback callback) {
		Handler handler = new Handler(Looper.getMainLooper(), callback);
		Message message = handler.obtainMessage(what, obj);
		
		handler.sendMessage(message);
	}
}
