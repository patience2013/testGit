package xiong.com.mvptest.mvp.presenter.impl;

import android.content.Context;
import android.util.Log;

import xiong.com.mvptest.bean.User;
import xiong.com.mvptest.mvp.model.impl.OnLoginListener;
import xiong.com.mvptest.mvp.model.impl.UserLoginModel;
import xiong.com.mvptest.mvp.view.impl.ILoginview;

/**
 * Created by 62416 on 2016/10/12.
 */

public class UserLoginPresenter {
    private static UserLoginPresenter mUserpresenter;
    private UserLoginModel mUsermodel;
    private ILoginview mUserView;
    private Context mContext;
    public UserLoginPresenter(ILoginview view){
        this.mUserView= view;
        this.mUsermodel=new UserLoginModel();
    }

    public void login() {
//        mUserView.showProgress();
        mUsermodel.login(mUserView.getUsername(), mUserView.getUserpass(), new OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                Log.i("response", "loginSuccess: "+user.toString());
                mUserView.toMainActivity(user);
                mUserView.hideProgress();
            }

            @Override
            public void loginFailed(String msg) {
                mUserView.toFailed(msg);
                mUserView.hideProgress();
            }

        });
    }
}
