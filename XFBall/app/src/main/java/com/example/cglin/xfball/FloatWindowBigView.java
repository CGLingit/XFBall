package com.example.cglin.xfball;

import android.accessibilityservice.AccessibilityService;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import static android.content.Context.DEVICE_POLICY_SERVICE;
import static android.view.accessibility.AccessibilityEvent.*;

/**
 * Created by Administrator on 2017/4/12.
 */

public class FloatWindowBigView extends LinearLayout {

    ComponentName mAdminName;
    DevicePolicyManager mDPM;
    /**
     * 记录大悬浮窗的宽度
     */
    public static int viewWidth;

    /**
     * 记录大悬浮窗的高度
     */
    public static int viewHeight;

    public FloatWindowBigView(final Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.activity_float_window_big, this);
        View view = findViewById(R.id.float_window_big);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;
        Button back = (Button) findViewById(R.id.back);
        Button lock=(Button)findViewById(R.id.lock);
        Button home=(Button)findViewById(R.id.home);
        Button recent=(Button)findViewById(R.id.recent);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击返回的时候，移除大悬浮窗，创建小悬浮窗
                MyWindowManager.removeBigWindow(context);
                MyWindowManager.createSmallWindow(context);
            }
        });
        lock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //设备管理器，启动系统服务DEVICE_POLICY_SERVICE
                DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService(DEVICE_POLICY_SERVICE);
                MyWindowManager.removeBigWindow(context);
                MyWindowManager.createSmallWindow(context);
                //实现锁屏
                devicePolicyManager.lockNow();
            }
        });
       /*
         实现home键
       */
        home.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MyWindowManager.removeBigWindow(context);
                MyWindowManager.createSmallWindow(context);
                MyAccessibilityService.home();

            }
        });
        /*
           实现RECTENT

         */
        recent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MyWindowManager.removeBigWindow(context);
                MyWindowManager.createSmallWindow(context);
                MyAccessibilityService.recents();

            }
        });
    }

}
