package xiong.com.mvptest.tcp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import xiong.com.mvptest.packet.RoomStateSerializer;
import xiong.com.mvptest.packet.TCPStruct;

public abstract class BasicPacket {
	public static final Gson gson = new GsonBuilder().registerTypeAdapter(
			TCPStruct.RoomState.class, new RoomStateSerializer()).create();
	

	/**
	 * 创建发往服务器的包。 from to都为空
	 *  
	 * @return
	 */
	public Packet createPacket() {
		Packet packet = new Packet();
		packet.setFrom("");
		packet.setTo("");
		packet.setContent(gson.toJson(this));
		packet.setCommand(command());
		return packet;
	}
	
	public Packet createPacket(String to) {
		Packet packet = new Packet();
		packet.setFrom("");
		packet.setTo(to);
		packet.setContent(gson.toJson(this));
		packet.setCommand(command());
		return packet;
	}

	abstract public int command();
}
