package com.example.win7.justdoit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.win7.justdoit.R;

import java.util.ArrayList;

/**
 * Created by win7 on 2016/10/30.
 */

public class MainListAdapter extends BaseAdapter {
    private ArrayList<String> itemList;
    private Context mContext;

    public MainListAdapter(ArrayList<String> itemList, Context mContext) {
        this.itemList = itemList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_main,null);
            viewHolder = new ViewHolder();
            viewHolder.itemName = (TextView) convertView.findViewById(R.id.item_main_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.itemName.setText(itemList.get(position));
        return convertView;
    }

    static class ViewHolder {
        TextView itemName ;
    }

}
