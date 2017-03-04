package xiong.com.mvptest.packet;

public class TCPStruct {

	public static enum RoomState {
		RoomState_WAITING_BEGIN, // 会场未开始
		RoomState_WAITING_BID, // 等待拍品开始
		RoomState_BIDDING, // 拍品正在拍卖
		RoomState_END, // 会场结束
	};

	/**
	 * 拍品当前出价结构
	 * 
	 * @author 卢明渊
	 *  
	 */
	public static class AuctionBid {
		public int gid; // 单品id
		public long startBidTime; // 竞拍开始时间
		public double currentPrice; // 当前竞拍价
		public String currentUid; // 当前拍品竞价最高的用户
		public String currentUname; //当前用户的昵称
		public long endTime; // 竞拍结束时间
		public int isBiding; // 是否竞价中
		public String currentName;//当前用户的真正昵称
	}

	// 拍品基本信息
	public static class Auction {
		public int gid; // 拍品id
		public String name; // 作品 名
		public String author; // 作者
		public String pic; // 作品图
		public double startPrice; // 底价
		public double addPrice; // 最低加价
		public double referencePrice;//市场价/参考价
		public int shengyu;//拍场拍品剩余数量
		public double realAddprice;//可以变动的加价区间
	}
}
