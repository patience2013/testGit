package xiong.com.mvptest.http;


import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by 62416 on 2016/10/28.
 */

public abstract class ResponseCallback extends Callback<HttpResponsePacket> {
    @Override
    public HttpResponsePacket parseNetworkResponse(Response response, int id) throws Exception {

        String string =response.body().toString();
        HttpResponsePacket packet =new Gson().fromJson(string,HttpResponsePacket.class);
        return packet;
    }
}
