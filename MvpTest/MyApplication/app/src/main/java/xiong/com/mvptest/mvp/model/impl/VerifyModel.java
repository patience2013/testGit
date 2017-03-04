package xiong.com.mvptest.mvp.model.impl;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;
import xiong.com.mvptest.bean.RequestPacket;
import xiong.com.mvptest.http.HttpNetwork;
import xiong.com.mvptest.http.HttpRequestPacket;
import xiong.com.mvptest.http.HttpResponsePacket;
import xiong.com.mvptest.mvp.model.IVerifyMobile;
import xiong.com.mvptest.packet.HttpRequest;

/**
 * Created by 62416 on 2016/10/28.
 */

public class VerifyModel implements IVerifyMobile {
    @Override
    public void verifyMobile(String mobile, final VerifyMobileListener verifyListener) {
        RequestPacket.CheckMobile cmd =new RequestPacket.CheckMobile(mobile);
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
            @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                    verifyListener.success(response.code,response.message);
            }
        }, new HttpNetwork.NetErrorCallback() {
            @Override
            public void onError(HttpRequestPacket request, String errorMsg) {

            }
        });
    }
    @Override
    public void sendVerfyCode(String mobile, final SendVerifyCodeListener sendVerifyCodeListener) {
        RequestPacket.GetVerificationCode cmd =new RequestPacket.GetVerificationCode(mobile);
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
            @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                if(response.code==0){

                    sendVerifyCodeListener.SendSuccess();
                }
            }
        });
    }

    @Override
    public void verifyCode(String mobile, String code, final VerifyCodeListener verifyCodeListener) {
        RequestPacket.VerifyCode cmd =new RequestPacket.VerifyCode(mobile,code);
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
            @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                if(response.code==0){
                    verifyCodeListener.verifyCodeSuccess();
                }else{
                    verifyCodeListener.verifyCodeFailed(response.message);
                }
            }
        });
    }

    @Override
    public void bindThird(BindThirdListener bindThirdListener) {

    }
}
