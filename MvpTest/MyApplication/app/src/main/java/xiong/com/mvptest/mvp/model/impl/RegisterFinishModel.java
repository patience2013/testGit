package xiong.com.mvptest.mvp.model.impl;

import xiong.com.mvptest.Global;
import xiong.com.mvptest.bean.HttpStruct;
import xiong.com.mvptest.bean.RequestPacket;
import xiong.com.mvptest.http.HttpNetwork;
import xiong.com.mvptest.http.HttpRequestPacket;
import xiong.com.mvptest.http.HttpResponsePacket;
import xiong.com.mvptest.mvp.model.IRegisterFinishModel;
import xiong.com.mvptest.util.Config;

/**
 * Created by lenovo on 2016/11/17.
 */

public class RegisterFinishModel implements IRegisterFinishModel {

    @Override
    public void register(final String mobile, String nickname, final String password, final RegisterListener listener) {
        RequestPacket.Register cmd =new RequestPacket.Register();
        cmd.account =mobile;
        cmd.name =nickname;
        cmd.password =password;
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
            @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                if(response.code==0){
                    HttpStruct.UserInfo ret = response.getData(HttpStruct.UserInfo.class);
                    if (ret != null) {
                        Global.uid = String.valueOf(ret.id);
                        Global.userInfo = ret;
                        Config.getInstance().set(Config.USERNAME,
                                mobile);
                        Config.getInstance().set(Config.PASSWORD,
                                password);
                    }
                    listener.registerSuccess(true);
                }else{
                    listener.registerFailed(response.message);
                }
            }
        });
    }

    @Override
    public void registerWithThird(RequestPacket.ThirdLogin thirdLogin, String mobile, String nickname, String password, final RegisterListener listener) {
        RequestPacket.RegisterWithThird cmd =new RequestPacket.RegisterWithThird();
        cmd.mobile = mobile;
        cmd.password = password;
        cmd.thirdType = thirdLogin.type;
        cmd.thirdUUID = thirdLogin.uuid;
        cmd.thirdInfos = thirdLogin.info;
        cmd.thirdInfos.put("name", nickname);
        cmd.thirdInfos.put("realname", nickname);
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
            @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                if(response.code ==0){
                    listener.registerSuccess(true);
                }else{
                    listener.registerFailed(response.message);
                }
            }
        });
    }
}
