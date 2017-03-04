package xiong.com.mvptest.packet;

import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xiong.com.mvptest.Global;
import xiong.com.mvptest.http.HttpRequestPacket;
import xiong.com.mvptest.http.HttpResponsePacket;

public class HttpRequest {

	/*
	 * public static class GetMessageContent extends HttpRequest{ public String
	 * uid; public GetMessageContent() { super() } }
	 */

	/**
	 * 登录命令
	 * 
	 * @author Lou
	 * 
	 */
	public static class LoginRequest extends HttpRequestPacket {
		public String account; // 用户名
		public String password; // 密码
		public int type; // 1微信登录 2:QQ登录 其他 帐号登录
		public int isVip;

		// 掉用父类的方法 直接把就把这个地址 当作参数搞上去
		public LoginRequest() {
			super("/User/login");
		}

		@Override
		public Type getResponseType() {
			return HttpStruct.UserInfo.class;
		}
	}

	// 获取验证码
	public static class GetVerificationCode extends HttpRequestPacket {
		public String mobile;

		public GetVerificationCode(String mobile) {
			super("/App/get_verification_code");
			this.mobile = mobile;
		}

		@Override
		public Type getResponseType() {
			return String.class;
		}
	}

	// 验证验证码
	public static class VerifyCode extends HttpRequestPacket {
		public String mobile;
		public String code;

		public VerifyCode(String mobile, String code) {
			super("/App/verify_code");
			this.mobile = mobile;
			this.code = code;
		}
	}

	// 注册
	public static class Register extends HttpRequestPacket {
		public String account;
		public String name;
		public String password;

		public Register() {
			super("/User/register");
		}

		@Override
		public Type getResponseType() {
			return HttpStruct.UserInfo.class;
		}
	}

	// 第三方登录
	public static class ThirdLogin extends HttpRequestPacket {
		public static final int Wechat = 1;
		public static final int QQ = 2;
		public String uuid;
		public int type;
		public Map<String, String> info;

		public ThirdLogin(String uuid, int type, Map<String, String> info) {
			super("/User/third_login");
			this.uuid = uuid;
			this.type = type;
			this.info = info;
		}

		@Override
		public Type getResponseType() {
			return HttpStruct.UserInfo.class;
		}
	}

	// 第三方注册
	public static class RegisterWithThird extends HttpRequestPacket {
		public String mobile;
		public String password;
		public String thirdUUID;
		public int thirdType;
		public Map<String, String> thirdInfos;

		public RegisterWithThird() {
			super("/User/register_with_third");
		}

		@Override
		public Type getResponseType() {
			return HttpStruct.UserInfo.class;
		}
	}

	// 第三方与手机号绑定
	public static class BindWithThird extends HttpRequestPacket {
		public String mobile;
		public String thirdUUID;
		public int thirdType;

		public BindWithThird() {
			super("/User/bind_with_third");
		}

		@Override
		public Type getResponseType() {
			return HttpStruct.UserInfo.class;
		}
	}

	// 检查帐号
	public static class CheckMobile extends HttpRequestPacket {
		public String mobile;

		public CheckMobile(String mobile) {
			super("/User/check_mobile");
			this.mobile = mobile;
		}
	}

	// 获取消息列表
	public static class getMessage extends HttpRequestPacket {
		public String uid;
		public String type;
		public int page;
		public int rows;

		public getMessage(String uid, String type, int page, int rows) {
			super("/User/GetUserNewsList");
			this.uid = uid;
			this.type = type;
			this.page = page;
			this.rows = rows;
		}
	}

	// 获取未读消息数量
	public static class getMessageContent extends HttpRequestPacket {
		public String uid;

		public getMessageContent(String uid) {
			super("/User/GetUserNewsCount");
			this.uid = uid;
		}
	}

	// 获取广告位图片
	public static class getAdvertisingImg extends HttpRequestPacket {
		public String adsSet;

		public getAdvertisingImg(String adsSet) {
			super("/Exhibition/GetArticleAdsSet");
			this.adsSet = adsSet;
		}
	}

	// 获取首页五条文章
	public static class getArticlelist extends HttpRequestPacket {
		public String adsSet;
		public String page;
		public String rows;

		public getArticlelist(String adsSet, String page, String rows) {
			super("/Exhibition/GetArticleAdsList");
			this.adsSet = adsSet;
			this.page = page;
			this.rows = rows;
		}
	}

	// 获取文章列表
	public static class getArticleListContent extends HttpRequestPacket {
		public int type;
		public String keyword;
		public int page;
		public int rows;

		public getArticleListContent(int type, String keyword, int page,
									 int rows) {
			super("/Exhibition/GetArticleList");
			this.type = type;
			this.keyword = keyword;
			this.page = page;
			this.rows = rows;
		}
	}

	// 上传cid到服务器
	public static class UploadCid extends HttpRequestPacket {
		public String FUID;
		public String FSerialNo;
		public String FDeviceType;
		public String FUserToken;

		public UploadCid(String FUID, String FSerialNo, String FDeviceType,
						 String FUserToken) {
			super("/User/RecordUserToken");
			this.FUID = FUID;
			this.FSerialNo = FSerialNo;
			this.FDeviceType = FDeviceType;
			this.FUserToken = FUserToken;
		}

		@Override
		public Type getResponseType() {

			return String.class;
		}
	}

	// 获取精品的列表
	public static class GetGoodAuction extends HttpRequestPacket {
		public int type;
		public int page;

		public GetGoodAuction(int page) {
			super("/Topic/importauction");
			this.page = page;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Auction>>() {
			}.getType();
			return type;
		}
	}
	// 获取首页的四条精品
	public static class GetFourQuality extends HttpRequestPacket {
		public int type;
		public int page;

		public GetFourQuality() {
			super("Topic/importauction_test");
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Auction>>() {
			}.getType();
			return type;
		}
	}
	// 获取首页的十条精品
	public static class GetQuality extends HttpRequestPacket {
		public int type;
		public int page;

		public GetQuality() {
			super("Topic/importauction_new");
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Auction>>() {
			}.getType();
			return type;
		}
	}
	//获取首页今日拍品
	public static class GetTodayAuction extends HttpRequestPacket {
//		public int type;
//		public int page;

		public GetTodayAuction() {
			super("Topic/get_today_auction");
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Auction>>() {
			}.getType();
			return type;
		}
	}
	public static class GetTopicListPage extends HttpRequestPacket{
		public int type;
		public int page;
		public GetTopicListPage(int type, int page) {
			super("/Topic/get_topic_list");
			this.type =type;
			this.page = page;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Topic>>() {
			}.getType();
			return type;
		}
	}
	// 获取专场列表
	public static class GetTopicList extends HttpRequestPacket {
		public static final int TOPIC = 1; // 专场
		public static final int ROOM = 2; // 拍卖房间
		public int type;
		public int specail; // type＝1时有效 0全部 1开拍 2预展 3:结拍
		public int page;
		public String uid;
		public int topiccategory;

		public GetTopicList(int type,int page) {
			super("/Topic/get_topic_list");
			/* super("/topic/topiclist"); */
			this.type = type;
			this.uid = Global.uid;
			this.page=page;
		}

		public GetTopicList(int type, int specail, int page,int topiccategory) {
			this(type,page);
			this.specail = specail;
			this.page = page;
			this.topiccategory =topiccategory;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Topic>>() {
			}.getType();
			return type;
		}
	}

	// 获取topic对象
	public static class GetTopicid extends HttpRequestPacket {
		int tid;

		public GetTopicid(int tid) {
			super("/Topic/topicid");
			this.tid = tid;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Topic>>() {
			}.getType();
			return type;
		}
	}

	// 根据单品名称来模糊查询有关该名称的所有单品名称信息
	public static class GetTopicNameByKey extends HttpRequestPacket {
		String key;

		public GetTopicNameByKey(String key) {
			super("/Topic/search");
			this.key = key;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Auction>>() {
			}.getType();
			return type;
		}
	}

	// 根据预展或结拍获取所有列表
	public static class GetTopicList1 extends HttpRequestPacket {
		public static final int TOPIC = 1; // 专场
		public static final int ROOM = 2; // 拍卖房间
		public int type;
		public int specail; // type＝1时有效 0全部 1开拍 2预展 3:结拍
		public int page;
		public String uid;

		public GetTopicList1() {
			/* super("/Topic/get_topic_list"); */
			super("/Topic/topiclist");
			this.uid = Global.uid;
		}

		public GetTopicList1(int specail, int page) {
			super("/Topic/topiclist");
			this.specail = specail;
			this.page = page;

		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Topic>>() {
			}.getType();
			return type;
		}
	}

	// 获取拍品列表
	public static class GetAuctionList extends HttpRequestPacket {
		public int type;
		public int page;

		public GetAuctionList(int type, int page) {
			super("/Topic/get_auction_list");
			this.type = type;
			this.page = page;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Auction>>() {
			}.getType();
			return type;
		}
	}

	// 筛选拍品
	public static class AuctionFilter extends HttpRequestPacket implements
			Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public String key;// 关键词
		public int minDate;
		public int maxDate;
		public String spjg_key;// 送拍机构
		public String cid;
		public int page;

		public AuctionFilter() {
			super("/Topic/auction_filter");
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Auction>>() {
			}.getType();
			return type;
		}
	}

	// 获取专场下的单品列表
	public static class GetTopicAuctions extends HttpRequestPacket {
		public int topic_id;
		public String uid;
		public int page;
		public GetTopicAuctions(int id,int page) {
		//	super("/Topic/get_auctions_by_topic");
			super("/Topic/get_auctions_by_topic_new");
			this.topic_id = id;
			this.uid = Global.uid;
			this.page=page;
		}

		@Override
		public Type getResponseType() {
			return HttpStruct.TopicAuctionsData.class;
		}
	}

	// 根据单品名称和单品类别查询出单品信息
	public static class GetTopicAuctionsBySort extends HttpRequestPacket {
		public int cid;
		public String key;
		public int page;

		public GetTopicAuctionsBySort(int cid, String key, int page) {
			super("/Topic/auction_filter1");
			this.key = key;
			this.cid = cid;
			this.page = page;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Auction>>() {
			}.getType();
			return type;
		}
	}

	// 获取专场信息
	public static class GetTopic extends HttpRequestPacket {
		public int tid;
		public String uid;
		public String uname;

		public GetTopic(int id) {
			super("/Topic/get_topic");
			this.tid = id;
			this.uid = Global.uid;
			this.uname = Global.uname;
		}

		@Override
		public Type getResponseType() {
			return HttpStruct.Topic.class;
		}
	}

	// 获取用户的拍品列表
	public static class GetUserAuctions extends HttpRequestPacket {
		public int uid;
		public int page;

		public GetUserAuctions(int uid, int page) {
			super("/Topic/get_user_auction");
			this.uid = uid;
			this.page = page;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Auction>>() {
			}.getType();
			return type;
		}
	}

	// 获取用户信息
	public static class GetUserInfo extends HttpRequestPacket {
		public String uid;

		public GetUserInfo(String uid) {
			super("/User/get_user_info");
			this.uid = uid;
		}

		@Override
		public Type getResponseType() {
			return HttpStruct.UserInfo.class;
		}
	}

	// 编辑用户资料
	public static class EditUserInfo extends HttpRequestPacket {

		public String uid;
		public HashMap<String, String> info = new HashMap<String, String>();

		public EditUserInfo() {
			super("/User/edit_user_info");
			this.uid = Global.uid;
		}

		@Override
		public Type getResponseType() {
			return null;
		}
	}

	// 获取订单列表
	public static class GetOrderList extends HttpRequestPacket {

		public static final int TYPE_BAOJIA = 0; // 保价订单
		public static final int TYPE_JINGPAI = 1; // 竞拍订单
		public static final int TYPE_JIFEN = 2; // 积分订单
		public static final int TYPE_CASH = 3; // 保证金订单

		// 竞拍订单状态
		public static int JP_STATE_ALL = -1; // 竞拍全部
		public static int JP_STATE_NO_PAY = 0; // 未付款
		public static int JP_STATE_NO_SEND = 1; // 未发货
		public static int JP_STATE_NO_RECEIVE = 2; // 未收货、已发货
		public static int JP_STATE_RECEIVE = 3; // 已签收
		public static int JP_STATE_DELETE = 4; // 已删除
		public static int JP_STATE_DONE = 5; // 已完成
		public static int JP_STATE_BACK = 6; // 已退款

		// 保证金订单状态
		public static int CA_STATE_ALL = -1; // 保证金全部
		public static int CA_STATE_NO_PAY = 0; // 未付款
		public static int CA_STATE_NO_DONE = 1; // 未完成，冻结状态
		public static int CA_STATE_DONE = 3; // 已解冻

		public int type;
		public int state;
		public String uid;
		public int page = 0;

		public GetOrderList(int type, int state, int page) {
			super("/Topic/get_user_order_list");
			this.type = type;
			this.state = state;
			this.uid = Global.uid;
			this.page = page;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.OrderInfo>>() {
			}.getType();
			return type;
		}
	}

	// 获取地址列表
	public static class GetAddressList extends HttpRequestPacket {
		public String uid;

		public GetAddressList() {
			super("/User/get_user_address_list");
			this.uid = Global.uid;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.AddressInfo>>() {
			}.getType();
			return type;
		}
	}

	// 地址操作
	public static class AddressOption extends HttpRequestPacket {

		public static final int ADDRESS_OPTION_ADD = 1;
		public static final int ADDRESS_OPTION＿EDIT = 2;
		public static final int ADDRESS_OPTION_DELETE = 3;
		public static final int ADDRESS_OPTION_DEFAULT = 4;

		public int operate;
		public Map<String, String> address = new HashMap<String, String>();

		public AddressOption() {
			super("/User/edit_user_address");
		}

		public void addAddress(HttpStruct.AddressInfo info) {
			this.operate = ADDRESS_OPTION_ADD;
			address.put("uid", Global.uid);
			address.put("reciver", info.reciver);
			address.put("phoneno", info.phoneno);
			address.put("province", info.province + "");
			address.put("city", info.city + "");
			address.put("district", info.district + "");
			address.put("postcode", info.postcode);
			address.put("address", info.address);
			address.put("memo", info.memo);
			address.put("type", Global.userInfo.type + "");
			address.put("def", HttpStruct.AddressInfo.ADDRESS_NORMAL + "");
		}

		public void editAddress(Map<String, String> editMap, int id) {
			this.operate = ADDRESS_OPTION＿EDIT;
			address.put("uid", Global.uid);
			address.put("id", id + "");
			address.putAll(editMap);
		}

		public void deleteAddress(int id) {
			this.operate = ADDRESS_OPTION_DELETE;
			address.put("id", id + "");
		}

		public void defaultAddress(int id) {
			this.operate = ADDRESS_OPTION_DEFAULT;
			address.put("id", id + "");
			address.put("uid", Global.uid);
		}

		@Override
		public Type getResponseType() {
			return HttpResponsePacket.class;
		}
	}

	// 获取我的竞拍
	public static class GetMyJingPai extends HttpRequestPacket {

		public static int JP_TYPE_ALL = 0; // 全部类型
		public static int JP_TYPE_ING = 1; // 正在竞拍
		public static int JP_TYPE_GET = 2; // 得拍
		public static int JP_TYPE_LOSE = 3; // 失拍

		public int page;
		public String uid;
		public int type;

		public GetMyJingPai(int page, int type) {
			super("/Topic/get_user_bail_auctions");
			this.page = page;
			this.uid = Global.uid;
			this.type = type;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Auction>>() {
			}.getType();
			return type;
		}
	}

	// 获取关注列表
	public static class GetFocusList extends HttpRequestPacket {
		public String uid;
		int type;

		public GetFocusList(int type) {
			// super("/User/get_user_collect_list");
			super("/User/get_user_collect_list_new");
			this.uid = Global.uid;
			this.type = type;
		}

		@Override
		public Type getResponseType() {
			Type ty = null;
			if (type == 0) {
				ty = new TypeToken<List<HttpStruct.Auction>>() {
				}.getType();
			} else {
				ty = new TypeToken<List<HttpStruct.Topic>>() {
				}.getType();
			}
			return ty;
		}
	}

	// 获取委托价格
	public static class GetAuthorice extends HttpRequestPacket {
		public String uid;
		int gid;

		public GetAuthorice(int gid) {
			// super("/User/get_user_collect_list");
			super("/Topic/get_auction_trust");
			this.uid = Global.uid;
			this.gid = gid;
		}

		@Override
		public Type getResponseType() {

			return String.class;
		}
	}

	// 关注操作
	public static class FocusOption extends HttpRequestPacket {

		public final static int FOCUS_OPTION_ADD = 1;
		public final static int FOCUS_OPTION_DELETE = 2;

		public final static int FOCUS_TYPE_AUCTION = 1; // 单品
		public final static int FOCUS_TYPE_TOPIC = 2; // 专场

		public int operate;
		public Map<String, String> collect;

		public FocusOption() {
			super("/User/edit_user_collect");
			collect = new HashMap<String, String>();
		}

		public void addFocus(int pid, int type) {
			this.operate = FOCUS_OPTION_ADD;
			collect.put("uid", Global.uid);
			collect.put("pid", pid + "");
			collect.put("type", type + "");
		}

		public void deleteFocus(int pid, int type) {
			this.operate = FOCUS_OPTION_DELETE;
			collect.put("uid", Global.uid);
			collect.put("pid", pid + "");
			collect.put("type", type + "");
		}

		@Override
		public Type getResponseType() {
			return HttpResponsePacket.class;
		}
	}

	// 获取拍品详情
	public static class GetAuctionDetail extends HttpRequestPacket {

		public int gid;
		public String uid;

		public GetAuctionDetail(int gid) {
			super("/Topic/get_auction_detail");
			this.gid = gid;
			this.uid = Global.uid;
		}

		@Override
		public Type getResponseType() {
			return HttpStruct.AuctionDetail.class;
		}
	}

	// 获取同产拍品
	public static class GetAuctionsBySame extends HttpRequestPacket {

		public int gid;
		public int tid;
		public int page;
		public GetAuctionsBySame(int gid, int tid,int page) {
			super("/Topic/get_other_auction");
			this.gid = gid;
			this.tid = tid;
			this.page=page;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Auction>>() {
			}.getType();
			return type;
		}
	}

	public static class GetAuctionNewestLogs extends HttpRequestPacket {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public int aid;
		public String uid;

		public GetAuctionNewestLogs(int aid) {
			super("/Topic/get_auction_newest_logs");
			this.aid = aid;
			this.uid = Global.uid;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.AuctionLog>>() {
			}.getType();
			return type;
		}
	}

	// 获取订单详情
	public static class GetOrderDetail extends HttpRequestPacket {

		public int oid;
		public String uid;

		public GetOrderDetail(int oid) {
			super("/Topic/get_order_detail");
			this.oid = oid;
			this.uid = Global.uid;
		}

		@Override
		public Type getResponseType() {
			return HttpStruct.OrderDetail.class;
		}
	}

	// 修改订单状态
	public static class OrderOption extends HttpRequestPacket {

		public final static int ORDER_OPTION_EDIT_ADDRESS = 1; // 修改地址
		public final static int ORDER_OPTION_EDIT_STATUS = 2; // 修改订单状态

		public int operate;
		public int oid;
		public HashMap<String, String> data;

		public OrderOption(int oid) {
			super("/Topic/edit_order");
			this.data = new HashMap<String, String>();
			this.oid = oid;
		}

		@Override
		public Type getResponseType() {
			return HttpResponsePacket.class;
		}

		public void editAddress(String address, String recipient,
								String postalcode, String tel) {
			this.operate = ORDER_OPTION_EDIT_ADDRESS;
			this.data.put("address", address);
			this.data.put("recipient", recipient);
			this.data.put("postalcode", postalcode);
			this.data.put("tel", tel);
		}

		public void editStatus(int state) {
			this.operate = ORDER_OPTION_EDIT_STATUS;
			this.data.put("state", "" + state);
		}
	}

	// 用户出价
	public static class UserBid extends HttpRequestPacket {

		public int aid; // 拍品id
		public String uid; // 用户id
		public String uname; // 用户名
		public double bidprice; // 出价
		public double addprice; // 加价幅度

		public UserBid(int aid, double bidprice, double addprice) {
			super("/Topic/user_bid");
			this.aid = aid;
			this.uname = Global.uname;
			this.uid = Global.uid;
			this.bidprice = bidprice;
			this.addprice = addprice;
		}

		public static String getErrorString(int error) {
			if (error == -1) {
				return "参数错误";
			} else if (error == 1) {
				return "未缴纳保证金";
			} else if (error == 2) {
				return "拍品还未开始竞拍";
			} else if (error == 3) {
				return "拍品竞拍已经结束";
			} else if (error == 4) {
				return "最后一次出价的是自己";
			} else if (error == 5) {
				return "出价低于当前最高价，请刷新界面";
			}
			return "出价失败";
		}

		@Override
		public Type getResponseType() {
			return HttpResponsePacket.class;
		}
	}

	// 生成保证金订单
	public static class ProduceBailOrder extends HttpRequestPacket {

		public final static int BAIL_ORDER_TYPE_AUCTION = 1; // 拍品
		public final static int BAIL_ORDER_TYPE_TOPIC = 2; // 专场

		public int type; // 1 拍品保证金 $id = aid // 2 专场保证金 $id = tid
		public String uid; // 用户编号
		public String uname; // 用户姓名
		public int id; // 拍品或专场的id

		public ProduceBailOrder(int type, int id) {
			super("/Topic/produce_bail_order");
			this.type = type;
			this.id = id;
			this.uid = Global.uid;
			this.uname = Global.uname;
		}

		@Override
		public Type getResponseType() {
			return HttpStruct.OrderInfo.class;
		}
	}

	// 创建支付订单
	public static class ProducePayOrder extends HttpRequestPacket {

		public String ordersn; // 订单编号
		public int payment_id; // 支付类型编号
		public String payment; // 支付类型名称

		public ProducePayOrder(String ordersn, int payment_id, String payment) {
			super("/Topic/produce_pay_order");
			this.ordersn = ordersn;
			this.payment_id = payment_id;
			this.payment = payment;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<Map<String, String>>() {
			}.getType();
			return type;
		}
	}

	// 重置密码
	public static class ResetPassword extends HttpRequestPacket {

		public String mobile;
		public String newpass;

		public ResetPassword(String mobile, String password) {
			super("/User/reset_password");
			this.mobile = mobile;
			this.newpass = password;
		}

		@Override
		public Type getResponseType() {
			return HttpResponsePacket.class;
		}
	}

	// 修改密码
	public static class EditPassword extends HttpRequestPacket {

		public String uid;
		public String oldpass;
		public String newpass;

		public EditPassword(String oldpass, String newpass) {
			super("/User/change_password");
			this.oldpass = oldpass;
			this.newpass = newpass;
			this.uid = Global.uid;
		}

		@Override
		public Type getResponseType() {
			return HttpResponsePacket.class;
		}
	}

	// 获取广告数据
	public static class GetAds extends HttpRequestPacket {

		public GetAds() {
			super("/App/get_ads");
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.IndexAd>>() {
			}.getType();
			return type;
		}
	}

	// 提交意见
	public static class SendSuggest extends HttpRequestPacket {

		public String uid;
		public String suggest;

		public SendSuggest(String suggest) {
			super("/App/suggest");
			this.uid = Global.uid;
			this.suggest = suggest;
		}

		@Override
		public Type getResponseType() {
			return HttpResponsePacket.class;
		}
	}

	// 获取版本号
	public static class GetVersion extends HttpRequestPacket {
		public int type;

		public GetVersion() {
			super("/App/get_version");
			type = 1;
		}

		@Override
		public Type getResponseType() {
			return HttpStruct.Version.class;
		}
	}

	// 获取文章内容
	public static class GetArticle extends HttpRequestPacket {
		public static final String REGISTER_PROTOCOL = "APP_REGISTER_PROTOCOL";
		public static final String ABOUTUS = "APP_ABOUTUS";
		public static final String BAIL_PROTOCOL = "APP_BAIL_PROTOCOL";
		public String name;

		public GetArticle(String name) {
			super("/App/get_article");
			this.name = name;
		}

		@Override
		public Type getResponseType() {
			return String.class;
		}
	}

	// 送拍拍品
	public static class CreateTopicAuction extends HttpRequestPacket {
		public int tid;
		public HttpStruct.Auction auction;

		public CreateTopicAuction(int tid, HttpStruct.Auction auction) {
			super("/Topic/create_topic_auction");
			this.tid = tid;
			this.auction = auction;
		}
	}

	// 用户认证
	public static class VerificationUser extends HttpRequestPacket {
		public HttpStruct.UserExtand extand;

		public VerificationUser(HttpStruct.UserExtand extand) {
			super("/User/verification_user");
			this.extand = extand;
		}
	}

	// 个推clientid 上传
	public static class GetTuiClientId extends HttpRequestPacket {
		public String FUID;
		public String FSerialNo;
		public String FDeviceType;
		public String FUserToken;

		public GetTuiClientId(String FUID, String FSerialNo,
							  String FDeviceType, String FUserToken) {
			super("/User/RecordUserToken");
			this.FUID = FUID;
			this.FSerialNo = FSerialNo;
			this.FDeviceType = FDeviceType;
			this.FUserToken = FUserToken;
		}
	}

	// 修改个推消息状态
	public static class UpdateMessageState extends HttpRequestPacket {
		public int Id;

		public UpdateMessageState(int Id) {
			super("/User/UpdateUserNewsState");
			this.Id = Id;
		}

	}

	// 开拍提醒的数据接口
	public static class CameraMessage extends HttpRequestPacket {
		public String uid;
		public int type;
		public int page;
		public int rows;

		public CameraMessage(String uid, int type, int page, int rows) {
			super("/User/GetUserNewsList");
			this.uid = uid;
			this.type = type;
			this.page = page;
			this.rows = rows;

		}

	}

	// 开拍提醒的数据接口
	public static class NotificationMessage extends HttpRequestPacket {
		public String uid;
		public int type;
		public int page;
		public int rows;

		public NotificationMessage(String uid, int type, int page, int rows) {
			super("/User/GetUserNewsList");
			this.uid = uid;
			this.type = type;
			this.page = page;
			this.rows = rows;

		}

	}

	// 拍卖介绍文章
	public static class AuctionArticle extends HttpRequestPacket {
		public String keyword;
		public int type;
		public int page;
		public int rows;

		public AuctionArticle(String keyword, int type, int page, int rows) {
			super("/Exhibition/GetArticleList");
			this.keyword = keyword;
			this.type = type;
			this.page = page;
			this.rows = rows;

		}

	}

	// 获取文章分类接口
	public static class ArticleSortPacket extends HttpRequestPacket {
		public String url;

		public ArticleSortPacket(int code) {
			url = "/Exhibition/GetCateList?code=" + code;
		}
	}

	// 获取拍品分类接口
	public static class AuctionCategory extends HttpRequestPacket {

		public AuctionCategory() {
			super("/Topic/getcategory");
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Category>>() {
			}.getType();
			return type;
		}
	}
	// 获取竞拍规则
	public static class GetBiddingRules  extends HttpRequestPacket {
		public String name ="APP_BAIL_PROTOCOL";
		public int tid;

		public GetBiddingRules(int tid) {
			super("/App/get_config");
			this.tid =tid;
		}

		@Override
		public Type getResponseType() {
			Type type = new TypeToken<List<HttpStruct.Category>>() {
			}.getType();
			return type;
		}
	}
	public static class GetTopic_total extends HttpRequestPacket{
		public int topiccategory;
		public GetTopic_total(int topiccategory) {
			super("Topic/get_topicstate_num");
			this.topiccategory =topiccategory;
		}
	}
	public static class UpdateOrderState extends HttpRequestPacket{
		public String oid;
		public UpdateOrderState(String oid) {
			super("Topic/set_order_underline");
			this.oid =oid;
		}

	}
}
