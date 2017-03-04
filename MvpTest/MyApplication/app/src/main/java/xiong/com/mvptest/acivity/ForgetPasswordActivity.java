package xiong.com.mvptest.acivity;

import android.content.Intent;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;

import xiong.com.mvptest.Global;
import xiong.com.mvptest.R;
import xiong.com.mvptest.baseclass.BaseActivity;
import xiong.com.mvptest.bean.RequestPacket;
import xiong.com.mvptest.http.HttpNetwork;
import xiong.com.mvptest.http.HttpRequestPacket;
import xiong.com.mvptest.http.HttpResponsePacket;
import xiong.com.mvptest.mvp.model.impl.VerifyModel;
import xiong.com.mvptest.mvp.presenter.impl.VerifyModelPresenter;
import xiong.com.mvptest.mvp.view.impl.IVerifyView;
import xiong.com.mvptest.util.Config;
import xiong.com.mvptest.util.StringUtil;
import xiong.com.mvptest.util.ToastUtil;
import xiong.com.mvptest.util.UIHandlerUtil;

public class ForgetPasswordActivity extends BaseActivity implements IVerifyView,OnClickListener {
	EditText editMobile;
	Button btn_get_code;
	Button btn_next;
	EditText edt_forget_code;
	private int sendCodeSuccess = 0; // 0代表没有发送 1代表发送成功 2代表发送
	String mobile;
	View v_mobile, v_codes;
	ImageView iv_display_code;
	TextView tv_display_mobile;
	//private static int downTimer;
	//CountDownTimer countTimer;
    public VerifyModel model;
    private VerifyModelPresenter presenter =new VerifyModelPresenter(this);


	@Override
	protected void initView() {
		setContentView(R.layout.activity_forget_password);
		setTitle("忘记密码");
		tv_display_mobile = (TextView) findViewById(R.id.tv_display_mobile);
		iv_display_code = (ImageView) findViewById(R.id.iv_display_code);
		v_mobile = (View) findViewById(R.id.v_mobile);
		v_codes = (View) findViewById(R.id.v_codes);
		btn_get_code = (Button) findViewById(R.id.btn_get_code);
		btn_next = (Button) findViewById(R.id.btn_next);
		edt_forget_code = (EditText) findViewById(R.id.edt_forget_code);
		handlerEdtDisplay();
		// 处理进入界面重新开始倒计时的方法
		//handlerContinueTimer();

	}

	// 处理输入框的显示效果的方法
	public void handlerEdtDisplay() {
		editMobile = (EditText) findViewById(R.id.register_mobile_edit);
		editMobile.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
				String content = s.toString();
				if ("".equals(content)) {
					btn_next.setAlpha(0.4f);
					btn_next.setEnabled(false);
				} else {
					boolean haveContentToPassword = !edt_forget_code.getText()
							.toString().equals("");
					if (haveContentToPassword) {
						btn_next.setAlpha(1f);
						btn_next.setEnabled(true);
					} else {
						btn_next.setAlpha(0.4f);
						btn_next.setEnabled(false);
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
										  int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		edt_forget_code.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
				String content = s.toString();
				if ("".equals(content)) {
					btn_next.setAlpha(0.4f);
					btn_next.setEnabled(false);
				} else {
					boolean haveContentToUser = !editMobile.getText()
							.toString().equals("");
					if (haveContentToUser) {
						btn_next.setAlpha(1f);
						btn_next.setEnabled(true);
					} else {
						btn_next.setAlpha(0.4f);
						btn_next.setEnabled(false);
					}
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
										  int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		editMobile.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					editMobile.setAlpha(1);
					editMobile.setHintTextColor(getResources().getColor(
							R.color.login_input_focus_size));
					tv_display_mobile.setAlpha(1);
					v_mobile.setAlpha(1);
				} else {

					editMobile.setAlpha(0.4f);
					editMobile.setHintTextColor(getResources().getColor(
							R.color.login_input_nofocus_size));
					tv_display_mobile.setAlpha(0.4f);
					v_mobile.setAlpha(0.4f);

				}
			}
		});
		edt_forget_code.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					edt_forget_code.setAlpha(1);
					edt_forget_code.setHintTextColor(getResources().getColor(
							R.color.login_input_focus_size));
					iv_display_code.setAlpha(1f);
					v_codes.setAlpha(1);
				} else {
					edt_forget_code.setAlpha(0.4f);
					edt_forget_code.setHintTextColor(getResources().getColor(
							R.color.login_input_nofocus_size));
					iv_display_code.setAlpha(0.4f);
					v_codes.setAlpha(0.4f);
				}
			}
		});
	}

	private Timer mTimer;
	// 重发倒计时
	private void startCount() {
		btn_get_code.setEnabled(false);
		if (mTimer != null) {
			mTimer.cancel();
		}
		mTimer = new Timer();
		mTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				final int pass = (int) (System.currentTimeMillis() / 1000)
						- Config.getInstance().getInt(Config.LAST_SEND_CODE, 0);
				//downTimer = Global.AuthSendTime - pass;
				if (Global.AuthSendTime - pass < 0) {
					UIHandlerUtil.sendEmptyMessage(0, new Callback() {

						@Override
						public boolean handleMessage(Message msg) {
							btn_get_code.setText("重发");
							btn_get_code.setEnabled(true);
							sendCodeSuccess = 0;
							return false;
						}
					});
				} else {

					UIHandlerUtil.sendEmptyMessage(0, new Callback() {

						@Override
						public boolean handleMessage(Message msg) {
							btn_get_code.setText((Global.AuthSendTime - pass)
									+ "秒");
							return false;
						}
					});
				}
			}
		}, 1000, 1000);
	}
    @Override
    public String getMobile() {
        return editMobile.getText().toString();
    }

	@Override
	public String getCode() {
		return edt_forget_code.getText().toString();
	}

	@Override
    public void toUpdatepass(String mobile) {
		Intent intent =new Intent(ForgetPasswordActivity.this,SetPasswordActivity.class);
		intent.putExtra("mobile", mobile);
		startActivity(intent);
    }

    @Override
    public void sendVerificationCode(String mobile) {
        int last = Config.getInstance().getInt(Config.LAST_SEND_CODE, 0);
        int now = (int) (System.currentTimeMillis() / 1000);
        if (now - last < Global.AuthSendTime)
            return;

        Config.getInstance().setInt(Config.LAST_SEND_CODE, now);
        startCount();
        RequestPacket.GetVerificationCode cmd =new RequestPacket.GetVerificationCode(mobile);
        HttpNetwork.getInstance().request(cmd, new HttpNetwork.NetResponseCallback() {
            @Override
            public void onResponse(HttpRequestPacket request, HttpResponsePacket response) {
                if (response.code == 0) {
                    sendCodeSuccess = 1;
                } else {
                    sendCodeSuccess = 2;
                }
                ToastUtil.showToast(response.message);
            }
        },null);
    }

    @Override
    public void showToast(String msg) {

    }

	@Override
	public void verifyCodeFailed(String msg) {
		ToastUtil.showToast(msg);
	}

	@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get_code:
                mobile = editMobile.getText().toString();
                if (StringUtil.isMobile(mobile)) {
                    presenter.VerifyMobile();
//                    checkMobile(mobile);
                } else {
                    ToastUtil.showToast("请填写正确的手机号码！");
                }
                break;
            case R.id.btn_next:
				String code = edt_forget_code.getText().toString();
				mobile = editMobile.getText().toString();
				if (TextUtils.isEmpty(code)) {
					ToastUtil.showToast("请先填写验证码");
					return;
				}
				if (sendCodeSuccess == 0) {
					ToastUtil.showToast("还没有发送验证码");
					return;
				}

                presenter.VerifyCode();
        }
    }
}
