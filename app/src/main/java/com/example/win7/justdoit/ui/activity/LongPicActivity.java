package com.example.win7.justdoit.ui.activity;

import android.graphics.BitmapRegionDecoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.win7.justdoit.R;
import com.example.win7.justdoit.ui.utils.FileHelper;
import com.example.win7.justdoit.ui.utils.JDILog;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by win7 on 2016/11/1.
 */
public class LongPicActivity extends AppCompatActivity{
    private ImageView imageView;
    private BitmapRegionDecoder bitmapRegionDecoder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.long_pic_test);
        imageView = (ImageView) findViewById(R.id.long_pic_test_image);
        findViewById(R.id.long_pic_test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JDILog.i(" click");
                try {
                    JDILog.i(" 生成BitmapRegionDecoder ");
                     bitmapRegionDecoder = BitmapRegionDecoder.newInstance(FileHelper.getSDPath() + "/test.png", false);
                    JDILog.i(" 生成BitmapRegionDecoder 完毕");
                    JDILog.i(bitmapRegionDecoder.getHeight() + "----原始图的高度");
                    JDILog.i(bitmapRegionDecoder.getWidth() + "----原始图的宽度");
                    JDILog.i(imageView.getMeasuredHeight() + "----ImageView的高度");
                    JDILog.i(imageView.getMeasuredWidth() + "----ImageView的宽度");

                }catch (Exception e){

                }


            }

        });
    }
}
