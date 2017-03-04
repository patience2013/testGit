package xiong.com.mvptest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import xiong.com.mvptest.R;

/**
 * Created by lenovo on 2017/2/23.
 */

public class MyInfoZhuyeFragment extends Fragment{
    private ExpandableTextView et_jianjie;
    public MyInfoZhuyeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.myinfo_zhuyefragment,container,false);
        et_jianjie = (ExpandableTextView) view.findViewById(R.id.et_jianjie);
        initView();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void initView(){
        et_jianjie.setText("fowaegjaowfo佛啊额我佛发我啊额就噶嘎我恶搞恶搞嘎我文稿文稿i高额高温干旱iowa额噶额我刚" +
                "好问个好为噶为更好围观哈维后果嘎我热哦更好围观哈维哦韩国哦嘎和我干哈我额个哦好哇宫娥很高违和感嘎" +
                "我额和高温和嘎为哦给哦啊围观号位黄盖我额个好哦话我额个哈我额该");
    }
}
