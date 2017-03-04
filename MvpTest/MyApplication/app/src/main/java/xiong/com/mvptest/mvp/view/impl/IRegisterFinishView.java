package xiong.com.mvptest.mvp.view.impl;

/**
 * Created by lenovo on 2016/11/17.
 */

public interface IRegisterFinishView {
    String getNickName();
    String getPassword();
    String getMobile();
    void toMainActivity();
    void showFailToast(String message);
}
