package com.lxh.userlibrary.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created with Android Studio.
 * Authour：Eric_chan
 * Date：2016/10/9 10:01
 * Des： (状态栏工具,处理魅族FlymeOS4.x/Android4.4.4)
 */
public class StatusBarUtils {
    public static int getHeight(Context context) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        Log.d(StatusBarUtils.class.getSimpleName(), "statusBarHeight--->" + statusBarHeight);
        if (isFlymeOs4x()) {
            return 2 * statusBarHeight;
        }

        return statusBarHeight;
    }

    public static boolean isFlymeOs4x() {
        String sysVersion = android.os.Build.VERSION.RELEASE;
        if ("4.4.4".equals(sysVersion)) {
            String sysIncrement = android.os.Build.VERSION.INCREMENTAL;
            String displayId = android.os.Build.DISPLAY;
            if (!TextUtils.isEmpty(sysIncrement)) {
                return sysIncrement.contains("Flyme_OS_4");
            } else {
                return displayId.contains("Flyme OS 4");
            }
        }
        return false;
    }
}

