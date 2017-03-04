package xiong.com.mvptest.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MadeUtil {
	

	/**
	 * 设置状态栏图标为深色和魅族特定的文字风格，Flyme4.0以上
	 * 可以用来判断是否为Flyme用户
	 * @param window 需要设置的窗口
	 * @param dark 是否把状态栏字体及图标颜色设置为深色
	 * @return  boolean 成功执行返回true
	 *
	 */
	public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
		boolean result = false;
		if (window != null) {
			try {
				WindowManager.LayoutParams lp = window.getAttributes();
				Field darkFlag = WindowManager.LayoutParams.class
						.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
				Field meizuFlags = WindowManager.LayoutParams.class
						.getDeclaredField("meizuFlags");
				darkFlag.setAccessible(true);
				meizuFlags.setAccessible(true);
				int bit = darkFlag.getInt(null);
				int value = meizuFlags.getInt(lp);
				if (dark) {
					value |= bit;
				} else {
					value &= ~bit;
				}
				meizuFlags.setInt(lp, value);
				window.setAttributes(lp);
				result = true;
			} catch (Exception e) {

			}
		}
		return result;
	}

	/**
	 * 设置状态栏字体图标为深色，需要MIUIV6以上
	 * @param window 需要设置的窗口
	 * @param dark 是否把状态栏字体及图标颜色设置为深色
	 * @return  boolean 成功执行返回true
	 *
	 */
	public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
		boolean result = false;
		if (window != null) {
			Class clazz = window.getClass();
			try {
				int darkModeFlag = 0;
				Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
				Field  field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
				darkModeFlag = field.getInt(layoutParams);
				Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
				if(dark){
					extraFlagField.invoke(window,darkModeFlag,darkModeFlag);//状态栏透明且黑色字体
				}else{
					extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
				}
				result=true;
			}catch (Exception e){

			}
		}
		return result;
	}
	public static void setTranslucentStatus(Activity context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window win = context.getWindow();
			WindowManager.LayoutParams winParams = win.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			winParams.flags |= bits;
			win.setAttributes(winParams);
		}
		SystemStatusManager tintManager = new SystemStatusManager(context);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(0);// 状态栏无背景
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	// 解决状态栏适配的问题
	public static void solveScreenStateAdpater(LinearLayout line_state,
			Context context) {
		if (line_state == null || context == null)
			return;
		try {
			float version = mobileVersion();
			if (version > 4.4) {
				LayoutParams layoutParams = line_state.getLayoutParams();
				layoutParams.height = dip2px(context, 17);
			} else {
				LayoutParams layoutParams = line_state.getLayoutParams();
				layoutParams.height = dip2px(context, 0);
				line_state.setLayoutParams(layoutParams);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// 解决我那个界面的状态了一体化
	public static void solveScreenStateAdpaterMine(LinearLayout line_state,
			Context context) {

		try {
			float version = mobileVersion();
			if (version > 4.4) {
				LayoutParams layoutParams = line_state.getLayoutParams();
				layoutParams.height = dip2px(context, 17);
			} else {
				LayoutParams layoutParams = line_state.getLayoutParams();
				layoutParams.height = dip2px(context, 0);
				line_state.setLayoutParams(layoutParams);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// 获得手机的版本号
	public static float mobileVersion() {
		try {
			String mobileVersion = Build.VERSION.RELEASE;
			StringBuffer sbVersion = new StringBuffer();
			for (int i = 0; i < mobileVersion.length(); i++) {
				char d = mobileVersion.charAt(i);
				if (Character.isDigit(d)) {
					sbVersion.append(d);
				}
				if (i == 0) {
					sbVersion.append(".");
				}
			}
			return Float.parseFloat(sbVersion.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
