package xiong.com.mvptest.acivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import xiong.com.mvptest.App;
import xiong.com.mvptest.R;
import xiong.com.mvptest.adapter.FragmentAdapter;
import xiong.com.mvptest.baseclass.BaseActivity;
import xiong.com.mvptest.fragment.MyInfoZhuyeFragment;
import xiong.com.mvptest.fragment.NewsFragment;
import xiong.com.mvptest.util.FastBlurUtil;

/**
 * Created by lenovo on 2017/2/22.
 */

public class MyInfoDetails extends BaseActivity{
    private LinearLayout ly_bg;
    private ImageView im_bg;
    private TabLayout tb_myinfo;
    private ViewPager vp_myinfo;
    private String url ="http://photocdn.sohu.com/20170222/Img481452754.jpeg";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo_details);
        ly_bg = (LinearLayout) findViewById(R.id.ly_bg);
        im_bg = (ImageView) findViewById(R.id.im_bg);
        tb_myinfo = (TabLayout) findViewById(R.id.tb_myinfo);
        vp_myinfo = (ViewPager) findViewById(R.id.vp_myinfo);
        update_bg(im_bg,url);
        initViewPager();
    }
    //更新毛玻璃背景
    public void update_bg(final ImageView img, final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {
//                下面的这个方法必须在子线程中执行
                final Bitmap blurBitmap2 = FastBlurUtil.GetUrlBitmap(url, 40);
                App.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        im_bg.setScaleType(ImageView.ScaleType.FIT_XY);
                        im_bg.setImageBitmap(blurBitmap2);
                    }
                });
            }
        }).start();
    }
    public void initViewPager(){
        List<String> titles = new ArrayList<>();
        titles.add("主页");
        titles.add("公开课");
        titles.add("预约课");
        titles.add("作品");
        for(int i=0;i<titles.size();i++){
            tb_myinfo.addTab(tb_myinfo.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        for(int i=0;i<titles.size();i++){
            fragments.add(new MyInfoZhuyeFragment());
        }
        FragmentAdapter mFragmentAdapteradapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        vp_myinfo.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        tb_myinfo.setupWithViewPager(vp_myinfo);
        //给TabLayout设置适配器
        tb_myinfo.setTabsFromPagerAdapter(mFragmentAdapteradapter);

    }
}
