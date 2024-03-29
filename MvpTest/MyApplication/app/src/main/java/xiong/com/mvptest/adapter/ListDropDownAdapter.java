package xiong.com.mvptest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import xiong.com.mvptest.R;

public class ListDropDownAdapter extends BaseAdapter {



    private Context context;

    private List<String> list;

    private int checkItemPosition = 0;



    public void setCheckItem(int position) {

        checkItemPosition = position;

        notifyDataSetChanged();

    }



    public ListDropDownAdapter(Context context, List<String> list) {

        this.context = context;

        this.list = list;

    }



    @Override

    public int getCount() {

        return list.size();

    }



    @Override

    public Object getItem(int position) {

        return null;

    }



    @Override

    public long getItemId(int position) {

        return position;

    }



    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView != null) {

            viewHolder = (ViewHolder) convertView.getTag();

        } else {

            convertView = LayoutInflater.from(context).inflate(R.layout.search_item, null);
            viewHolder = new ViewHolder();
            viewHolder.mText= (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(viewHolder);

        }

        fillValue(position, viewHolder);

        return convertView;

    }



    private void fillValue(int position, ViewHolder viewHolder) {

        viewHolder.mText.setText(list.get(position));

        if (checkItemPosition != -1) {

            if (checkItemPosition == position) {

                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.drop_down_selected));

                viewHolder.mText.setBackgroundResource(R.color.check_bg);

            } else {

                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));

                viewHolder.mText.setBackgroundResource(R.color.white);

            }

        }

    }



    static class ViewHolder {
        TextView mText;
    }

}