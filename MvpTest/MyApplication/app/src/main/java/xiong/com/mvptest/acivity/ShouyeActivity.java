package xiong.com.mvptest.acivity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import xiong.com.mvptest.R;
import xiong.com.mvptest.adapter.FragmentAdapter;
import xiong.com.mvptest.fragment.NewsFragment;


public class ShouyeActivity extends AppCompatActivity {
    private static final String TAG = "ShouyeActivity";
    private ViewPager pager;
    ArrayList<String> list=new ArrayList<>();
    private ArrayList<View> views =new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouye);
        pager = (ViewPager) findViewById(R.id.vp_shouye);
        initViewPager();
    }
    public void initViewPager(){
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        List<String> titles = new ArrayList<>();
        titles.add("精选");
        titles.add("体育");
        titles.add("巴萨");
        titles.add("购物");
        for(int i=0;i<titles.size();i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        for(int i=0;i<titles.size();i++){
            fragments.add(new NewsFragment(ShouyeActivity.this,titles));
        }
        FragmentAdapter mFragmentAdapteradapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        pager.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(pager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);

    }
}
