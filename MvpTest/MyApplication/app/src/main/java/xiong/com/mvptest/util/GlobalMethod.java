package xiong.com.mvptest.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class GlobalMethod {
    public static boolean isLogin() {

        String password = Config.getInstance().get(Config.PASSWORD, "");
        boolean thirdLogin = Config.getInstance().getBoolean(
                Config.THIRD_LOGIN, false);
        if (password.equals("") && thirdLogin == false) {
            return false;
        } else {
            return true;
        }

    }

    public static int[] getScreenSize(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int[] screenSize = {width, height};
        return screenSize;

    }
}
