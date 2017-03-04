package xiong.com.mvptest.mvp.presenter.impl;

import android.content.Context;
import android.widget.Toast;

import java.io.File;

import xiong.com.mvptest.mvp.model.impl.UploadListener;
import xiong.com.mvptest.mvp.model.impl.UploadModel;
import xiong.com.mvptest.mvp.view.impl.ILoginview;

/**
 * Created by 62416 on 2016/10/20.
 */

public class UploadPresenter {
    private UploadListener uploadListener;
    private UploadModel uploadModel;
    private ILoginview iLoginview;
    private Context mContext;
    public UploadPresenter(ILoginview view,Context context){
        mContext =context;
        iLoginview =view;
        uploadModel =new UploadModel();
    }
    public void UploadImg(File file,String url){
        uploadModel.UploadImg(url, file, iLoginview.getFilename(), new UploadListener() {
            @Override
            public void UploadSuccess() {
                Toast.makeText(mContext, "上传成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void UploadFailed() {
                Toast.makeText(mContext, "上传失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
