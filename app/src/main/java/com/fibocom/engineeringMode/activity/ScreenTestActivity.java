package com.fibocom.engineeringMode.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.fibocom.engineeringMode.db.DBopenhelper;
import com.fibocom.engineeringMode.utils.Util;
import com.fibocom.myapplication.R;

public class ScreenTestActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private int currentImageIndex = 0;
    private int[] imageResources={
            R.mipmap.colorful,
            R.mipmap.red,
            R.mipmap.green,
            R.mipmap.blue,
            R.mipmap.white,
            R.mipmap.black,
            R.mipmap.black_white,
            R.mipmap.black_white_slow,
            R.mipmap.colorful
    };
    private final int LENGTH = imageResources.length;
    private Button button1;
    private Button button2;

//    Thread hideButtonThread1 = new Thread(()-> {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        //发布隐藏按钮到主线程
//        handler.post(hideButtonThread1);
//        button1.setVisibility(View.INVISIBLE);
//        button2.setVisibility(View.INVISIBLE);
//    });
    private Runnable hideButtonsRunnable = new Runnable() {
        @Override
        public void run() {
            button1.setVisibility(View.INVISIBLE);
            button2.setVisibility(View.INVISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_test);


        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        View view = findViewById(android.R.id.content);
        view.setBackgroundResource(imageResources[currentImageIndex]);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    if(button1.getVisibility() == View.VISIBLE&&button2.getVisibility() == View.VISIBLE){
                        button1.setVisibility(View.INVISIBLE);
                        button2.setVisibility(View.INVISIBLE);
                    }else {
                        button1.setVisibility(View.VISIBLE);
                        button2.setVisibility(View.VISIBLE);
                        handler.postDelayed(hideButtonsRunnable, 5000);
                    }
                }
//
//                // 移除之前的定时器
//                handler.removeCallbacks(hideButtonsRunnable);
//                // 添加新的定时器，5秒后执行
//                handler.postDelayed(hideButtonsRunnable, 2000);

                return true;
            }
        });
        //按退出键返回上一个页面
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //按继续键播放图片
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hideButtonThread1重新启动，重新计时
//                hideButtonThread1.start();
                //移除之前的定时器
                handler.removeCallbacks(hideButtonsRunnable);
                //添加新的定时器，5秒后执行
                handler.postDelayed(hideButtonsRunnable, 5000);
                if (currentImageIndex < LENGTH - 1) {
                    ++currentImageIndex;
                    view.setBackgroundResource(imageResources[currentImageIndex]);
                } else {
                    currentImageIndex = 0;
                    DBopenhelper dBopenhelper = DBopenhelper.getInstance(ScreenTestActivity.this, "test.db", null, 1);
                    Util.alertDialog("测试完成", "屏幕测试完成", ScreenTestActivity.this, "screen", dBopenhelper, 0,
                            (dialog, which) -> finish());

                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}