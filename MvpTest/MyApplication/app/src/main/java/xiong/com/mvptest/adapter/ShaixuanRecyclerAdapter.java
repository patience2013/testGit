package xiong.com.mvptest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xiong.com.mvptest.R;
import xiong.com.mvptest.bean.ShaixuanItem;

/**
 * Created by lenovo on 2017/1/13.
 */

public class ShaixuanRecyclerAdapter extends RecyclerView.Adapter<ShaixuanRecyclerAdapter.MyViewHolder>{
    private List<ShaixuanItem> mdatas;
    private Context mContext;
    private LayoutInflater inflater;
    private int pos;
    public interface OnItemClickLitener
    {
        void onItemClick(View view,String data, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public ShaixuanRecyclerAdapter(Context context, List<ShaixuanItem> mdatas){
        this.mContext =context;
        this.mdatas =mdatas;
        inflater =LayoutInflater.from(mContext);
    }
    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mdatas.get(position).getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = holder.getLayoutPosition();
                notifyDataSetChanged();
                mOnItemClickLitener.onItemClick(holder.itemView,(String)holder.itemView.getTag(),pos);

            }
        });
        if(pos ==position)
        {
            holder.tv.setBackgroundResource(R.drawable.shaixuan_selected);
            holder.tv.setTextColor(mContext.getResources().getColorStateList(R.color.tint_bar_before));
        }else{
            holder.tv.setBackgroundResource(R.drawable.shaixuan_default);
            holder.tv.setTextColor(mContext.getResources().getColorStateList(R.color.gray));
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = holder.getLayoutPosition();
                mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                return false;
            }
        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.item_home,parent,false);
        MyViewHolder holder =new MyViewHolder(view);

        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View view){
            super(view);
            tv= (TextView) view.findViewById(R.id.tv_item);

        }
    }
    public void addData(int position) {
//        mdatas.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mdatas.remove(position);
        notifyItemRemoved(position);
    }
}
