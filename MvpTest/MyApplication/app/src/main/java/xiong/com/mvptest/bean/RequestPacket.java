package xiong.com.mvptest.bean;

import java.lang.reflect.Type;
import java.util.Map;

import xiong.com.mvptest.http.HttpRequestPacket;
import xiong.com.mvptest.http.HttpResponsePacket;
import xiong.com.mvptest.packet.HttpStruct;

/**
 * Created by 62416 on 2016/10/19.
 */

public class RequestPacket {
    /**
     * 登录命令
     *
     * @author xiong
     *
     */
    public static class LoginRequest extends HttpRequestPacket{
        public String account; // 用户名
        public String password; // 密码
        public int type; // 1微信登录 2:QQ登录 其他 帐号登录
        public int isVip;
        public LoginRequest(){
            super("/User/login");
        }
    }
    /**
     * 检查手机号是否注册
     *
     * @author Xiong
     *
     */
    public static class CheckMobile extends HttpRequestPacket{
        public String mobile;

        public CheckMobile(String mobile) {
            super("/User/check_mobile");
            this.mobile = mobile;
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
    // 注册
    public static class Register extends HttpRequestPacket {
        public String account;
        public String name;
        public String password;

        public Register() {
            super("/User/register");
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
    public static class GetUserArticle extends HttpRequestPacket{
        public static final String REGISTER_PROTOCOL = "APP_REGISTER_PROTOCOL";
        public static final String ABOUTUS = "APP_ABOUTUS";
        public static final String BAIL_PROTOCOL = "APP_BAIL_PROTOCOL";
        public String name;
        public GetUserArticle(String name){
            super("/App/get_article");
            this.name =name;
        }
    }
}
