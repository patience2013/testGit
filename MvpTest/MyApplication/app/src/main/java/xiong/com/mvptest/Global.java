package xiong.com.mvptest;


import java.util.ArrayList;

import xiong.com.mvptest.bean.HttpStruct;


//全局变量
public class Global {
	// 商户PID
	public static final String PARTNER = "2088121228782252";
	// 商户收款账号
	public static final String SELLER = "lqaution@126.com";
	// 商户私钥，pkcs8格式
	public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQ EFAASCAmEwggJdAgEAAoGBAM01qWGFl8b71DdPYIQAJdQo9ccSynEThFL3Ec/PowoIuweFau/P4vEMr5J24njvROACJGPw+SIOTeiCYYbjrnvZVXecbu13YGvDZYUnve75sincAIlqDfNed/npo3bviZKFVCrS9z4aSSr0NpDK9l9vRX2WWoSHr172UTWj8tA5AgMBAAECgYEAhxu2mpPHm2ZAke38IMysG2SgdWKE0TBFZg5vtsABm2CmK0yHXL8lrwloK2mEsJTwZwBiDz8f+HEskFIPfi03ufqIkfEZOIn6zIg4YDXeOILingsiaqSlxUNf8pyX56rzJqxUkQWF5WaKJK1YrBsvvgQ3r4c0LM5BMUR5Ng477uUCQQD2HII4KD2i8iuO5iFGmx0pJfOQAk/d3iW9ML5jqtxqUEPq1KkBcNOLNYuSXYuosspeEute1/pf/p6Y6UNycJAfAkEA1XRwdd9KNZqn3vyhupHE4VZOUJgjYDik0otHAZq4KAA0b7nlnByDjsPRRPJEll3MeBgYEHwgSQiUVSgLlvG0pwJAKeAZ7BI0wpfv/2V6vffAk3LIqXUrP7X/t9wmWPEGhfRYSgmbJAqdg+89a08NZ0vckPspHz8ic9jwBVaTSlKKwwJADeJ4GkK/CgOYXdnjD0bCHsJFb9qlmnG0zvk+Ui1lQzzYLFhURjZYnGXCbBCyQ3GwhjNTBWd9liKXu35SWqw4SwJBAKJBlmhFZbKxDqoJnHXMv70uT6aVurkq4I/p/1qfnTHvvUlWAF+eswdj4BWsvIglxCcvYasunNzQd/LiXmtNKbE=";
	// 支付宝公钥
	//开放平台应用公钥
//	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNNalhhZfG+9Q3T2CEACXUKPXHEspxE4RS9xHPz6MKCLsHhWrvz+LxDK+SduJ470TgAiRj8PkiDk3ogmGG46572VV3nG7td2Brw2WFJ73u+bIp3ACJag3zXnf56aN274mShVQq0vc+Gkkq9DaQyvZfb0V9llqEh69e9lE1o/LQOQIDAQAB";
	//开放平台支付宝公钥
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	public static final int SDK_PAY_FLAG = 1;


	public static final String APP_ID = "wxacd0d265406c9d21";
	public static final String API_KEY = "F587941D8B69227B830A43B7EB8BC557";

	public static String uid = "";
	public static String uname = "";
	public static int isVip;
	public static HttpStruct.UserInfo userInfo = null;
	public static int LOAD_PAGE_BEGIN = 0;
	public static int ADDRESS_EDIT_REQUEST = 1; // 收货地址编辑
	public static int ADDRESS_CHOOSE_REQUEST = 2; // 地址选择
	public static int ORDER_CHOOSE_ADDRESS_REQUEST = 3; // 订单选择收货地址
	public static int ORDER_BAIL_PAY_REQUEST = 4; // 保证金订单请求支付

	public static int ADDRESS_EDIT_RESULT = 1; // 收货地址编辑返回
	public static int ADDRESS_CHOOSE_RESULT = 2; // 地址选择返回
	public static int ORDER_CHOOSE_ADDRESS_RESULT = 3; // 订单选择收货地址返回
	public static int ORDER_BAIL_PAY_RESULT = 4; // 保证金订单支付返回

	public static String RECIVER_ADDRESS_CHANGE = "address_change"; // 添加编辑收货地址
	public static String RECIVER_ORDER_CHANGE = "order_change"; // 订单数据改变
	public static String RECIVER_FOCUS_CHANGE = "focus_change"; // 关注数据改变

	public static int AuthSendTime = 60; // 请求验证码间隔时间
	public static ArrayList<HttpStruct.ArticleSort> articleSorts;
	public static final int SYSTEM_ARTICLE_TYPE = 0;
	public static final int SUBLIST_ARTICLE_TYPE = 1;
	public static final int AUCTION_ARTICLE_TYPE = 2;
}
