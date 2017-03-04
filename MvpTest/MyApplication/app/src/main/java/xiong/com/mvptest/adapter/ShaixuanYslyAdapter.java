package xiong.com.mvptest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xiong.com.mvptest.R;
import xiong.com.mvptest.bean.ShaixuanItem;

/**
 * Created by lenovo on 2017/2/16.
 */

public class ShaixuanYslyAdapter extends RecyclerView.Adapter<ShaixuanYslyAdapter.MyViewHolder>{
    private Context context;
    private List<ShaixuanItem> datas;
    public OnItemSelectedListener mListener;
    private boolean[] isSelected ;

    public interface OnItemSelectedListener{
        void onItemSelect(int position);
    }
    public void setOnItemSelectedListener(OnItemSelectedListener listener){
        mListener = listener;
    }
    public ShaixuanYslyAdapter(Context context, List<ShaixuanItem> data) {
        this.context=context;
        this.datas =data;
        isSelected = new boolean[data.size()];
        for (int i = 0; i < data.size(); i++) {
            isSelected[i] = false;
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.text.setText(datas.get(position).getText());
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected[position] =isSelected[position]==true?false:true;
                notifyDataSetChanged();
                mListener.onItemSelect(position);
            }
        });
        if(isSelected[position] == true){
            holder.text.setTextColor(context.getResources().getColorStateList(R.color.tint_bar_before));
            holder.text.setBackgroundResource(R.drawable.shaixuan_selected);
        }else{
            holder.text.setBackgroundResource(R.drawable.shaixuan_default);
            holder.text.setTextColor(context.getResources().getColorStateList(R.color.gray));
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context, R.layout.item_home,null));
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.tv_item);
        }

    }
}
