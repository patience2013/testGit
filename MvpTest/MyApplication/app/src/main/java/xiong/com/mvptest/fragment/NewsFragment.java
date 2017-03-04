package xiong.com.mvptest.fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xiong.com.mvptest.R;
import xiong.com.mvptest.adapter.MyRecyclerAdapter;
import xiong.com.mvptest.util.ToastUtil;

public class NewsFragment extends Fragment {
    private RecyclerView rc_context;
    private Context mContext;
    private List<String> mDatas;
    public NewsFragment(Context context, List<String> datas) {
        this.mContext =context;
        this.mDatas =datas;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rc_context = (RecyclerView) inflater.inflate(R.layout.fragment_news,container,false);
        return rc_context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rc_context.setLayoutManager(new LinearLayoutManager(rc_context.getContext()));
        MyRecyclerAdapter adapter =new MyRecyclerAdapter(getActivity(),mDatas);
        rc_context.setAdapter(adapter);
        adapter.setOnItemClickLitener(new MyRecyclerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtil.showToast("点击"+position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
