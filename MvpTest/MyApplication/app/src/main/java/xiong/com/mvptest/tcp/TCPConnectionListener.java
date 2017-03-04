package xiong.com.mvptest.tcp;

public interface TCPConnectionListener {
	public static final int CREATED = 1;
	public static final int OPENED = 2;
	public static final int COLSED = 3;
	public static final int RECONNECTING = 4;
	public static final int RECONNECTSUCCESS = 5;
	public static final int RECONNECTFAILED = 6;

	void onConnectionEvent(int event);
}
