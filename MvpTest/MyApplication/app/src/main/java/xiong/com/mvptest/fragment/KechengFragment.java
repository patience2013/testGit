package xiong.com.mvptest.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import xiong.com.mvptest.image.GlideImageLoader;
import xiong.com.mvptest.util.ToastUtil;

public class KechengFragment extends Fragment {

    private Banner banner;
    private RecyclerView recyclerView;
    private View view;
    private List<String> images =new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_kecheng, container, false);
        initView();
        return view;

    }
    private void initView(){
        Fresco.initialize(this.getActivity());
        images.add("http://img1.gtimg.com/tech/pics/hv1/136/74/2186/142163656.jpg");
        images.add("http://img1.gtimg.com/news/pics/hv1/22/76/2186/142164052.jpg");
        images.add("http://n1.itc.cn/img8/wb/recom/2016/05/02/146213387784474164.JPEG");
        banner = (Banner) view.findViewById(R.id.banner);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        banner = (Banner) view.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtil.showToast("点击"+position);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

}
