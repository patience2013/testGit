package xiong.com.mvptest.acivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xiong.com.mvptest.R;
import xiong.com.mvptest.adapter.ConstellationAdapter;
import xiong.com.mvptest.adapter.ListDropDownAdapter;
import xiong.com.mvptest.adapter.MingshiListAdapter;

public class OpenClassActivity extends AppCompatActivity {
    private DropDownMenu mDropDownMenu;
    //    private TextView  contentView;
    private ListDropDownAdapter cityAdapter, sexAdapter, ageAdapter;
    private ConstellationAdapter mConstellationAdapter;
    private LinearLayoutManager layoutmanger;
    private String headers[] = {"城市", "年龄", "性别", "星座"};
    private List<View> popupViews = new ArrayList<>();
    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};

    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};

    private String sexs[] = {"不限", "男", "女"};

    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};
    private int constellationPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_class);
        mDropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);
//        contentView = (TextView) findViewById(R.id.tv_context);
        initView();
    }

//    private void initView() {
//
//        //init city menu
//
//        final RecyclerView cityView = new RecyclerView(this);
//
//        adapter = new Baseadapter(this,Arrays.asList(citys));
//
//        layoutmanger = new LinearLayoutManager(this);
//        cityView.setLayoutManager(layoutmanger);
//        cityView.setAdapter(adapter);
//        adapter.setOnItemClickLitener(new MyRecyclerAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                adapter.setCheckItemPosition(position);
//
//                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
//                mDropDownMenu.closeMenu();
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        });
//
//
//        //init age menu
//
//        final RecyclerView ageView = new RecyclerView(this);
//        layoutmanger = new LinearLayoutManager(this);
//        ageView.setLayoutManager(layoutmanger);
//        adapter = new Baseadapter(this,Arrays.asList(ages));
//        ageView.setAdapter(adapter);
//        adapter.setOnItemClickLitener(new MyRecyclerAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                adapter.setCheckItemPosition(position);
//
//                mDropDownMenu.setTabText(position == 0 ? headers[0] : ages[position]);
//
//                mDropDownMenu.closeMenu();
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        });
//
//
//        //init sex menu
//
//        final RecyclerView sexView = new RecyclerView(this);
//
//        layoutmanger = new LinearLayoutManager(this);
//        adapter = new Baseadapter(this,Arrays.asList(sexs));
//        sexView.setLayoutManager(layoutmanger);
//        sexView.setAdapter(adapter);
//        adapter.setOnItemClickLitener(new MyRecyclerAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                adapter.setCheckItemPosition(position);
//
//                mDropDownMenu.setTabText(position == 0 ? headers[0] : sexs[position]);
//
//                mDropDownMenu.closeMenu();
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        });
//
//
//        //init constellation
//        final RecyclerView xingzuoView = new RecyclerView(this);
//
//        layoutmanger = new LinearLayoutManager(this);
//        adapter = new Baseadapter(this,Arrays.asList(constellations));
//        xingzuoView.setLayoutManager(layoutmanger);
//        xingzuoView.setAdapter(adapter);
//        adapter.setOnItemClickLitener(new MyRecyclerAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                adapter.setCheckItemPosition(position);
//
//                mDropDownMenu.setTabText(position == 0 ? headers[0] : constellations[position]);
//
//                mDropDownMenu.closeMenu();
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        });
//
//
//        //init popupViews
//
//        popupViews.add(cityView);
//
//        popupViews.add(ageView);
//
//        popupViews.add(sexView);
//
//        popupViews.add(xingzuoView);
//
//
//        //add item click event
//
//
//        //init context view
//
//        TextView contentView = new TextView(this);
//
//        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//
//        contentView.setText("内容显示区域");
//
//        contentView.setGravity(Gravity.CENTER);
//
//        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
//
//
//        //init dropdownview
//
//        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
//
//    }

    private void initView() {

        //init city menu

        final ListView cityView = new ListView(this);

        cityAdapter = new ListDropDownAdapter(this, Arrays.asList(citys));

        cityView.setDividerHeight(1);

        cityView.setAdapter(cityAdapter);


        //init age menu

        final ListView ageView = new ListView(this);

        ageView.setDividerHeight(1);

        ageAdapter = new ListDropDownAdapter(this, Arrays.asList(ages));

        ageView.setAdapter(ageAdapter);


        //init sex menu

        final ListView sexView = new ListView(this);

        sexView.setDividerHeight(1);

        sexAdapter = new ListDropDownAdapter(this, Arrays.asList(sexs));

        sexView.setAdapter(sexAdapter);


        //init constellation

        final View constellationView = getLayoutInflater().inflate(R.layout.custom_layout, null);

        GridView constellation = (GridView) constellationView.findViewById(R.id.constellation);

        mConstellationAdapter = new ConstellationAdapter(this, Arrays.asList(constellations));

        constellation.setAdapter(mConstellationAdapter);

        TextView ok = (TextView) constellationView.findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[3] : constellations[constellationPosition]);

                mDropDownMenu.closeMenu();

            }

        });


        //init popupViews

        popupViews.add(cityView);

        popupViews.add(ageView);

        popupViews.add(sexView);

        popupViews.add(constellationView);


        //add item click event

        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cityAdapter.setCheckItem(position);

                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);

                mDropDownMenu.closeMenu();

            }

        });


        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ageAdapter.setCheckItem(position);

                mDropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);

                mDropDownMenu.closeMenu();

            }

        });


        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                sexAdapter.setCheckItem(position);

                mDropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);

                mDropDownMenu.closeMenu();

            }

        });


        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mConstellationAdapter.setCheckItem(position);

                constellationPosition = position;

            }

        });


        //init context view

        TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        contentView.setText("内容显示" + "");

        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(20);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        RecyclerView recyclerView =new RecyclerView(this);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayoutManager layoutmanger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutmanger);
        recyclerView.setAdapter(new MingshiListAdapter());
        //init dropdownview

        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, recyclerView);

    }

    @Override

    public void onBackPressed() {

        //退出activity前关闭菜单

        if (mDropDownMenu.isShowing()) {

            mDropDownMenu.closeMenu();

        } else {

            super.onBackPressed();

        }

    }

}
