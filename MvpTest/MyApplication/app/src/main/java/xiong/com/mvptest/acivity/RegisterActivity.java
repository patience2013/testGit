package xiong.com.mvptest.acivity;

import android.content.Intent;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
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
import xiong.com.mvptest.Main2Activity;
import xiong.com.mvptest.R;
import xiong.com.mvptest.baseclass.BaseActivity;
import xiong.com.mvptest.bean.HttpStruct;
import xiong.com.mvptest.bean.RequestPacket;
import xiong.com.mvptest.http.HttpNetwork;
import xiong.com.mvptest.http.HttpRequestPacket;
import xiong.com.mvptest.http.HttpResponsePacket;
import xiong.com.mvptest.mvp.presenter.impl.RegisterModelPresenter;
import xiong.com.mvptest.mvp.view.impl.IRegisterView;
import xiong.com.mvptest.util.Config;
import xiong.com.mvptest.util.StringUtil;
import xiong.com.mvptest.util.ToastUtil;
import xiong.com.mvptest.util.UIHandlerUtil;

public class RegisterActivity extends BaseActivity implements IRegisterView,OnClickListener{
	EditText mMobileEdit;
	RequestPacket.ThirdLogin mThirdLogin = null;
	private Button btn_get_code;
	private Button btn_next;
	// ////////////////////////////////////////////
	EditText mCodeEdit;
	String mobile;
	boolean registered;
	private int sendCodeSuccess = 0; // 0代表没有发送 1代表发送成功 2代表发送
	TextView mProtocolText;
	TextView tv_display_mobile;
	View v_mobile, v_codes;
	ImageView iv_display_code;
	private RegisterModelPresenter mPresenter =new RegisterModelPresenter(this,mThirdLogin);

	@Override
	protected void initView() {
		setContentView(R.layout.activity_register);
		mThirdLogin = (RequestPacket.ThirdLogin) getIntent().getSerializableExtra(
				"ThirdLogin");

		if (mThirdLogin != null) {
			setTitle("绑定手机号码");
		} else {
			setTitle("注册");
		}
		tv_display_mobile = (TextView) findViewById(R.id.tv_display_mobile);
		iv_display_code = (ImageView) findViewById(R.id.iv_display_code);
		v_mobile = findViewById(R.id.v_mobile);
		v_codes = findViewById(R.id.v_mobile);
		mMobileEdit = (EditText) findViewById(R.id.register_mobile_edit);
		btn_get_code = (Button) findViewById(R.id.btn_get_code);
		btn_next = (Button) findViewById(R.id.btn_next);
		mCodeEdit = (EditText) findViewById(R.id.reigster_verify_code);
		mProtocolText = (TextView) findViewById(R.id.register_protocol_text);
		ClickableSpan clickSpan = new ClickableSpan() {

			@Override
			public void onClick(View widget) {
				mPresenter.ShowAgreement();
			}
		};
		SpannableString span = new SpannableString(
				"注册即表示您同意艺易拍用户协议。违反协议的用户可能会被限制使用，请查看协议并约束行为。");
		span.setSpan(new ForegroundColorSpan(0x369BDD), 8, 15,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		span.setSpan(clickSpan, 8, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		mProtocolText.setMovementMethod(LinkMovementMethod.getInstance());
		mProtocolText.setText(span);
		handlerEdtDisplay();
	}

	// 处理输入框的显示效果的方法
	public void handlerEdtDisplay() {
		mMobileEdit = (EditText) findViewById(R.id.register_mobile_edit);
		mMobileEdit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
				String content = s.toString();
				if ("".equals(content)) {
					btn_next.setAlpha(0.4f);
					btn_next.setEnabled(false);
				} else {
					boolean haveContentToPassword = !mCodeEdit.getText()
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
		mCodeEdit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
				String content = s.toString();
				if ("".equals(content)) {
					btn_next.setAlpha(0.4f);
					btn_next.setEnabled(false);
				} else {
					boolean haveContentToUser = !mMobileEdit.getText()
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
		mMobileEdit.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					mMobileEdit.setAlpha(1);
					mMobileEdit.setHintTextColor(getResources().getColor(
							R.color.login_input_focus_size));
					tv_display_mobile.setAlpha(1);
					v_mobile.setAlpha(1);
				} else {

					mMobileEdit.setAlpha(0.4f);
					mMobileEdit.setHintTextColor(getResources().getColor(
							R.color.login_input_nofocus_size));
					tv_display_mobile.setAlpha(0.4f);
					v_mobile.setAlpha(0.4f);

				}
			}
		});
		mCodeEdit.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					mCodeEdit.setAlpha(1);
					mCodeEdit.setHintTextColor(getResources().getColor(
							R.color.login_input_focus_size));
					iv_display_code.setAlpha(1f);
					v_codes.setAlpha(1);
				} else {
					mCodeEdit.setAlpha(0.4f);
					mCodeEdit.setHintTextColor(getResources().getColor(
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
	public void onClick(View v) {
		String mobile = mMobileEdit.getText().toString();
		switch (v.getId()){
			case R.id.btn_get_code:
				if (StringUtil.isMobile(mobile)) {
					mPresenter.VerifyMobile();
				} else {
					ToastUtil.showToast("请填写正确的手机号码！");
				}
			break;
			case R.id.btn_next:
				String code = mCodeEdit.getText().toString();
				if (TextUtils.isEmpty(code)) {
					ToastUtil.showToast("请先填写验证码");
					return;
				}
				if (sendCodeSuccess == 0) {
					ToastUtil.showToast("还没有发送验证码");
				} else if (sendCodeSuccess == 1) {
					ToastUtil.showToast("已发送成功，准备验证");
					mPresenter.VerifyCode();
				} else {
					ToastUtil.showToast("验证码发送失败,请重新发送验证码");
				}
				break;
		}
	}

	@Override
	public String getMobile() {
		return mMobileEdit.getText().toString();
	}

	@Override
	public String getCode() {
		return mCodeEdit.getText().toString();
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
		ToastUtil.showToast(msg);
	}

	@Override
	public void verifyCodeFailed(String msg) {
		ToastUtil.showToast(msg);
	}

	@Override
	public void toRegisterFinish(String mobile) {
		Intent intent = new Intent(
				RegisterActivity.this,
				RegisterFinishActivity.class);
		intent.putExtra("mobile",
				mobile);
		if (mThirdLogin != null) {
			intent.putExtra("ThirdLogin", mThirdLogin);
		}
		startActivity(intent);
		finish();
	}

	@Override
	public void toMainActivity() {
		Intent intent = new Intent(RegisterActivity.this,
				Main2Activity.class);
		startActivity(intent);
	}

	@Override
	public void BindThird() {
		final RequestPacket.BindWithThird bind = new RequestPacket.BindWithThird();
		bind.mobile = mobile;
		bind.thirdUUID = mThirdLogin.uuid;
		bind.thirdType = mThirdLogin.type;
		HttpNetwork.getInstance().request(bind, new HttpNetwork.NetResponseCallback() {

			@Override
			public void onResponse(HttpRequestPacket request,
								   HttpResponsePacket response) {
				// 注册成功 跳转到主页
				HttpStruct.UserInfo ret = response.getData(request.getResponseType());
				if (ret != null) {
					Global.uid = String.valueOf(ret.id);
					Global.userInfo = ret;
					Config.getInstance().set(Config.UUID, bind.thirdUUID);
					Config.getInstance().setInt(Config.UUID_TYPE,
							bind.thirdType);
					Config.getInstance().setBoolean(Config.THIRD_LOGIN, true);
					Intent intent = new Intent(RegisterActivity.this,
							Main2Activity.class);
					startActivity(intent);
				}
				BaseActivity.finishAll();
			}
		});
	}

	@Override
	public void showAgreement(String content) {
		TextView tv = (TextView) findViewById(R.id.register_protocol_content);
		tv.setAlpha(0);

		tv.setText(Html.fromHtml(content));
		tv.animate().alpha(1).setDuration(1000).start();
	}
}
