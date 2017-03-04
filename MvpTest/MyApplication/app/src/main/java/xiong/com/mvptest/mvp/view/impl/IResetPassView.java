package xiong.com.mvptest.mvp.view.impl;

/**
 * Created by 62416 on 2016/11/1.
 */

public interface IResetPassView {
    String getMobile();
    String getPass();
    void toLoginActivity();
    void showFailedToast();
}
