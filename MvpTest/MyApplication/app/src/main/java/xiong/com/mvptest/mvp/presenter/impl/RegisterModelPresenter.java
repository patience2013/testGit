package xiong.com.mvptest.mvp.presenter.impl;

import android.util.Log;

import xiong.com.mvptest.Global;
import xiong.com.mvptest.bean.HttpStruct;
import xiong.com.mvptest.bean.RequestPacket;
import xiong.com.mvptest.http.HttpNetwork;
import xiong.com.mvptest.http.HttpRequestPacket;
import xiong.com.mvptest.http.HttpResponsePacket;
import xiong.com.mvptest.mvp.model.IRegisterModel;
import xiong.com.mvptest.mvp.model.IVerifyMobile;
import xiong.com.mvptest.mvp.model.impl.RegisterModel;
import xiong.com.mvptest.mvp.model.impl.VerifyModel;
import xiong.com.mvptest.mvp.view.impl.IRegisterView;
import xiong.com.mvptest.mvp.view.impl.IVerifyView;
import xiong.com.mvptest.util.Config;
import xiong.com.mvptest.util.ToastUtil;

/**
 * Created by 62416 on 2016/10/28.
 */

public class RegisterModelPresenter {
    private RegisterModelPresenter presenter;
    private RegisterModel model;
    private IRegisterView iView;
    private RequestPacket.ThirdLogin mThirdLogin;
    private Boolean registered;
    public RegisterModelPresenter(IRegisterView iView, RequestPacket.ThirdLogin mThirdLogin){
        this.iView =iView;
        this.mThirdLogin =mThirdLogin;
        model =new RegisterModel();
    }
    public void VerifyMobile(){
        model.verifyMobile(iView.getMobile(), new IVerifyMobile.VerifyMobileListener() {
            @Override
            public void success(int code,String message) {
                if(code ==0 || (code==-2 && mThirdLogin!=null)){
                    registered =code==-2;
                    iView.sendVerificationCode(iView.getMobile());
                }else if(code==-2){
                    iView.showToast("该用户已经注册,可以直接登录");
                }
            }

            @Override
            public void failed() {
            }
        });
    }
    public void VerifyCode(){
        model.verifyCode(iView.getMobile(), iView.getCode(), new IVerifyMobile.VerifyCodeListener() {
            @Override
            public void verifyCodeSuccess() {
                if(mThirdLogin!=null){
                    BindThird();
                }else{
                    iView.toRegisterFinish(iView.getMobile());
                }
            }

            @Override
            public void verifyCodeFailed(String message) {
                iView.toRegisterFinish(iView.getMobile());
//                iView.verifyCodeFailed(message);
            }
        });
    }
    public void BindThird(){
        model.bindThird(iView.getMobile(), mThirdLogin, new IVerifyMobile.BindThirdListener() {
            @Override
            public void bindThirdSuccess(HttpStruct.UserInfo userInfo) {
                iView.toMainActivity();
            }

            @Override
            public void bindThirdFailed() {

            }
        });
    }
    public void ShowAgreement(){
        model.requestUserAgreement(new IRegisterModel.RequestUserAgreementListener() {
            @Override
            public void RequestSuccess(String content) {
                iView.showAgreement(content);
            }
        });
    }
}
