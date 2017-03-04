package xiong.com.mvptest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xiong.com.mvptest.R;

public class Baseadapter extends RecyclerView.Adapter<Baseadapter.ViewHolder> {
    private int checkItemPosition = 0;
    private Context mContext;
    private List<String> datas;
    public Baseadapter(Context context,List<String> datas) {
        this.mContext =context;
        this.datas=datas;
    }

    public void setCheckItemPosition(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private MyRecyclerAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(MyRecyclerAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (checkItemPosition != -1) {

            if (checkItemPosition == position) {

                holder.tv_item.setTextColor(mContext.getResources().getColor(R.color.drop_down_selected));

                holder.tv_item.setBackgroundResource(R.color.check_bg);

            } else {

                holder.tv_item.setTextColor(mContext.getResources().getColor(R.color.drop_down_unselected));

                holder.tv_item.setBackgroundResource(R.color.white);

            }

        }
        holder.tv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos =holder.getLayoutPosition();
                mOnItemClickLitener.onItemClick(holder.itemView,pos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item;

        public ViewHolder(View view) {
            super(view);
            tv_item = (TextView) view.findViewById(R.id.tv_item);
        }
    }
}