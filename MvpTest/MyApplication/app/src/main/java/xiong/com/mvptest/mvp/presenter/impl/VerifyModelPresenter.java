package xiong.com.mvptest.mvp.presenter.impl;

import xiong.com.mvptest.mvp.model.IVerifyMobile;
import xiong.com.mvptest.mvp.model.impl.VerifyModel;
import xiong.com.mvptest.mvp.view.impl.IVerifyView;
import xiong.com.mvptest.util.ToastUtil;

/**
 * Created by 62416 on 2016/10/28.
 */

public class VerifyModelPresenter {
    private VerifyModelPresenter presenter;
    private VerifyModel model;
    private IVerifyView iView;
    public VerifyModelPresenter(IVerifyView iView){
        this.iView =iView;
        model =new VerifyModel();
    }
    public void VerifyMobile(){
        model.verifyMobile(iView.getMobile(), new IVerifyMobile.VerifyMobileListener() {
            @Override
            public void success(int code,String message) {
                if(code==0){
                    ToastUtil.showToast("该号码还未注册,请先注册");
                }else if(code ==-2){
                    iView.sendVerificationCode(iView.getMobile());
                }else{
                    ToastUtil.showToast("出现异常");
                }
            }

            @Override
            public void failed() {
            }
        });
    }
    public void VerifyCode(){
        model.verifyCode(iView.getMobile(), iView.getCode(), new IVerifyMobile.VerifyCodeListener() {
            @Override
            public void verifyCodeSuccess() {
                iView.toUpdatepass(iView.getMobile());
            }

            @Override
            public void verifyCodeFailed(String message) {
                iView.verifyCodeFailed(message);
            }
        });
    }
}
