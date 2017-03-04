package xiong.com.mvptest.packet;


import xiong.com.mvptest.tcp.BasicPacket;

//发送请求的 数据包
public class TCPRequest {

	/******************************************************
	 * 认证包
	 ******************************************************/
	public static class REQAuthPacket extends BasicPacket {
		public static final int REQ_AUTH = 1;

		// public String account; // 用户
		// public String password; // 密码

		public String uid;

		@Override
		public int command() {
			return REQ_AUTH;
		}
	}

	/******************************************************
	 * 房间包
	 ******************************************************/

	// 进入房间
	public static class REQEnterRoomPacket extends BasicPacket {
		public static final int REQ_ENTER_ROOM = 100;
		public int roomId;

		@Override
		public int command() {
			return REQ_ENTER_ROOM;
		}
	}

	// 退出房间
	public static class REQExitRoomPacket extends BasicPacket {
		public static final int REQ_EXIT_ROOM = 101;
		public int roomId;

		@Override
		public int command() {
			return REQ_EXIT_ROOM;
		}
	}

	// 聊天消息 即作为发送包 也作为聊天返回包。
	public static class RoomChatPacket extends BasicPacket {
		public static final int ROOM_CHAT = 102;
		public String message; // 聊天信息 如果type＝2，内容就是价格 double 型的字符串
		public int roomId; // 房间id
		public String userId; // 用户id
		public int isVip;// 是否是vip   
		public String uname; // 号牌
		public String head; // 用户头像
		public long sendTime; // 发送时间 客户端可不填， 服务器在收到消息后重置为服务器时间
		public int index; // 聊天记录索引值
		public int isBid; // 是否是出价的聊天记录
		public String nickname;
		public int roomstate;
		@Override
		public int command() {
			return ROOM_CHAT;
		}
	}

	// 出价请求包
	public static class REQBidPacket extends BasicPacket {
		public static final int BID = 103;
		public int rid; // 房间id
		public int gid; // 拍品id
		public double price; // 出价价格
		@Override
		public int command() {
			return BID;
		}
	}
	// 获取房间信息请求包
	public static class REQRoomInfoPacket extends BasicPacket {
		public static final int ROOM_INFO = 104;
		public int rid;

		@Override
		public int command() {
			return ROOM_INFO;
		}
	}

	 //请求获取房间历史记录
	public static class REQRoomHistoryPacket extends BasicPacket {
		public static final int ROOM_HISTORY = 105;
		public int rid;
		public int start; // 当前最后一条索引
		public int num; // 往上获取几条

		@Override
		public int command() {
			return ROOM_HISTORY;
		}
	}
}
