package xiong.com.mvptest.mvp.presenter.impl;

import android.content.Intent;

import xiong.com.mvptest.baseclass.BaseActivity;
import xiong.com.mvptest.bean.RequestPacket;
import xiong.com.mvptest.mvp.model.IRegisterFinishModel;
import xiong.com.mvptest.mvp.model.impl.RegisterFinishModel;
import xiong.com.mvptest.mvp.view.impl.IRegisterFinishView;

/**
 * Created by lenovo on 2016/11/17.
 */

public class RegisterFinishModelPresenter {
    private RegisterFinishModel model;
    private IRegisterFinishView view;
    private RequestPacket.ThirdLogin mThirdLogin;
    public RegisterFinishModelPresenter(IRegisterFinishView view, RequestPacket.ThirdLogin mThirdLogin){
        this.view =view;
        this.mThirdLogin =mThirdLogin;
        model =new RegisterFinishModel();
    }
    public void register(){
        if(mThirdLogin!=null){
            model.registerWithThird(mThirdLogin, view.getMobile(), view.getNickName(), view.getPassword(), new IRegisterFinishModel.RegisterListener() {
                @Override
                public void registerSuccess(Boolean verification) {
                    view.toMainActivity();
                }

                @Override
                public void registerFailed(String message) {
                    view.showFailToast(message);
                }
            });
        }else{
            model.register(view.getMobile(), view.getNickName(), view.getPassword(), new IRegisterFinishModel.RegisterListener() {
                @Override
                public void registerSuccess(Boolean verification) {
                    view.toMainActivity();
                }

                @Override
                public void registerFailed(String message) {
                    view.showFailToast(message);
                }
            });
        }
    }
}
