package xiong.com.mvptest.acivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xiong.com.mvptest.R;
import xiong.com.mvptest.adapter.MingshiListAdapter;
import xiong.com.mvptest.adapter.ShaixuanRecyclerAdapter;
import xiong.com.mvptest.adapter.ShaixuanYslyAdapter;
import xiong.com.mvptest.baseclass.BaseActivity;
import xiong.com.mvptest.bean.ShaixuanItem;
import xiong.com.mvptest.driver.DividerGridItemDecoration;

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "SearchActivity";
    TextView shaixuan;
    private LinearLayout lt_shaixuan;
    private List<ShaixuanItem> datas = new ArrayList<>();
    private List<ShaixuanItem> yslydatas = new ArrayList<>();
    private RecyclerView rcYY, rcysly;
    private ShaixuanRecyclerAdapter adapter;
    private ShaixuanYslyAdapter yslyAdapter;
    private float mDensity;
    private int mHiddenViewMeasuredHeight; //筛选框的高度
    private RecyclerView ry_mingshi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        shaixuan = (TextView) findViewById(R.id.tv_shaixuan);
        rcYY = (RecyclerView) findViewById(R.id.rc_yy);
        rcysly = (RecyclerView) findViewById(R.id.rc_ysly);
        lt_shaixuan = (LinearLayout) findViewById(R.id.lt_shaixuan);
        ry_mingshi = (RecyclerView) findViewById(R.id.ry_mingshi);
        LinearLayoutManager layoutmanger = new LinearLayoutManager(this);
        ry_mingshi.setLayoutManager(layoutmanger);
        MingshiListAdapter adapter = new MingshiListAdapter();
        ry_mingshi.setAdapter(adapter);
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        lt_shaixuan.measure(w, h);
        mDensity = getResources().getDisplayMetrics().density;
        mHiddenViewMeasuredHeight = (int) (lt_shaixuan.getMeasuredHeight() * mDensity + 0.5);

        initRecycleView();
        shaixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onCreate: " + lt_shaixuan.getHeight() + "高度" + lt_shaixuan.getMeasuredHeight());
                if (lt_shaixuan.getVisibility() == View.GONE) {
                    lt_shaixuan.setVisibility(View.VISIBLE);
                    animateOpen(lt_shaixuan);
                    animationIvOpen();
                } else {
                    animateClose(lt_shaixuan);
                    animationIvClose();
                }
            }
        });

    }

    private void initRecycleView() {
        initListData();
//        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(10, 40);
        adapter = new ShaixuanRecyclerAdapter(this, datas);
        yslyAdapter = new ShaixuanYslyAdapter(this, yslydatas);
        rcysly.setHasFixedSize(true);
        rcysly.addItemDecoration(itemDecoration);
        rcysly.setAdapter(yslyAdapter);
        rcysly.setLayoutManager(new GridLayoutManager(this, 3));
        yslyAdapter.setOnItemSelectedListener(new ShaixuanYslyAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelect(int position) {
                if (yslydatas.get(position).getIsCheck() == 0) {
                    yslydatas.get(position).setIsCheck(1);
                } else {
                    yslydatas.get(position).setIsCheck(0);
                }
            }
        });
        if (adapter != null) {
//            rcYY.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//            rcYY.setLayoutManager(layoutManager);
            rcYY.setHasFixedSize(true);

            rcYY.addItemDecoration(itemDecoration);

            rcYY.setAdapter(adapter);
            rcYY.setLayoutManager(new GridLayoutManager(this, 3));
            rcYY.setItemAnimator(new DefaultItemAnimator());
        }
        adapter.setOnItemClickLitener(new ShaixuanRecyclerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                if (datas.get(position).getIsCheck() == 0) {
                    datas.get(position).setIsCheck(1);
                } else {
                    datas.get(position).setIsCheck(0);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private void initListData() {
        datas.add(new ShaixuanItem(0, "初级"));
        datas.add(new ShaixuanItem(1, "中级"));
        datas.add(new ShaixuanItem(2, "高级"));
        datas.add(new ShaixuanItem(3, "特级"));

        yslydatas.add(new ShaixuanItem(0, "书法"));
        yslydatas.add(new ShaixuanItem(1, "篆刻"));
        yslydatas.add(new ShaixuanItem(2, "国画"));
        yslydatas.add(new ShaixuanItem(3, "油画"));
        yslydatas.add(new ShaixuanItem(4, "雕刻"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_shaixuan:

        }
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int horizontalspace;
        private int verticalspace;

        public SpaceItemDecoration(int horizontalspace, int verticalspace) {
            this.horizontalspace = horizontalspace;
            this.verticalspace = verticalspace;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.left = horizontalspace;
            outRect.bottom = verticalspace;
//        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
            if (parent.getChildLayoutPosition(view) % 3 == 0) {
                outRect.left = 0;
            }
            int childCount = parent.getChildCount();
        }

    }

    private void animateOpen(View v) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0,
                mHiddenViewMeasuredHeight);
        animator.start();

    }

    private void animationIvOpen() {
        RotateAnimation animation = new RotateAnimation(0, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        lt_shaixuan.startAnimation(animation);
    }

    private void animationIvClose() {
        RotateAnimation animation = new RotateAnimation(0, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        lt_shaixuan.startAnimation(animation);
    }

    private void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = Integer.parseInt(arg0.getAnimatedValue().toString());
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);

            }
        });
        return animator;
    }
}
