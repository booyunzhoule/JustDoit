package com.example.win7.justdoit.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.win7.justdoit.R;
import com.example.win7.justdoit.ui.adapter.SQLiteTestAdapter;
import com.example.win7.justdoit.db.SQLiteTestHelper;
import com.example.win7.justdoit.ui.dialog.SQLiteAddUserDialog;

/**
 * Created by win7 on 2016/10/30.
 */
public class SQLiteTestActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView sqliteListView;
    private TextView createBtn, queryBtn, delBtn, addBtn, modifyBtn, updateBtn;
    private SQLiteTestAdapter adapter;
    private SQLiteTestHelper helper;
    public final static String SQLITE_NAME = "sqlite_test.db";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_test);
        sqliteListView = (ListView) findViewById(R.id.sqlite_content);
        createBtn = (TextView) findViewById(R.id.sqlite_tool_create);
        queryBtn = (TextView) findViewById(R.id.sqlite_tool_query);
        delBtn = (TextView) findViewById(R.id.sqlite_tool_del);
        addBtn = (TextView) findViewById(R.id.sqlite_tool_add);
        modifyBtn = (TextView) findViewById(R.id.sqlite_tool_modify);
        updateBtn = (TextView) findViewById(R.id.sqlite_tool_update);
        helper = new SQLiteTestHelper(this, SQLITE_NAME, null, 1);
        sqliteListView.setAdapter(adapter);
        adapter = new SQLiteTestAdapter(this);
        createBtn.setOnClickListener(this);
        queryBtn.setOnClickListener(this);
        delBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        modifyBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        createBtn.setVisibility(View.GONE);
        queryBtn.setVisibility(View.VISIBLE);
        delBtn.setVisibility(View.VISIBLE);
        addBtn.setVisibility(View.VISIBLE);
        modifyBtn.setVisibility(View.VISIBLE);
        updateBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sqlite_tool_create:
                onCreateData();
                break;
            case R.id.sqlite_tool_query:
                onQueryData();
                break;
            case R.id.sqlite_tool_del:
                onDelData();
                break;
            case R.id.sqlite_tool_add:
                onAddData();
                break;
            case R.id.sqlite_tool_modify:
                onModifyData();
                break;
            case R.id.sqlite_tool_update:
                onUpdateData();
                break;
        }
    }

    /**
     * 更新数据
     */
    private void onUpdateData() {

    }

    /**
     * 修改数据
     */
    private void onModifyData() {

    }

    /**
     * 添加数据
     */
    private void onAddData() {
        SQLiteAddUserDialog dialog = new SQLiteAddUserDialog(this);
        dialog.setHelper(helper);
        dialog.show();
    }

    /**
     * 删除数据
     */
    private void onDelData() {

    }

    /**
     * 查询数据库
     */
    private void onQueryData() {

    }

    /**
     * 创建数据库
     */
    private void onCreateData() {
        helper.getWritableDatabase();
    }
}
