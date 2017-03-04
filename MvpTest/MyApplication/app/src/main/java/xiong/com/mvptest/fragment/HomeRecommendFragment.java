package xiong.com.mvptest.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import xiong.com.mvptest.R;
import xiong.com.mvptest.adapter.FragmentAdapter;
import xiong.com.mvptest.adapter.MyGridRecyclerAdapter;
import xiong.com.mvptest.driver.DividerGridItemDecoration;
import xiong.com.mvptest.image.GlideImageLoader;
import xiong.com.mvptest.util.ToastUtil;


public class HomeRecommendFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager vp_shouye;
    private List<String> images =new ArrayList<>();
    public HomeRecommendFragment() {
        // Required empty public constructor
    }

    public static HomeRecommendFragment newInstance(String param1, String param2) {
        HomeRecommendFragment fragment = new HomeRecommendFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_recommend, container, false);
//        initViewPager();
        Fresco.initialize(this.getActivity());
        images.add("http://img1.gtimg.com/tech/pics/hv1/136/74/2186/142163656.jpg");
        images.add("http://img1.gtimg.com/news/pics/hv1/22/76/2186/142164052.jpg");
        images.add("http://n1.itc.cn/img8/wb/recom/2016/05/02/146213387784474164.JPEG");
        mTabLayout = (TabLayout) view.findViewById(R.id.tl_shouye);
        vp_shouye = (ViewPager) view.findViewById(R.id.vp_shouye);
        List<String> datas =new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            datas.add(i,"item"+i);
        }
        initViewPager();
        return view;
    }
    private void initViewPager(){
//        vp_shouye = (ViewPager) getActivity().findViewById(R.id.vp_shouye);
        List<String> titles = new ArrayList<>();
        titles.add("精选");
        titles.add("体育");
        titles.add("巴萨");
        titles.add("购物");
        for(int i=0;i<titles.size();i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new KechengFragment());
        fragments.add(new NewsFragment(getActivity(),titles));
//        for(int i=0;i<titles.size();i++){
//            fragments.add(new NewsFragment(getActivity(),titles));
//        }
        FragmentAdapter mFragmentAdapteradapter =
                new FragmentAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        vp_shouye.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(vp_shouye);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);

    }


}
