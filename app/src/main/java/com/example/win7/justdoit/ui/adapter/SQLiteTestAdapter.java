package com.example.win7.justdoit.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.win7.justdoit.R;
import com.example.win7.justdoit.ui.api.User;
import java.util.ArrayList;

/**
 * Created by win7 on 2016/10/30.
 */
public class SQLiteTestAdapter extends BaseAdapter {
    private ArrayList<User> data;
    private Context mContext;

    public void setData(ArrayList<User> data) {
        this.data = data;
    }

    public SQLiteTestAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sqlite_test, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            Log.i("TAG","缓存");
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameText = (TextView) convertView.findViewById(R.id.item_sqlite_name);
        holder.sexText = (TextView) convertView.findViewById(R.id.item_sqlite_sex);
        holder.descText = (TextView) convertView.findViewById(R.id.item_sqlite_desc);
        holder.delText = (TextView) convertView.findViewById(R.id.item_sqlite_del);
        holder.nameText.setText(data.get(position).name);
        holder.sexText.setText(data.get(position).isMale ? "男" : "女");
        holder.sexText.setTextColor(mContext.getResources().getColor(data.get(position).isMale ? R.color.male_txt_color : R.color.female_txt_color));
        holder.descText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class ViewHolder{
        public TextView nameText, sexText, descText, delText;
    }
}
