package com.sridhar.taskapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{


    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fruits_button).setOnClickListener(this);
        findViewById(R.id.tvs_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.fruits_button:
                intent=new Intent(MainActivity.this,CheckItemActivity.class);
                intent.putExtra("items",getResources().getString(R.string.fruits));
                break;

            case R.id.tvs_button:
                intent=new Intent(MainActivity.this,CheckItemActivity.class);
                intent.putExtra("items",getResources().getString(R.string.tvs));
                break;
        }

        startActivity(intent);
    }
}
