package xiong.com.mvptest.mvp.view.impl;

/**
 * Created by 62416 on 2016/10/28.
 */

public interface IVerifyView {
    String getMobile();
    String getCode();
    void toUpdatepass(String mobile);
    void sendVerificationCode(String mobile);
    void showToast(String msg);
    void verifyCodeFailed(String msg);
}
