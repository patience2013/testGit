package xiong.com.mvptest.mvp.model;

import java.io.File;

import xiong.com.mvptest.mvp.model.impl.UploadListener;

/**
 * Created by 62416 on 2016/10/19.
 */

public interface IUploadImg {
    void UploadImg(String url, File file,String fileName, UploadListener uploadListener);
}
