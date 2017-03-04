package xiong.com.mvptest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import de.hdodenhof.circleimageview.CircleImageView;
import xiong.com.mvptest.R;

/**
 * Created by lenovo on 2017/2/22.
 */

public class MingshiListAdapter extends RecyclerView.Adapter<MingshiListAdapter.ViewHolder> {

    public MingshiListAdapter() {
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.imageView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mingshi_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = (CircleImageView) view.findViewById(R.id.cim_image);
        }
    }
}
