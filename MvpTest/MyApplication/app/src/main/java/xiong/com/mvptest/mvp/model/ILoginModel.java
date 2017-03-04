package xiong.com.mvptest.mvp.model;


import xiong.com.mvptest.mvp.model.impl.OnLoginListener;

/**
 * Created by 62416 on 2016/10/12.
 */

public interface ILoginModel {
    void login(String username, String userpass, OnLoginListener loginListener);
}
