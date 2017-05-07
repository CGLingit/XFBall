package com.example.cglin.xfball;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
/**
 * Created by Administrator on 2017/4/24.
 *
 * 新建一个MyAccessibilityService继承AccessibilityService
 * 参考的资料链接：http://www.itkeyword.com/doc/0948012785352754490/AccessibilityService-Android
 *               http://www.jianshu.com/p/b8d3c0723582?from=jiantop.com
 *               http://www.jianshu.com/p/4cd8c109cdfb
 */
/*辅助服务*/
public class MyAccessibilityService extends AccessibilityService {
    private static MyAccessibilityService context;
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i("dly", "onAccessibilityEvent");
        context = this;
    }
    @Override
    public void onInterrupt() {
        // TODO Auto-generated method stub
    }
    @Override
    protected void onServiceConnected() {
        Log.i("dly", "onServiceConnected");
        context = this;
        super.onServiceConnected();
    }
    @Override
    public void onCreate() {
        Log.i("dly", "onCreate");
        context = this;
        super.onCreate();
    }
    public static void home() {
        context.performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME);
        MyWindowManager.removeBigWindow(context);
        MyWindowManager.createSmallWindow(context);
    }
    public static void recents(){
        context.performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
    }
}
