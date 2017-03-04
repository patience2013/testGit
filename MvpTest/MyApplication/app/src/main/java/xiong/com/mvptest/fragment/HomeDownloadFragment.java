package xiong.com.mvptest.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xiong.com.mvptest.R;
import xiong.com.mvptest.acivity.MainActivity;
import xiong.com.mvptest.adapter.MyRecyclerAdapter;
import xiong.com.mvptest.driver.DriverItemDecoration;
import xiong.com.mvptest.util.ToastUtil;


public class HomeDownloadFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyRecyclerAdapter adapter;
    private List<String> datas;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Log.i("datas", datas.size()+"qq");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_home_download, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        datas =new ArrayList<String>();
        for (int i = 0; i <40 ; i++) {
            datas.add("Item"+i);
        }
        adapter =new MyRecyclerAdapter(this.getActivity(),datas);
        LinearLayoutManager layout=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layout);
        layout.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DriverItemDecoration(getActivity(),DriverItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickLitener(new MyRecyclerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtil.showToast("点击"+position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ToastUtil.showToast("长按"+position);
                adapter.removeData(position);

            }
        });
        return view;
    }

}
