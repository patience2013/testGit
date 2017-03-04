package xiong.com.mvptest.mvp.model;

/**
 * Created by 62416 on 2016/11/1.
 */

public interface IResetPass {
    void resetPass(String mobile,String newPass,ResetPassListener updatePassListener);
    interface ResetPassListener{
        void resetSuccess();
        void resetFailed();
    }
}
