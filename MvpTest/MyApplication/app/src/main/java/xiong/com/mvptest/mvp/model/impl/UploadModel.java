package xiong.com.mvptest.mvp.model.impl;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;
import xiong.com.mvptest.mvp.model.IUploadImg;

/**
 * Created by 62416 on 2016/10/19.
 */

public class UploadModel implements IUploadImg {
    @Override
    public void UploadImg(String url, File file,String fileName, UploadListener uploadListener) {
        OkHttpUtils.post().addFile("Img",fileName,file).url(url).build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                return null;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.i("TAG", "onError: "+e.toString());
            }

            @Override
            public void onResponse(Object response, int id) {
                Log.i("TAG", "onResponse: "+response.toString());
            }

            @Override
            public void inProgress(float progress, long total, int id) {
                super.inProgress(progress, total, id);
            }
        });
    }
}
