package xiong.com.mvptest.mvp.model.impl;

import xiong.com.mvptest.bean.RequestPacket;
import xiong.com.mvptest.http.HttpNetwork;
import xiong.com.mvptest.http.HttpRequestPacket;
import xiong.com.mvptest.http.HttpResponsePacket;
import xiong.com.mvptest.mvp.model.IResetPass;

/**
 * Created by 62416 on 2016/11/1.
 */

public class ResetPassModel implements IResetPass {
    @Override
    public void resetPass(String mobile, String newPass, final ResetPassListener resetPassListener) {
        RequestPacket.ResetPassword cmd =new RequestPacket.ResetPassword(mobile,newPass);
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
            @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                if(response.code==0){
                    resetPassListener.resetSuccess();
                }
            }
        });
    }
}
