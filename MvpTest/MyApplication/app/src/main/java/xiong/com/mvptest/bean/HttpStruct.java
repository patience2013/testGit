package xiong.com.mvptest.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xiong.com.mvptest.App;
import xiong.com.mvptest.Global;

public class HttpStruct {

    public static class MessageContent implements Serializable {
    }

    public static class Topic implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        public int id;
        public int type; // 类型：1,普通专场, 2:房间
        public int seller_id; // 发布专场用户id
        public String seller_name; // 发布专场用户名
        public String title; // 专场标题
        public String stitle; // 简短标题(4-5个字)
        public String banner; // 专场横幅
        public String logo; // 拍卖大厅使用
        public String brief; // 专场简介
        public String content; // 专场描述
        public double bailprice; // 保证金
        public int starttime; // 开始时间
        public int endtime; // 结束时间
        public int addtime; // 专场添加时间
        public int state; // 专场状态 1:未通过审核（默认）2.通过审核 3.审核失败 4:关闭
        public int flag; // 0:未推荐1:推荐
        public String remark; // 用于审核失败时管理添加的注释
        public int num_auctions; // 拍品数量
        public int num_bail; // 缴纳保证金人数
        public int num_auction_log; // 专场下拍品出价总次数
        public int type_auction; // 大厅模式：1:大厅自由拍，2:大厅专场拍
        public String goodsName;
        public int roomstate; // 0是属于预展 1是开拍 2是结拍
        public int collectid; // 关注拍品的id
        public int collecttype; // 关注的类别
        public int collectpid;
        public int organization;//房间类型1留青拍卖，2，3，4...其他合作拍卖

        public void setRoomState() {

            Date nowTime = new Date(System.currentTimeMillis());// 获取当前时间
            long currentMills = nowTime.getTime() / 1000;

            Log.i("starTimeEndTime", "startTime" + starttime + "endTime"
                    + endtime + "nowTime" + currentMills);
            if (starttime > currentMills) {
                roomstate = 0;
            } else if (starttime < currentMills && endtime > currentMills) {
                roomstate = 1;
            } else if (currentMills > endtime) {
                roomstate = 2;
            }

        }
    }

    // 统计信息
    public static class CountInfo implements Cloneable {
        public int address;
        public int orders;
        public int no_pay_orders;
        public int no_send_orders;
        public int wait_receive_orders;

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    // 个人信息结构
    public static class UserInfo implements Cloneable {

        public static int TYPE_UNAUTH_FOR_USER = 0;
        public static int TYPE_AUTH_FOR_PERSON = 1;
        public static int TYPE_AUTH_FOR_COMPANY = 2;
        public static int SEX_WOMAX = 0;
        public static int SEX_MAN = 1;

        public String id; // 编号
        public String name; // 昵称
        public String realname; // 真实姓名
        public String mail; // 邮箱
        public String mobile; // 手机账户
        public String head; // 头像
        public int isVip; // vip帐户
        public int sex; // 0：女 、 1：男
        public long birthday; // 生日
        public String tel; // 手机号
        public int type; // 用户认证类型 0：默认，没有认证 1：个人认证用户，2：企业认证用户
        public int account_status;
        public CountInfo count; // 统计信息

        @Override
        public Object clone() throws CloneNotSupportedException {
            UserInfo userInfo = (UserInfo) super.clone();
            userInfo.count = (CountInfo) userInfo.count.clone();
            return userInfo;
        }
    }

    // 版本信息
    public static class Version implements Serializable {
        private static final long serialVersionUID = 1L;
        public int FVersionNo; // 版本号
        public String FPath;// 更新地址
        public String FContent; // 更新日志
        public int FType; // 1强制更新 2选择更新
        public String FMessage;
    }

    public static class Attrs implements Serializable {
        public int id;
        public String name;
        public String value;
    }

    // 拍品信息
    public static class Auction implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        public final static int AUCTION_TYPE_NONE = 0; // 无状态
        public final static int AUCTION_TYPE_READY = 1; // 等待开始
        public final static int AUCTION_TYPE_ING = 2; // 竞拍中
        public final static int AUCTION_TYPE_GET = 3; // 得拍
        public final static int AUCTION_TYPE_LOSE = 4; // 失拍

        public int id;
        public String sn; // 商品编号
        public int cid; // 商品分类
        public int seller_id; // 用户id
        public String seller_name; // 用户名称
        public String goodsname; // 商品名称
        public String author; // 作者',
        public String goods_pic; // 商品图片',
        public int starttime; // 开始时间',
        public int endtime; // 结束时间',
        public int ordertime; // 竞拍生成订单时间',
        public int logtime; // 最新竞拍时间',
        public double startprice; // 商品起始价格',
        public int addsecond; // 时间浮动',
        public double addprice; // 价格浮动',
        public double marketprice; // 市场价',
        public double keepprice; // 拍品保留价',
        public double nowprice; // 当前竞拍价',
        public String remark; // 用于记录拍品审核失败的原因',
        public int state; // 竞拍状态：0.未开始 1.上架中 2.已结束 3.已产生订单',
        public double bailprice; // 拍品保证金',
        public int status; // 拍品审核状态：1：未通过审核（默认）2：通过审核，3：审核失败',
        public int num_bail; // 缴纳保证金人数',
        public int auctioncount; // 出价总次数',
        public String lastuid; // 最后出价用户uid
        public String lastuname; // 最后出价用户名字
        public int hit; // 浏览数
        public String detailpage;
        public String origin;//拍品列表的大图路径
        public String goodsdes;
        public ArrayList<Attrs> attr;

        public AuctionAtt detail; // 详细信息
        public ArrayList<AuctionAlbums> albums; // 相册

        public int owner_tid; // 大于0表示专场单品
        public boolean belong_room; // 是否属于房间
        public String topic_title; // 所属房间标题
        public int tid;
        public int gid;
        public int tuid;
        public boolean exist_authorize = false;// 是否存在委托
        public double authorize_price = -1; // 委托价格
        public int topictype;
        public int collectid; // 关注拍品的id
        public int collecttype; // 关注的类别
        public int collectpid;

        // public String origin; //拍品列表的大图路径
        public int getAuctionType() {
            int now = (int) (App.currentTimeMillis() / 1000);
            if (starttime == 0 || starttime > now) {
                return AUCTION_TYPE_READY;
            } else if (starttime < now && (endtime == 0 || endtime > now)) {
                return AUCTION_TYPE_ING;
            } else if (endtime < now) {
                // 已结束
                if (TextUtils.isEmpty(lastuid) || !lastuid.equals(Global.uid)
                        || state != 3) {
                    return AUCTION_TYPE_LOSE;
                } else {
                    return AUCTION_TYPE_GET;
                }
            }
            return AUCTION_TYPE_NONE;
        }

        // 0 预展 1是开拍 2是结拍
        public int getRoomState() {
            int now = (int) (App.currentTimeMillis() / 1000);
            if (starttime == 0 || starttime > now) {
                return 0;
            } else if (starttime < now && (endtime == 0 || endtime > now)) {
                return 1;
            } else if (endtime < now) {
                return 2;
            }
            return AUCTION_TYPE_NONE;
        }
    }

    // 单品属性
    public static class AuctionAtt implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        public int id; // 自增id
        public int aid; // 拍品id
        public List<Attr> attr; // 分类属性值，序列化保存
        public double freight; // 运费
        public String content; // 拍品描述
        public String tag; // 拍品标签，逗号隔开
    }

    // 单品相册
    public static class AuctionAlbums implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        public int id; // 自增id
        public int aid; // 拍品id
        public String type; // 类型:相册，附加图
        public String title; // 标题
        public int size; // 附件大小
        public String thumb; // 缩略图路径
        public String patch; // 中图
        public String origin; // 原始图片路径
        public int uploadtime; // 上传时间
        public int sign; // 图片排序

        public static AuctionAlbums createAuctionPic(String path) {
            AuctionAlbums albums = new AuctionAlbums();
            albums.type = "img";
            String name = path.substring(path.lastIndexOf('/') + 1);
            albums.thumb = path.replace(name, "s_" + name);
            albums.patch = path.replace(name, "m_" + name);
            albums.origin = path;
            return albums;
        }
    }

    // 竞拍日志
    public static class AuctionLog {
        public int id; // 自增id
        public int aid; // 拍品ID
        public int uid; // 用户ID
        public String buyer_name; // 用户名称
        public String ip; // 用户IP
        public String address; // ip所在地
        public double bidprice; // 出价金额
        public int addtime; // 出价时间
        public String buyer_token;// 号牌

    }

    // 单品详情
    public static class AuctionDetail {

        public Auction auction;
        public ArrayList<AuctionLog> auction_log;
        public boolean is_att; // 0未关注，1关注
        public boolean is_pay_bail; // 0未交付，1交付
        public int owner_tid; // 大于0表示专场单品
        public boolean belong_room;
    }

    // 订单信息
    public static class OrderInfo {

        public final static int PAY_TYPE_NONE = -1; // 未选择支付方式
        public final static int PAY_TYPE_WEIXIN = 1; // 微信支付
        public final static int PAY_TYPE_ALIPAY = 2; // 支付宝支付
        public final static int PAY_TYPE_OFFLINE =3; //线下支付

        public final static String PAY_NAME_WEIXIN = "微信支付";
        public final static String PAY_NAME_ALIPAY= "支付宝支付";

        public int id; // 自增id
        public int seller_id; // 卖家id
        public int uid; // 买家id
        public String buyer_name; // 买家用户名
        public String buyer_token; // 保证金号牌
        public String ordersn; // 订单编号
        public int object_id; // 对象ID
        public String object_type; // 对象类型， a:拍品，t:房间或者专场、o：订单
        public String object_name; // 对象名称
        public String object_image; // 图片
        public int pay_type; // 1.微信支付
        public int num; // 数量
        public double price; // 拍品成交价格
        public double amount; // 订单总金额
        public double commission; // 佣金
        public String delivery_company; // 快递公司
        public String delivery_no; // 订单号
        public double freight; // 邮费
        public String recipient; // 收件人
        public String address; // 收货地址
        public String postalcode; // 邮编
        public String tel; // 电话
        public String remark; // 备注
        public int type; // 订单类型：0:保价订单,1：竞拍订单,2：积分订单，3:保证金订单
        public long addtime; // 添加时间
        public long modify_time; // 修改时间
        public int state; // 订单状态 0：未付款 1.未发货 2.已发货 3.已签收 4.已删除 5.已完成6.已退款
        // 保证金订单状态:1：已经付款 3：完成交易 6已经退款 5：交易结束
        public String topicendtime;
        public String topicstate;

        public static String getPaymentName(int type) {
            if (type == PAY_TYPE_WEIXIN) {
                return PAY_NAME_WEIXIN;
            }
            return "";
        }
    }

    // 订单详情
    public static class OrderDetail {
        public OrderInfo order; // 订单信息
        public UserInfo seller; // 卖家信息
    }

    // 关注单品
    public static class FocusInfo {
        public int id; // 自增id
        public int uid; // 用户id
        public String url; // 收藏物品的url
        public int pid; // 添加收藏的物品id
        public int type; // 收藏类型，1为拍品，2为专场
        public String image; // 商品图片路径
        public String title; // 标题
        public int isdeal; // 收藏是否软删除，0为正常，1为软删除
        public int addtime; // 添加时间
        public int num_bail; // 保证金
        public int auctioncount; // 出价总次数
        public int nowprice; // 当前价格
        public Topic topic;
        public Auction auction;
    }

    // 地址信息
    public static class AddressInfo implements Parcelable, Cloneable {

        public final static int ADDRESS_NORMAL = 0;
        public final static int ADDRESS_DEFAULT = 1;

        public int id; // 自增id
        public int uid; // 用户id
        public String name; // 用户名
        public String reciver; // 收货人
        public String phoneno; // 收货人电话
        public int province; // 省
        public int city; // 市
        public int district; // 地区
        public String postcode; // 邮编
        public String address; // 详细地址
        public String memo; // 备注
        public int type; // 地址所属用户类型：0 (默认)个人认证和未认证用户 1：认证企业用户
        public int def; // 1默认地址 0其他

        public AddressInfo() {
            this.name = "";
            this.reciver = "";
            this.phoneno = "";
            this.postcode = "";
            this.address = "";
            this.memo = "";
            this.province = -1;
            this.city = -1;
            this.district = -1;
        }

        public static HashMap<String, String> getChangeMap(AddressInfo oldInfo,
                                                           AddressInfo newInfo) {
            HashMap<String, String> editMap = new HashMap<String, String>();
            if (!oldInfo.reciver.equals(newInfo.reciver)) {
                editMap.put("reciver", newInfo.reciver);
            }
            if (!oldInfo.phoneno.equals(newInfo.phoneno)) {
                editMap.put("phoneno", newInfo.phoneno);
            }
            if (oldInfo.province != newInfo.province) {
                editMap.put("province", newInfo.province + "");
            }
            if (oldInfo.city != newInfo.city) {
                editMap.put("city", newInfo.city + "");
            }
            if (oldInfo.district != newInfo.district) {
                editMap.put("district", newInfo.district + "");
            }
            if (!oldInfo.postcode.equals(newInfo.postcode)) {
                editMap.put("postcode", newInfo.postcode);
            }
            if (!oldInfo.address.equals(newInfo.address)) {
                editMap.put("address", newInfo.address);
            }
            if (!oldInfo.memo.equals(newInfo.memo)) {
                editMap.put("memo", newInfo.memo);
            }
            return editMap;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(uid);
            dest.writeString(name);
            dest.writeString(reciver);
            dest.writeString(phoneno);
            dest.writeInt(province);
            dest.writeInt(city);
            dest.writeInt(district);
            dest.writeString(postcode);
            dest.writeString(address);
            dest.writeString(memo);
            dest.writeInt(type);
            dest.writeInt(def);
        }

        public static final Creator<AddressInfo> CREATOR = new Creator<AddressInfo>() {

            @Override
            public AddressInfo[] newArray(int size) {
                // TODO Auto-generated method stub
                return new AddressInfo[size];
            }

            @Override
            public AddressInfo createFromParcel(Parcel source) {
                // TODO Auto-generated method stub
                AddressInfo info = new AddressInfo();
                info.id = source.readInt();
                info.uid = source.readInt();
                info.name = source.readString();
                info.reciver = source.readString();
                info.phoneno = source.readString();
                info.province = source.readInt();
                info.city = source.readInt();
                info.district = source.readInt();
                info.postcode = source.readString();
                info.address = source.readString();
                info.memo = source.readString();
                info.type = source.readInt();
                info.def = source.readInt();
                return info;
            }
        };

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    // 首页广告数据
    public static class IndexAd {
        public int id; // 广告id
        public String url; // 需要展示的url
        public String img; // 需要显示的图片
        public String title; // 标题
        public int sign; // 排序
    }

    // 新的首页广告数据
    public static class NewIndexAd {
        public int Id; // 广告id
        public String Url; // 需要展示的url
        public String Link;
        public String Img; // 需要显示的图片
        public String Type;
        public String Title; // 标题
        public int Sort; // 排序
        public Boolean IsEnabled;
        public int ClicRate;
    }

    public static class SystemMessage {
        public String message;
        public int time;
    }

    public static class Attr implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        public int id;
        public String name;
        public String value;
    }

    public static class Trustauction {
        public int id;
        public int taid;
        public int tuid;
        public double trustprice;
        public String addtime;
    }

    public static class TopicAuctionsData {
        public boolean is_pay_bail;
        public boolean is_att;
        public int room_state = -1; // 0 预展 1是开拍 2是结拍
        public List<Trustauction> trustauction;
        public List<Auction> auctions;
    }

    // 分享结构
    public static class ShareInfo implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        public Topic topic = null;
        public Auction auction = null;
    }

    // 用户认证信息
    public static class UserExtand {
        public static final int PERSON = 1;
        public static final int COMPANY = 2;
//        public int id;
        public int uid;
        public int account_type;
        public String com_name;
        public String com_contact;
        public String com_phone;
        public String addr_detail;
        public String com_legal_represent;
        public String com_business_no;
        public String com_business_pic;
        public String com_tax_pic;
        public String com_org_pic;
        public String per_name;
        public String per_contact;
        public String per_ic;
        public String per_positive_pic;
        public String per_negative_pic;

        public boolean verify() {
            if (uid == 0)
                return false;
            // 个人认证
            if (this.account_type == PERSON) {
                if (TextUtils.isEmpty(addr_detail)
                        || TextUtils.isEmpty(per_name)
                        || TextUtils.isEmpty(per_contact)
                        || TextUtils.isEmpty(per_ic)
                        || TextUtils.isEmpty(per_negative_pic)
                        || TextUtils.isEmpty(per_positive_pic)) {
                    return false;
                }
                // 企业认证
            } else if (this.account_type == COMPANY) {
                if (TextUtils.isEmpty(com_name)
                        || TextUtils.isEmpty(com_contact)
                        || TextUtils.isEmpty(com_phone)
                        || TextUtils.isEmpty(addr_detail)
                        || TextUtils.isEmpty(com_legal_represent)
                        || TextUtils.isEmpty(com_business_no)
                        || TextUtils.isEmpty(com_business_pic)
                        || TextUtils.isEmpty(com_tax_pic)
                        || TextUtils.isEmpty(com_org_pic)) {
                    return false;
                }
            }
            return true;
        }
    }

    // 界面文章分类
    public static class ArticleSort {
        public int Value;
        public String Text;
        public int Sort;
    }

    public static class Category implements Serializable {
        public int cid;
        public String categoryname;
        public int sign;
        public String img;
        public String smallimg;
    }

//    public static class Link implements Serializable {
//        public int Id;
//        public String Name;
//        public String Type;
//    }
//
//    public static class Rows implements Serializable {
//        public int Id;
//        public String Title;
//        public String TitleImg;
//        public String Depic; // 描叙
//        public String Link; // 用户IP
//    }
//
//    // 广告轮播图
//    public static class Content implements Serializable {
//        public int Id; // 广告图id
//        public String Name; // 标题
//        public String Icon; // 图片
//        public String Section;
//        public List<Article> Rows;
//    }
//
//    public static class AllAdvertising {
//        public String StatusCode;
//        public String ErrorMessage;
//        public ArrayList<Content> Content;
//    }
//
//    public static class PaySort {
//        public int type;
//        public String name;
//
//    }
//
//    // 文章
//    public static class Articles implements Serializable {
//        public int total;
//        public List<Article> rows;
//        public boolean success;
//        public String message;
//    }
//
//    public static class ThirdArticle implements Serializable {
//        public String title;
//        public ArrayList<Article> ArticleList;
//    }
//        public static class IndexMessage implements Serializable {
//            public int id;
//            public int type;
//            public int type_auction;
//            public int seller_id;
//            public String seller_name;
//            public String title;
//            public String stitle;
//            public String logo;
//            public String banner;
//            public String brief;
//            public String content;
//            public Double bailprice;
//            public String pretime;
//            public String starttime;
//            public String endtime;
//            public String addtime;
//            public int state;
//            public int flag;
//            public String remark;
//            public int num_auctions;
//            public int num_bail;
//            public int num_auction_log;
//            public int hit;
//            public int sign;
//            public int roomstate;
//        }
//    public static class IndexMessages implements Serializable {
//        public int total;
//        public ArrayList<IndexMessage> rows;
//    }
//    public static class CooperationList implements Serializable {
//        public int StatusCode;
//        public String ErrorMessage;
//        public ArrayList<Cooperation> Content;
//    }
//    public static class Cooperation implements Serializable {
//        public int AIID;
//        public String MingCheng;
//        public String PaiXu;
//        public String TuPian;
//        public String JieShaoSM;
//        public String LastModifyTime;
//    }
//    public static class indexArticle implements Serializable {
//        public int StatusCode;
//        public String ErrorMessage;
//        public ArrayList<Article> Content;
//    }
}
