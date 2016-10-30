package com.example.win7.justdoit.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.win7.justdoit.R;
import com.example.win7.justdoit.db.SQLiteTestHelper;
import com.example.win7.justdoit.ui.activity.SQLiteTestActivity;
import com.example.win7.justdoit.ui.api.CommonDefines;

/**
 * Created by win7 on 2016/10/30.
 */

public class SQLiteAddUserDialog extends AppCompatDialog {
    private EditText userEdit, descEdit;
    private Button confirmButton;
    private Spinner spinner;
    private SQLiteTestHelper helper;
    private Context mContext;

    public void setHelper(SQLiteTestHelper helper) {
        this.helper = helper;
    }

    public SQLiteAddUserDialog(final Context context) {
        super(context,R.style.DefaultDialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = ((Activity)mContext).getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_sqlite_add,null);
        userEdit = (EditText) view.findViewById(R.id.dialog_sqlite_user);
        descEdit = (EditText) view.findViewById(R.id.dialog_sqlite_desc);
        spinner = (Spinner) view.findViewById(R.id.dialog_sqlite_sex);
        confirmButton = (Button) view.findViewById(R.id.dialog_sqlite_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", userEdit.getText().toString().trim());
                values.put("desc", descEdit.getText().toString().trim());
                values.put("sex", CommonDefines.USER_MALE);
                db.insert(SQLiteTestActivity.SQLITE_NAME,null,values);
                Toast.makeText(mContext, "添加数据成功" , Toast.LENGTH_SHORT).show();
                values.clear();
                dismiss();
            }
        });
        setContentView(view);
    }
}
