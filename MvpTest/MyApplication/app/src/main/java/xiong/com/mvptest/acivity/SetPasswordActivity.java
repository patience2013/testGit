package xiong.com.mvptest.acivity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;

import xiong.com.mvptest.R;
import xiong.com.mvptest.baseclass.BaseActivity;
import xiong.com.mvptest.mvp.presenter.impl.ResetPassPresenter;
import xiong.com.mvptest.mvp.view.impl.IResetPassView;
import xiong.com.mvptest.util.ToastUtil;

public class SetPasswordActivity extends BaseActivity implements IResetPassView ,OnClickListener{
	private EditText edt_password, edt_ok_password;
	private Button btn_ok;
	private String mobile;
	private String password;
	private View v_pass, v_ok_pass;
	private ResetPassPresenter presenter =new ResetPassPresenter(this);

	@Override
	protected void initView() {
		setContentView(R.layout.activity_set_password);
		mobile = getIntent().getStringExtra("mobile");
		edt_password = (EditText) findViewById(R.id.edt_password);
		edt_ok_password = (EditText) findViewById(R.id.edt_ok_password);
		v_pass = findViewById(R.id.v_pass);
		v_ok_pass = findViewById(R.id.v_ok_pass);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		setTitle("重置密码");
		handlerEdtDisplay();
	}
	// 处理输入框的显示效果的方法
	public void handlerEdtDisplay() {
		edt_password.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
				String content = s.toString();
				if ("".equals(content)) {
					btn_ok.setAlpha(0.4f);
					btn_ok.setEnabled(false);
				} else {
					boolean haveContentToPassword = !edt_ok_password.getText()
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
		edt_ok_password.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
				String content = s.toString();
				if ("".equals(content)) {
					btn_ok.setAlpha(0.4f);
					btn_ok.setEnabled(false);
				} else {
					boolean haveContentToUser = !edt_password.getText()
							.toString().equals("");
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
		edt_password.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					edt_password.setAlpha(1f);
					edt_password.setHintTextColor(getResources().getColor(
							R.color.login_input_focus_size));
					v_pass.setAlpha(1f);
				} else {

					edt_password.setAlpha(0.4f);
					edt_password.setHintTextColor(getResources().getColor(
							R.color.login_input_nofocus_size));
					v_pass.setAlpha(0.4f);
				}
			}
		});
		edt_ok_password.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					edt_ok_password.setAlpha(1f);
					edt_ok_password.setHintTextColor(getResources().getColor(
							R.color.login_input_focus_size));
					v_ok_pass.setAlpha(1f);
				} else {
					edt_ok_password.setAlpha(0.4f);
					edt_ok_password.setHintTextColor(getResources().getColor(
							R.color.login_input_nofocus_size));
					v_ok_pass.setAlpha(0.4f);
				}
			}
		});
	}

	@Override
	public String getMobile() {
		return mobile;
	}

	@Override
	public String getPass() {
		return edt_ok_password.getText().toString();
	}

	@Override
	public void toLoginActivity() {
		Intent intent =new Intent(SetPasswordActivity.this,LoginActivity.class);
		startActivity(intent);
		ToastUtil.showToast("密码修改成功，请重新登录");
	}

	@Override
	public void showFailedToast() {
		ToastUtil.showToast("两次输入的密码不一致");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btn_ok:
				password = edt_password.getText().toString();
				String ok_password = edt_ok_password.getText().toString();
				if (password.length() == 0) {
					ToastUtil.showToast("密码不能为空");
					return;
				}
				if (ok_password.length() == 0) {
					ToastUtil.showToast("确认密码不能为空");
					return;
				}
				if (!ok_password.equals(password)) {
					ToastUtil.showToast("两次输入密码不一致");
					return;
				}
				presenter.resetPasss();
				break;
		}
	}
}
