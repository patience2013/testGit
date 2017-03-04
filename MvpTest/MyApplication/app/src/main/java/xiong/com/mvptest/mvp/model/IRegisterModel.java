package xiong.com.mvptest.mvp.model;

import xiong.com.mvptest.bean.RequestPacket;

/**
 * Created by 62416 on 2016/11/2.
 */

public interface IRegisterModel {
    void register(String mobile,String code,RegisterListener registerListener);
    interface RegisterListener{
        void registerSuccess();
        void registerFailed();
    }
    void verifyMobile(String mobile,IVerifyMobile.VerifyMobileListener verifyMobileListener);
    void sendVerfyCode(String mobile,IVerifyMobile.SendVerifyCodeListener sendVerifyCodeListener);
    void verifyCode(String mobile,String code,IVerifyMobile.VerifyCodeListener verifyCodeListener);
    void bindThird(String mobile, RequestPacket.ThirdLogin thirdLogin,IVerifyMobile.BindThirdListener bindThirdListener);
    void requestUserAgreement(RequestUserAgreementListener requestUserAgreementListener);
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
        void bindThirdSuccess();
        void bindThirdFailed();
    }
    interface RequestUserAgreementListener{
        void RequestSuccess(String content);
    }
}
