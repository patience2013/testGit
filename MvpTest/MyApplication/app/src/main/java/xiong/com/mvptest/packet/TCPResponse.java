package xiong.com.mvptest.packet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import xiong.com.mvptest.tcp.BasicPacket;

public class TCPResponse {
	public static final Gson gson = new GsonBuilder().registerTypeAdapter(
			TCPStruct.RoomState.class, new RoomStateSerializer()).create();

	public static class RESPAuthPacket extends BasicPacket {
		public static final int RESP_AUTH = 999;
		public long currentTimeMillis; // / 服务器当前时间戳

		@Override
		public int command() {
			return RESP_AUTH;
		}
	}

	// 房间错误包，客户端根据code值判断
	public static class RESPRoomError extends BasicPacket {
		public static final int RESP_ROOM_ERROR = 998;
		
		public static final int NO_PAY_BAIL = 1; // 未缴保证金
		public static final int NOT_IN_ROOM = 2; // 未在房间中
		public int code;
		public String message;

		public RESPRoomError(int code, String message) {
			this.code = code;
			this.message = message;
		}
		
		public RESPRoomError(int code) {
			this.code = code;
		}
		
		@Override
		public int command() {
			return RESP_ROOM_ERROR;
		}
	}

	// 登录房间返回包
	public static class RESPEnterRoomPacket extends BasicPacket {
		public static final int RESP_ENTER_ROOM = 1000;
		public String error;
		public long currentTimeMillis; // / 服务器当前时间戳

		@Override
		public int command() {
			return RESP_ENTER_ROOM;
		}
	}

	// 离开房间消息
	public static class RESPExitRoomPacket extends BasicPacket {
		public static final int RESP_EXIT_ROOM = 1001;
		public String error;

		@Override
		public int command() {
			return RESP_EXIT_ROOM;
		}
	}

	// 用户进入消息
	public static class RESPUserComing extends BasicPacket {
		public static final int RESP_USER_COMING = 1002;
		public String comingUser; // 进入房间的用户
		public int userCount; // 当前房间里的人数
		public int roomId; // 房间id

		@Override
		public int command() {
			return RESP_USER_COMING;
		}
	}

	// 用户离开消息
	public static class RESPUserOuting extends BasicPacket {
		public static final int RESP_USER_OUTING = 1004;
		public String outingUser; // 进入房间的用户
		public int userCount; // 当前房间里的人数
		public int roomId; // 房间id

		@Override
		public int command() {
			return RESP_USER_OUTING;
		}
	}

	// 出价反馈包
	public static class RESPBidPacket extends BasicPacket {
		public static final int RESP_BID = 1005;
		
		public static final int NOT_PAY_BAIL = 7; // 未缴纳保证金
		public int rid;
		public int gid;
		public String error; // 出价失败信息 成功时＝null或空
		public int code = 0;
		public double price;

		@Override
		public int command() {
			return RESP_BID;
		}
	}

	// 拍品开始竞价包
	public static class RESPBidBeginPacket extends BasicPacket {
		public static final int RESP_BID_BEGIN = 1006;
		public int rid;
		public TCPStruct.AuctionBid cur_bid; // 当前拍品出价信息
		public HttpStruct.Auction cur_auction; // 当前拍品信息

		@Override
		public int command() {
			return RESP_BID_BEGIN;
		}
	}

	// 拍品竞价结束包
	public static class RESPBidEndPacket extends BasicPacket {
		public static final int RESP_BID_END = 1007;
		public int rid;
		public TCPStruct.AuctionBid final_bid; // 最后出价信息
		public HttpStruct.Auction cur_auction; // 下件拍品
		public TCPStruct.RoomState state; // 房间状态

		@Override
		public int command() {
			return RESP_BID_END;
		}
	}

	// 拍品竞价时间调整包
	public static class RESPBidTimePacket extends BasicPacket {
		public static final int RESP_BID_TIME = 1008;
		public int rid;
		public int gid;
		public long endTime;

		@Override
		public int command() {
			return RESP_BID_TIME;
		}
	}

	// 房间信息返回包
	public static class RESPRoomInfoPacket extends BasicPacket {
		public static final int RESP_ROOM_INFO = 1009;
		public int rid;
		public int rUserCount; // 会场里的人数
		public TCPStruct.RoomState state; // 房间当前状态
		public TCPStruct.AuctionBid auctionBid; // 当前拍品 没开始为null
		public HttpStruct.Auction auction;// 当前拍品或下一个拍品
		public int logs;
		 
		@Override
		public int command() {
			return RESP_ROOM_INFO;
		}
	}

	// 房间关闭包
	public static class RESPRoomClosePacket extends BasicPacket {
		public static final int RESP_ROOM_CLOSE = 1010;
		public int rid;

		@Override
		public int command() {
			return RESP_ROOM_CLOSE;
		}
	}

	// 历史记录返回包
	public static class RESPRoomHistoryPacket extends BasicPacket {
		public static final int RESP_ROOM_HISTORY = 1011;
		public int rid;
		public List<TCPRequest.RoomChatPacket> historys = new ArrayList<TCPRequest.RoomChatPacket>();

		@Override
		public int command() {
			return RESP_ROOM_HISTORY;
		}
	}

	// 房间开始拍卖包
	public static class RESPRoomBeginPacket extends BasicPacket {
		public static final int RESP_ROOM_BEGIN = 1012;
		public int rid;
		public HttpStruct.Auction first_auction; // 第一件拍品

		@Override
		public int command() {
			return RESP_ROOM_BEGIN;
		}
	}
	//房间撤销出价包
	public static class RESPRoomUndoPacket extends BasicPacket {
		public static final int RESP_ROOM_UNDO = 1015;
		public int rid;
		public double price;
		public TCPStruct.AuctionBid currentAuctionBid; // 第一件拍品

		@Override
		public int command() {
			return RESP_ROOM_UNDO;
		}
	}
	// 出价信息更新包
	public static class RESPUserBidPacket extends BasicPacket {
		public static final int RESP_USER_BID = 1013;
		public TCPStruct.AuctionBid bid;
		public int rid;

		@Override
		public int command() {
			return RESP_USER_BID;
		}
	}

	// 拍卖准备包
	public static class RESPBidReadyPacket extends BasicPacket {
		public static final int RESP_BID_READY = 1014;
		public int rid;
		public HttpStruct.Auction next_auction; // 下个拍品信息

		@Override
		public int command() {
			return RESP_BID_READY;
		}
	}

}
