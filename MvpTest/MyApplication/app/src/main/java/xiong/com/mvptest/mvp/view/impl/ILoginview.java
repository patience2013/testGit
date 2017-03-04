package xiong.com.mvptest.mvp.view.impl;


import xiong.com.mvptest.bean.User;

/**
 * Created by 62416 on 2016/10/12.
 */

public interface ILoginview {
    String getUsername();
    String getUserpass();
    void showProgress();
    void hideProgress();
    void clearUsername();
    void clearUserpass();
    void toMainActivity(User user);
    void toFailed(String msg);
    String getFilename();
}
