package com.sridhar.taskapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class CheckItemActivity extends AppCompatActivity
{
    private String item_match;
    private ListView listView;
    private CustomAdapter customAdapter;

    private String[] fruits={"Apple1","Guava2","Juniper berry3","Apple4","Guava5","Juniper berry6"};
    private String[] tvs={"tv1","tv2","tv3","tv4","tv5","tv6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_item);

        listView=(ListView)findViewById(R.id.item_list_view);

        Intent intent = this.getIntent();
        if (intent != null)
            item_match = intent.getStringExtra("items");

        if (item_match.equals(getResources().getString(R.string.fruits)))
        {
            Log.i("msg", "" + item_match);
            customAdapter=new CustomAdapter(CheckItemActivity.this,fruits);
            listView.setAdapter(customAdapter);
        }
        else if (item_match.equals(getResources().getString(R.string.tvs)))
        {
            customAdapter=new CustomAdapter(CheckItemActivity.this,tvs);
            listView.setAdapter(customAdapter);
            Log.i("msg", "" + item_match);
        }


        findViewById(R.id.done_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.select_items.isEmpty())
                {

                }
                else
                {
                    Log.e("msg",""+Utils.select_items.size());
                    startActivity(new Intent(CheckItemActivity.this,DoneActivity.class));
                }
            }
        });
    }
}
