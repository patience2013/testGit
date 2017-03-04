package xiong.com.mvptest.tcp;

import com.google.gson.Gson;

public interface TCPPacketListener {

	void onPacketSent(Packet packet, Gson parser);

	void onPacketRecviced(Packet packet, Gson parser);
}
