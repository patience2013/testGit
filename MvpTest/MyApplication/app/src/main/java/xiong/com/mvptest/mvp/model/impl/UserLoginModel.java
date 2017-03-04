package xiong.com.mvptest.mvp.model.impl;

import android.util.Log;

import xiong.com.mvptest.bean.RequestPacket;
import xiong.com.mvptest.bean.User;
import xiong.com.mvptest.http.HttpNetwork;
import xiong.com.mvptest.http.HttpRequestPacket;
import xiong.com.mvptest.http.HttpResponsePacket;
import xiong.com.mvptest.mvp.model.ILoginModel;

/**
 * Created by 62416 on 2016/10/12.
 */

public class UserLoginModel implements ILoginModel {
    @Override
    public void login(String username, String userpass, final OnLoginListener loginListener) {
        RequestPacket.LoginRequest cmd =new RequestPacket.LoginRequest();
        cmd.account =username;
        cmd.password =userpass;
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
                @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                Log.i("OnResponse", response.toString());
                if(response.code ==0){
                    User userInfo =response.getData(User.class);
                    loginListener.loginSuccess(userInfo);
                }else{
                    loginListener.loginFailed(response.message);
                }
            }
        }, new HttpNetwork.NetErrorCallback() {
            @Override
            public void onError(HttpRequestPacket request, String errorMsg) {

            }
        });
//        OkHttpUtils.postString().url(url).content(new Gson().toJson(new RequestPacket.LoginRequest(username,userpass))).mediaType(MediaType.parse("application/json; charset=utf-8"))
//               .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//
//                try {
//                    JSONObject obj =new JSONObject(response);
//                    int code =obj.getInt("code");
//                    String msg =obj.getString("message");
//                    Log.i("回调", "onResponse: "+response.toString());
//                    if(code==0){
//                        String userString =obj.getString("data");
//                        Gson gson =new Gson();
//                        User user =gson.fromJson(userString, User.class);
//                        loginListener.loginSuccess(user);
//                    }else{
//                        loginListener.loginFailed(msg);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }
}
