package xiong.com.mvptest.baseclass;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import xiong.com.mvptest.R;

/**
 * Created by 62416 on 2016/10/27.
 */

public class BaseActivity extends AppCompatActivity {
    private static ArrayList<Activity> activities = new ArrayList<Activity>();

    private boolean mMenuShowing = false;
    private ListView mMenuList;
    private boolean mHasDropMenu = false;
    private LinearLayout line_state;

    protected void initView() {

    }

    protected void updateView() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        activities.remove(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    /*初始化标题栏*/
    public void setTitle(String title){
        setTilte(title,null);
    }
    public void setTilte(String title, View view){
        TextView textView = (TextView) findViewById(R.id.title_title);
        textView.setText(title);
        //左边的返回按钮
        LinearLayout back = (LinearLayout) findViewById(R.id.title_back_li);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(view !=null){
            FrameLayout rightview= (FrameLayout) findViewById(R.id.title_right_rootView);
            rightview.addView(view);
        }
    }

    // 得到栈顶的activity
    public static Activity topActivity() {
        if (activities.size() > 0) {
            return activities.get(activities.size() - 1);
        }
        return null;
    }

    // 向栈添加activity
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    // 向栈中删除指定activity
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    // 迭代里面的activity 向栈中删除所有activity
    public static void finishAll() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        activities.clear();
    }

    // 迭代里面的activity 向栈中删除Activity除了栈顶的activity
    public static void finishAllExceptTop() {
        for (int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            if (i < activities.size() - 1) {
                if (activity != null) {
                    activity.finish();
                }
            }
        }
        activities.clear();
    }
    public Boolean isConnnectedType(){
        ConnectivityManager connMgr = (ConnectivityManager) getApplication()
                .getSystemService(getApplication().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();
        if(isWifiConn || isMobileConn){
            return true;
        }
        return false;
    }
}
