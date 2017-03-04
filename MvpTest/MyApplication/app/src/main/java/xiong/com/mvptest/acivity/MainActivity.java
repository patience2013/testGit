package xiong.com.mvptest.acivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.TextView;

import xiong.com.mvptest.R;
import xiong.com.mvptest.baseclass.BaseActivity;
import xiong.com.mvptest.fragment.FragmentController;

/**
 * Created by lenovo on 2017/1/12.
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{
    private FragmentController controller;
    private RadioGroup hometabRadio;
    private TextView hometab_toolbar_textview_title;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        controller = FragmentController.getInstance(this, R.id.hometab_context);
        controller.showFragment(0);
        hometabRadio = (RadioGroup) findViewById(R.id.hometab_radio);
        hometabRadio.setOnCheckedChangeListener(this);
        hometab_toolbar_textview_title = (TextView) findViewById(R.id.hometab_toolbar_textview_title);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_recommend:
                hometab_toolbar_textview_title.setText("推荐");
                controller.showFragment(0);
                break;
            case R.id.rb_stack:
                hometab_toolbar_textview_title.setText("书库");
                controller.showFragment(1);
                break;
            case R.id.rb_search:
                hometab_toolbar_textview_title.setText("搜索");
                controller.showFragment(2);
                break;
            case R.id.rb_download:
                hometab_toolbar_textview_title.setText("下载");
                controller.showFragment(3);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (((keyCode == KeyEvent.KEYCODE_BACK) ||
                (keyCode == KeyEvent.KEYCODE_HOME))
                && event.getRepeatCount() == 0) {
//            new MyDialogHint(HomeTabActivity.this,R.style.MyDialog1).show();
        }
        return false;
    }
}
