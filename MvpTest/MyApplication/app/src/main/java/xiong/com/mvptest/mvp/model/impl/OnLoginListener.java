package xiong.com.mvptest.mvp.model.impl;

import xiong.com.mvptest.bean.User;

/**
 * Created by 62416 on 2016/10/15.
 */

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed(String msg);
}
