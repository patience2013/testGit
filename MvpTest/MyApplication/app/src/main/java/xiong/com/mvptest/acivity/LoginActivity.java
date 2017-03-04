package xiong.com.mvptest.acivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;


import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import xiong.com.mvptest.App;
import xiong.com.mvptest.R;
import xiong.com.mvptest.baseclass.BaseActivity;
import xiong.com.mvptest.bean.User;
import xiong.com.mvptest.mvp.presenter.impl.UploadPresenter;
import xiong.com.mvptest.mvp.presenter.impl.UserLoginPresenter;
import xiong.com.mvptest.mvp.view.impl.ILoginview;
import xiong.com.mvptest.myView.HorizontalProgressBarWithNumber;
import xiong.com.mvptest.util.FileUtil;
import xiong.com.mvptest.util.ImageUtil;
import xiong.com.mvptest.util.ToastUtil;
import xiong.com.mvptest.util.UIHandlerUtil;

public class LoginActivity extends BaseActivity implements ILoginview, View.OnClickListener {
    private EditText et_username, et_userpass;
    private Button bt_login, bt_upload;
    private Toolbar toolbar;
    private ImageView img,iv_delete_acount, iv_delete_password,iv_pass,iv_account;
    private View v_account,v_pass;
    private ProgressBar pb;
    private HorizontalProgressBarWithNumber progress1;
    private UserLoginPresenter mUserpresenter = new UserLoginPresenter(this);
    private UploadPresenter uploadPresenter = new UploadPresenter(this, this);
    private Dialog progressDialog;

    Bitmap bitmap = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        mHandler.sendEmptyMessage(0x110);

        setProgressBarIndeterminateVisibility(true);
        FrameLayout rightview= (FrameLayout) findViewById(R.id.title_right_rootView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        v_account =findViewById(R.id.v_account);
        v_pass =findViewById(R.id.v_pass);
        iv_pass = (ImageView) findViewById(R.id.iv_pass);
        iv_account = (ImageView) findViewById(R.id.iv_pass);
        iv_delete_password = (ImageView) findViewById(R.id.iv_delete_password);
        iv_delete_acount = (ImageView) findViewById(R.id.iv_delete_acount);
        iv_delete_password.setOnClickListener(this);
        iv_delete_acount.setOnClickListener(this);
        et_username = (EditText) findViewById(R.id.et_username);
        et_userpass = (EditText) findViewById(R.id.et_userpass);
        bt_login = (Button) findViewById(R.id.login_login_btn);
//        pb = (ProgressBar) findViewById(R.id.pb);
        bt_upload = (Button) findViewById(R.id.bt_upload);
        img = (ImageView) findViewById(R.id.img);
        progress1 = (HorizontalProgressBarWithNumber) findViewById(R.id.progress);
        TextView forgetpass=new TextView(this);
        forgetpass.setTextColor(0xffA72B23);
        forgetpass.setText("忘记密码");
        setTilte("登陆",forgetpass);


        et_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    et_username.setAlpha(1);
                    et_username.setHintTextColor(getResources().getColor(
                            R.color.login_input_focus_size));
                    v_account.setAlpha(1);
                    iv_account.setAlpha(1f);
                } else {

                    et_username.setAlpha(0.4f);
                    et_username.setHintTextColor(getResources().getColor(
                            R.color.login_input_nofocus_size));
                    v_account.setAlpha(0.4f);
                    iv_account.setAlpha(0.4f);

                }
            }
        });
        et_userpass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    et_userpass.setAlpha(1);
                    et_userpass.setHintTextColor(getResources().getColor(
                            R.color.login_input_focus_size));
                    v_pass.setAlpha(1);
                    iv_pass.setAlpha(1f);
                } else {
                    et_userpass.setAlpha(0.4f);
                    et_userpass.setHintTextColor(getResources().getColor(
                            R.color.login_input_nofocus_size));
                    v_pass.setAlpha(0.4f);
                    iv_pass.setAlpha(0.4f);
                }
            }
        });
        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = s.toString();
                if ("".equals(content)) {
                    iv_delete_acount.setVisibility(View.GONE);
                    bt_login.setAlpha(0.4f);
                    bt_login.setEnabled(false);
                } else {
                    iv_delete_acount.setVisibility(View.VISIBLE);
                    boolean haveContentToPassword = !et_userpass.getText()
                            .toString().equals("");
                    if (haveContentToPassword) {
                        bt_login.setAlpha(1f);
                        bt_login.setEnabled(true);
                    } else {
                        bt_login.setAlpha(0.4f);
                        bt_login.setEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_userpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = s.toString();
                if ("".equals(content)) {
                    iv_delete_password.setVisibility(View.GONE);
                    bt_login.setAlpha(0.4f);
                    bt_login.setEnabled(false);
                } else {
                    iv_delete_password.setVisibility(View.VISIBLE);
                    boolean haveContentToUser = !et_username.getText()
                            .toString().equals("");
                    if (haveContentToUser) {
                        bt_login.setAlpha(1f);
                        bt_login.setEnabled(true);
                    } else {
                        bt_login.setAlpha(0.4f);
                        bt_login.setEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    public String getUsername() {
        return et_username.getText().toString();
    }

    @Override
    public String getUserpass() {
        return et_userpass.getText().toString();
    }

    @Override
    public void showProgress() {
        UIHandlerUtil.sendEmptyMessage(0, new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                ToastUtil.closeDialog(progressDialog);
                progressDialog = ToastUtil.showLoadDialog(LoginActivity.this,
                        "正在登录，请稍后...", false);
                return false;
            }
        });
//        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        ToastUtil.closeDialog(progressDialog);

//        pb.setVisibility(View.GONE);
    }

    @Override
    public void clearUsername() {
        et_username.setText("");
    }

    @Override
    public void clearUserpass() {
        et_userpass.setText("");
    }

    @Override
    public void toMainActivity(User user) {
        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtras("User", user);
        startActivity(intent);
    }

    @Override
    public void toFailed(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public String getFilename() {
        return ImageUtil.saveBitmapToTemp(bitmap);
    }

    @Override
    public void onClick(View v) {
        Intent intent =null;
        switch (v.getId()) {
            case R.id.login_login_btn:
                ToastUtil.showToast("登陆");
                mUserpresenter.login();
                break;
            case R.id.iv_delete_acount:
                et_username.setText("");
                iv_delete_acount.setVisibility(View.GONE);
                break;
            case R.id.iv_delete_password:
                et_userpass.setText("");
                iv_delete_password.setVisibility(View.GONE);

                break;
            case R.id.title_right_rootView:
                intent = new Intent(LoginActivity.this,
                        ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.register_login:
                intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_upload:
//                try {
//                    // 选择照片的时候也一样，我们用Action为Intent.ACTION_GET_CONTENT，
//                    // 有些人使用其他的Action但我发现在有些机子中会出问题，所以优先选择这个
//                    Intent intent = new Intent();
//                    intent.setType("image/*");
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                    startActivityForResult(intent, 2);
//                } catch (ActivityNotFoundException e) {
//
//                }
                new AlertDialog.Builder(this).setMessage("选择图片").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LoginActivity.this, "取消啦", Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, 2);

                    }
                }).show();
//                if(bitmap!=null){
//                    String filePath = ImageUtil.saveBitmapToTemp(bitmap);
//                    String filePath1 = FileUtil.getTmpFile();
//                    File file = FileUtil.getZipPicture(filePath, filePath1);
//                    uploadPresenter.UploadImg(file,App.HTTP_API_HOST+"/App/upload");
//                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {

        } else if (requestCode == 2) {
            Uri uri = data.getData();
            if (uri != null) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), uri);
                    img.setImageBitmap(bitmap);
                    String filePath = ImageUtil.saveBitmapToTemp(bitmap);
                    String filePath1 = FileUtil.getTmpFile();
                    File file = FileUtil.getZipPicture(filePath, filePath1);
                    Log.i("TAG", "onActivityResult: " + filePath + "filePath1" + filePath1);
//                    pb.setVisibility(View.VISIBLE);
                    OkHttpUtils.post().addFile("upload", filePath, file).url(App.HTTP_API_HOST + "/App/upload").build().execute(new com.zhy.http.okhttp.callback.Callback() {
                        @Override
                        public Object parseNetworkResponse(Response response, int id) throws Exception {
                            return null;
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {
                        }

                        @Override
                        public void onResponse(Object response, int id) {
                            Log.i("TAG", "onResponse: " + "上传成功" + id);

                        }

                        @Override
                        public void inProgress(float progress, long total, int id) {
                            super.inProgress(progress, total, id);
                            Log.i("progress", "inProgress: " + progress);

                            if (progress >= 1) {
//                                pb.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                            } else {
//                                pb.setProgress((int) progress * 100);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int progress = progress1.getProgress();
            progress1.setProgress(++progress);
            if (progress >= 100) {
                mHandler.removeMessages(0x110);
            }
            mHandler.sendEmptyMessageDelayed(0x110, 10);
        }

        ;
    };

}
