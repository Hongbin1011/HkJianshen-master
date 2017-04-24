package com.gz.hkjs.app.widget;

import android.content.Context;

/**
 * Company: chuangxinmengxiang-chongqingwapushidai
 * User: HongBin(Hongbin1011@gmail.com)
 * Date: 2017-04-14
 * Time: 14:46
 */
class Util {
    /**
     * 把密度转换为像素
     */
    static int dip2px(Context context, float px) {
        final float scale = getScreenDensity(context);
        return (int) (px * scale + 0.5);
    }
    /**
     * 得到设备的密度
     */
    private static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

}
