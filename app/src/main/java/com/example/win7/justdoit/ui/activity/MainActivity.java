package com.example.win7.justdoit.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.win7.justdoit.R;
import com.example.win7.justdoit.ui.adapter.MainListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ArrayList<String> itemTitleData;
    private MainListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.main_list_view);
        itemTitleData = new ArrayList<>();
        addItem();
        adapter = new MainListAdapter(itemTitleData, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void addItem() {
        itemTitleData.add("数据库测试");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position){
            case 0: // 数据库测试
                intent = new Intent(this,SQLiteTestActivity.class);
                startActivity(intent);
                break;
        }

    }
}
