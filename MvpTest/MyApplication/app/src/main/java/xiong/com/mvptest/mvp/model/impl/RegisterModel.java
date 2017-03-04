package xiong.com.mvptest.mvp.model.impl;

import xiong.com.mvptest.Global;
import xiong.com.mvptest.bean.HttpStruct;
import xiong.com.mvptest.bean.RequestPacket;
import xiong.com.mvptest.http.HttpNetwork;
import xiong.com.mvptest.http.HttpRequestPacket;
import xiong.com.mvptest.http.HttpResponsePacket;
import xiong.com.mvptest.mvp.model.IRegisterModel;
import xiong.com.mvptest.mvp.model.IVerifyMobile;
import xiong.com.mvptest.util.Config;

/**
 * Created by 62416 on 2016/11/2.
 */

public class RegisterModel implements IRegisterModel {
    @Override
    public void register(String mobile, String code, final RegisterListener registerListener) {
        RequestPacket.Register cmd = new RequestPacket.Register();
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
            @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                if (response.code == 0) {
                    registerListener.registerSuccess();
                }
            }
        });
    }

    @Override
    public void verifyMobile(String mobile, final IVerifyMobile.VerifyMobileListener verifyMobileListener) {
        RequestPacket.CheckMobile cmd = new RequestPacket.CheckMobile(mobile);
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
            @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                verifyMobileListener.success(response.code, response.message);
            }
        }, new HttpNetwork.NetErrorCallback() {
            @Override
            public void onError(HttpRequestPacket request, String errorMsg) {

            }
        });
    }

    @Override
    public void sendVerfyCode(String mobile, final IVerifyMobile.SendVerifyCodeListener sendVerifyCodeListener) {
        RequestPacket.GetVerificationCode cmd = new RequestPacket.GetVerificationCode(mobile);
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
            @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                if (response.code == 0) {

                    sendVerifyCodeListener.SendSuccess();
                }
            }
        });
    }

    @Override
    public void verifyCode(String mobile, String code, final IVerifyMobile.VerifyCodeListener verifyCodeListener) {
        RequestPacket.VerifyCode cmd = new RequestPacket.VerifyCode(mobile, code);
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
            @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                if (response.code == 0) {
                    verifyCodeListener.verifyCodeSuccess();
                } else {
                    verifyCodeListener.verifyCodeFailed(response.message);
                }
            }
        });
    }

    @Override
    public void bindThird(String mobile, RequestPacket.ThirdLogin thirdLogin, final IVerifyMobile.BindThirdListener bindThirdListener) {
        final RequestPacket.BindWithThird cmd = new RequestPacket.BindWithThird();
        cmd.mobile = mobile;
        cmd.thirdUUID = thirdLogin.uuid;
        cmd.thirdType = thirdLogin.type;
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
            @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                if (response.code == 0) {
                    // 注册成功 跳转到主页
                    HttpStruct.UserInfo ret = response.getData(request.getResponseType());
                    if (ret != null) {
                        Global.uid = String.valueOf(ret.id);
                        Global.userInfo = ret;
                        Config.getInstance().set(Config.UUID, cmd.thirdUUID);
                        Config.getInstance().setInt(Config.UUID_TYPE,
                                cmd.thirdType);
                        Config.getInstance().setBoolean(Config.THIRD_LOGIN, true);
                        bindThirdListener.bindThirdSuccess(ret);
                    }
                }
            }
        });
    }

    @Override
    public void requestUserAgreement(final RequestUserAgreementListener requestUserAgreementListener) {
        RequestPacket.GetUserArticle cmd =new RequestPacket.GetUserArticle(RequestPacket.GetUserArticle.REGISTER_PROTOCOL);
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
            @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                if(response.code==0){
                    String content=response.data;
                    requestUserAgreementListener.RequestSuccess(content);
                }
            }
        });
    }
}
