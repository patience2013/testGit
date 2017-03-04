package xiong.com.mvptest.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import xiong.com.mvptest.App;
import xiong.com.mvptest.R;
import xiong.com.mvptest.baseclass.BaseActivity;

public class ToastUtil {

	/**
	 * 短时间Toast提醒
	 * 
	 * @param msg
	 */
	public static void showToast(String msg) {
		if (TextUtils.isEmpty(msg)) {
			return;
		}
		// 得到栈顶的activity（也就是 当前的显示界面）
		Context context = BaseActivity.topActivity();
		if (context == null) {
			context = App.getInstance().getBaseContext();
		}
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 长时间Toast提醒
	 * 
	 * @param msg
	 */
	public static void showToastLong(String msg) {
		if (TextUtils.isEmpty(msg)) {
			return;
		}
		Context context = BaseActivity.topActivity();
		if (context == null) {
			context = App.getInstance().getBaseContext();
		}
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	public static void showDailogTip(String title, String message) {
		Context context = BaseActivity.topActivity();
		if (context == null) {
			return;
		}
		final Dialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		alertDialog.getWindow().setContentView(R.layout.alert_dialog_tip);
		TextView messageTextView = (TextView) alertDialog.getWindow()
				.findViewById(R.id.alert_dialog_tip_content);
		messageTextView.setText(message);
		TextView titleView = (TextView) alertDialog.getWindow().findViewById(
				R.id.alert_dialog_tip_title);
		titleView.setText(title);
	}

	public static Dialog showLoadingDialog(Context context, String msg) {
		return showLoadDialog(context, msg, true);
	}

	// 加载的dialog
	public static Dialog showLoadDialog(Context context, String msg,
										boolean cancelable) {
		if (context == null)
			return null;
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		// main.xml中的ImageView
		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
		TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
		// 加载动画
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
				context, R.anim.loading_anim);
		// 使用ImageView显示动画
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		tipTextView.setText(msg);// 设置加载信息

		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

		loadingDialog.setCancelable(cancelable);// 不可以用“返回键”取消
		loadingDialog.setCanceledOnTouchOutside(false);
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
		loadingDialog.show();
		return loadingDialog;
	}

	/**
	 * 显示进度条
	 * 
	 * @param context
	 * @param title
	 * @return
	 */
	public static ProgressDialog showProgressDialog(Context context,
													String title) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle(title);
		dialog.setCancelable(false);
		dialog.setIndeterminate(false);
		dialog.setIcon(R.mipmap.ic_launcher);
		dialog.setMax(100);
		dialog.setMax(0);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.show();
		return dialog;
	}

	public static void closeDialog(Dialog dialog) {
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}

//	public static Dialog showAlertDialog(Context context, String title,
//										 String message, final OnClickListener okListener) {
//		final Dialog alertDialog = new AlertDialog.Builder(context).create();
//		alertDialog.show();
//		alertDialog.getWindow().setContentView(R.layout.alert_dialog_ok_cancel);
//		TextView messageTextView = (TextView) alertDialog.getWindow()
//				.findViewById(R.id.alert_dialog_content);
//		messageTextView.setText(message);
//		TextView titleView = (TextView) alertDialog.getWindow().findViewById(
//				R.id.alert_dialog_title);
//		titleView.setText(title);
//
//		alertDialog.getWindow().findViewById(R.id.alert_dialog_ok)
//				.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View arg0) {
//						if (okListener != null) {
//							okListener.onClick(arg0);
//						}
//						alertDialog.dismiss();
//					}
//				});
//
//		alertDialog.getWindow().findViewById(R.id.alert_dialog_cancel)
//				.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View arg0) {
//						alertDialog.dismiss();
//						if (AuctionRoom2.getInstance() != null) {
//							if (AuctionRoom2.getInstance().popupSend_price_dialog != null
//									&& AuctionRoom2.getInstance().popupSend_price_dialog
//											.isShowing()) {
//								AuctionRoom2.getInstance().popupSend_price_dialog
//										.dismiss();
//							}
//						}
//					}
//				});
//		alertDialog.getWindow().setWindowAnimations(R.style.dialogAnimation);
//		return alertDialog;
//	}
}
