package com.sridhar.taskapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by 2136 on 11/9/2017.
 */

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private boolean[] thumbnailsselection;
    private String[] checked_items;
    private int width, height;

    public CustomAdapter(CheckItemActivity checkItemActivity, String[] items) {
        context = checkItemActivity;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        checked_items = items;
        thumbnailsselection = new boolean[checked_items.length];
        for (int i = 0; i < checked_items.length; i++) {

            thumbnailsselection[i] = false;
        }
    }

    @Override
    public int getCount() {
        return checked_items.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_check, parent, false);
            holder.textView = (TextView) convertView.findViewById(R.id.item_txt);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.check_box);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setId(position);
        holder.checkBox.setId(position);
        holder.textView.setText(checked_items[position]);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = holder.checkBox.getId();
                if (thumbnailsselection[id]) {
                    holder.checkBox.setChecked(false);
                    thumbnailsselection[id] = false;
                    holder.checkBox.setChecked(false);
                    if (!thumbnailsselection[id]) {
                        Utils.select_items.remove(checked_items[position]);

                    }

                } else {


                    thumbnailsselection[id] = true;
                    holder.checkBox.setChecked(true);
                    if (thumbnailsselection[id]) {

                        Utils.select_items.add(checked_items[position]);


                    }


                }


            }
        });


        holder.checkBox.setChecked(thumbnailsselection[position]);
        return convertView;
    }

    class ViewHolder {
        TextView textView;
        CheckBox checkBox;
    }
}
