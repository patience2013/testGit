package xiong.com.mvptest.mvp.model;

import xiong.com.mvptest.bean.RequestPacket;

/**
 * Created by lenovo on 2016/11/17.
 */

public interface IRegisterFinishModel {
    void register(String mobile ,String nickname,String password,RegisterListener listener);
    interface RegisterListener{
        void registerSuccess(Boolean verification);
        void registerFailed(String message);
    }
    void registerWithThird(RequestPacket.ThirdLogin thirdLogin, String mobile, String nickname, String password,RegisterListener listener);
}
