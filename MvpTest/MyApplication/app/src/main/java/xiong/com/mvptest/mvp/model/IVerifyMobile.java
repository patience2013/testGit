package xiong.com.mvptest.mvp.model;

import xiong.com.mvptest.bean.HttpStruct;

/**
 * Created by 62416 on 2016/10/28.
 */

public interface IVerifyMobile {
    void verifyMobile(String mobile,VerifyMobileListener verifyMobileListener);
    void sendVerfyCode(String mobile,SendVerifyCodeListener sendVerifyCodeListener);
    void verifyCode(String mobile,String code,VerifyCodeListener verifyCodeListener);
    void bindThird(BindThirdListener bindThirdListener);
    interface VerifyMobileListener{
        void success(int code,String message);
        void failed();
    }
    interface SendVerifyCodeListener{
        void SendSuccess();
        void SendFailed();
    }
    interface VerifyCodeListener{
        void verifyCodeSuccess();
        void verifyCodeFailed(String msg);
    }
    interface BindThirdListener{
        void bindThirdSuccess(HttpStruct.UserInfo userInfo);
        void bindThirdFailed();
    }
}
