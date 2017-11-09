package com.sridhar.taskapp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DoneActivity extends AppCompatActivity {
    private ListView listView1;
    private CustomAdapter1 customAdapter;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        listView1 = (ListView) findViewById(R.id.final_list_view);

        customAdapter = new CustomAdapter1(DoneActivity.this, Utils.select_items);
        listView1.setAdapter(customAdapter);

        findViewById(R.id.modify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater2 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater2.inflate(R.layout.customlist, null);

                ListView listView = (ListView) view1.findViewById(R.id.listView1);
                listView.setAdapter(new ListAdapters(DoneActivity.this, Utils.select_items));

                builder = new AlertDialog.Builder(DoneActivity.this);
                builder.setView(view1);
                builder.setTitle("Selected Items");
                alertDialog = builder.create();
                Button button = (Button) view1.findViewById(R.id.change_button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("msg1",""+Utils.select_items.size());
                        customAdapter = new CustomAdapter1(DoneActivity.this, Utils.select_items);
                        listView1.setAdapter(customAdapter);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });
    }

    public class CustomAdapter1 extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private ArrayList<String> checked_bitmaps = new ArrayList<>();

        public CustomAdapter1(DoneActivity doneActivity, ArrayList<String> select_items) {
            this.checked_bitmaps.clear();
            this.context = doneActivity;
            inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.checked_bitmaps.addAll(select_items);
        }

        @Override
        public int getCount() {
            return checked_bitmaps.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_check, parent, false);
                holder.textView = (TextView) convertView.findViewById(R.id.item_txt);
                holder.checkBox = (CheckBox) convertView.findViewById(R.id.check_box);
                holder.checkBox.setVisibility(View.GONE);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setId(position);
            holder.textView.setText(checked_bitmaps.get(position));
            return convertView;
        }
    }

    class ViewHolder {
        TextView textView;
        CheckBox checkBox;
    }

    private class ListAdapters extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private boolean[] thumbnailsselection;
        private ArrayList<String> checked_bitmaps = new ArrayList<>();

        public ListAdapters(DoneActivity onClickListener, ArrayList<String> select_items) {
            this.checked_bitmaps.clear();
            this.context = onClickListener;
            inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.checked_bitmaps.addAll(select_items);
            thumbnailsselection = new boolean[select_items.size()];
            if (checked_bitmaps.size() > 0) {
                for (int i = 0; i < checked_bitmaps.size(); i++) {

                    thumbnailsselection[i] = true;
                }
            }
        }

        @Override
        public int getCount() {
            return this.checked_bitmaps.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView imagesView;
            CheckBox checkBox;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_check, parent, false);
                holder.imagesView = (TextView) convertView.findViewById(R.id.item_txt);
                holder.checkBox = (CheckBox) convertView.findViewById(R.id.check_box);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.imagesView.setId(position);
            holder.checkBox.setId(position);
            holder.imagesView.setText(checked_bitmaps.get(position));

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = holder.checkBox.getId();
                    if (thumbnailsselection[id]) {
                        holder.checkBox.setChecked(false);
                        thumbnailsselection[id] = false;
                        if (!thumbnailsselection[id]) {

                            Utils.select_items.remove(checked_bitmaps.get(position));
                            Log.e("msg2",""+Utils.select_items.size());
                        }

                    } else {


                        thumbnailsselection[id] = true;
                        holder.checkBox.setChecked(true);
                        if (thumbnailsselection[id]) {


                            Utils.select_items.add(checked_bitmaps.get(position));
                            Log.e("msg3",""+Utils.select_items.size());
                        }


                    }


                }
            });


            holder.checkBox.setChecked(thumbnailsselection[position]);
            return convertView;
        }
    }
}
