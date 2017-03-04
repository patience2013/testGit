package xiong.com.mvptest.mvp.view.impl;

/**
 * Created by 62416 on 2016/11/10.
 */

public interface IRegisterView {
    String getMobile();
    String getCode();
    void sendVerificationCode(String mobile);
    void showToast(String msg);
    void verifyCodeFailed(String msg);
    void toRegisterFinish(String mobile);
    void toMainActivity();
    void BindThird();
    void showAgreement(String content);
}
