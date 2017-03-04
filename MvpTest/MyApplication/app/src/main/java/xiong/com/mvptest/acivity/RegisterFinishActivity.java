package xiong.com.mvptest.acivity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;

import xiong.com.mvptest.Main2Activity;
import xiong.com.mvptest.R;
import xiong.com.mvptest.baseclass.BaseActivity;
import xiong.com.mvptest.bean.RequestPacket;
import xiong.com.mvptest.mvp.presenter.impl.RegisterFinishModelPresenter;
import xiong.com.mvptest.mvp.view.impl.IRegisterFinishView;
import xiong.com.mvptest.util.ToastUtil;

public class RegisterFinishActivity extends BaseActivity implements IRegisterFinishView,OnClickListener {
	EditText mNameEdit;
	EditText mPassEdit;
	String mobile;
	RequestPacket.ThirdLogin mThirdLogin;
	Button btn_ok;
	View v_nick, v_pass;
	private RegisterFinishModelPresenter presenter;

	@Override
	protected void initView() {
		setContentView(R.layout.register_finish);

		this.mobile = getIntent().getStringExtra("mobile");
		mThirdLogin = (RequestPacket.ThirdLogin) getIntent().getSerializableExtra(
				"ThirdLogin");
		presenter=new RegisterFinishModelPresenter(this,mThirdLogin);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		v_nick = findViewById(R.id.v_nick);
		v_pass = findViewById(R.id.v_pass);

		mNameEdit = (EditText) findViewById(R.id.register_finish_name);
		mPassEdit = (EditText) findViewById(R.id.register_finish_pass);
		if (mThirdLogin != null) {
			setTitle("绑定手机号");
			String name = mThirdLogin.info.get("name");
			mNameEdit.setText(name);
		} else {
			setTitle("注册");
		}
		handlerEdtDisplay();
	}

	// 处理输入框的显示效果的方法
	public void handlerEdtDisplay() {
		mNameEdit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
				String content = s.toString();
				if ("".equals(content)) {
					btn_ok.setAlpha(0.4f);
					btn_ok.setEnabled(false);
				} else {
					boolean haveContentToPassword = !mPassEdit.getText()
							.toString().equals("");
					if (haveContentToPassword) {
						btn_ok.setAlpha(1f);
						btn_ok.setEnabled(true);
					} else {
						btn_ok.setAlpha(0.4f);
						btn_ok.setEnabled(false);
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
		mPassEdit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
				String content = s.toString();
				if ("".equals(content)) {
					btn_ok.setAlpha(0.4f);
					btn_ok.setEnabled(false);
				} else {
					boolean haveContentToUser = !mNameEdit.getText().toString()
							.equals("");
					if (haveContentToUser) {
						btn_ok.setAlpha(1f);
						btn_ok.setEnabled(true);
					} else {
						btn_ok.setAlpha(0.4f);
						btn_ok.setEnabled(false);
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
		mNameEdit.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					mNameEdit.setAlpha(1);
					mNameEdit.setHintTextColor(getResources().getColor(
							R.color.login_input_focus_size));
					v_nick.setAlpha(1);
				} else {

					mNameEdit.setAlpha(0.4f);
					mNameEdit.setHintTextColor(getResources().getColor(
							R.color.login_input_nofocus_size));
					v_nick.setAlpha(0.4f);
				}
			}
		});
		mPassEdit.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					mPassEdit.setAlpha(1);
					mPassEdit.setHintTextColor(getResources().getColor(
							R.color.login_input_focus_size));
					v_pass.setAlpha(1);
				} else {
					mPassEdit.setAlpha(0.4f);
					mPassEdit.setHintTextColor(getResources().getColor(
							R.color.login_input_nofocus_size));
					v_pass.setAlpha(0.4f);
				}
			}
		});
	}


	@Override
	public String getNickName() {
		return mNameEdit.getText().toString();
	}

	@Override
	public String getPassword() {
		return mPassEdit.getText().toString();
	}

	@Override
	public String getMobile() {
		return mobile;
	}

	@Override
	public void toMainActivity() {
		Intent intent = new Intent(
				RegisterFinishActivity.this,
				Main2Activity.class);
		intent.putExtra("verification", true);
		BaseActivity.finishAll();
		startActivity(intent);
	}

	@Override
	public void showFailToast(String message) {
		ToastUtil.showToast(message);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btn_ok:
				String name = mNameEdit.getText().toString();
				String pass = mPassEdit.getText().toString();
				if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
					ToastUtil.showToast("请填写昵称和密码");
					return;
				}
				presenter.register();
		}
	}
}
