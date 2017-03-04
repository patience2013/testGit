package xiong.com.mvptest.mvp.presenter.impl;

import xiong.com.mvptest.mvp.model.IResetPass;
import xiong.com.mvptest.mvp.model.impl.ResetPassModel;
import xiong.com.mvptest.mvp.view.impl.IResetPassView;

/**
 * Created by 62416 on 2016/11/1.
 */

public class ResetPassPresenter{
    public ResetPassModel model;
    public IResetPassView iview;
    public ResetPassPresenter(IResetPassView iview){
        this.iview =iview;
        model=new ResetPassModel();
    }
    public void resetPasss(){
        model.resetPass(iview.getMobile(), iview.getPass(), new IResetPass.ResetPassListener() {
            @Override
            public void resetSuccess() {
                iview.toLoginActivity();
            }

            @Override
            public void resetFailed() {
                iview.showFailedToast();
            }
        });
    }
}
