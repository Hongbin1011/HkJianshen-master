package com.gz.hkjs.app.ui.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import com.gz.hkjs.app.R;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.StatusBarCompat;

import butterknife.Bind;

/**
 * Company: chuangxinmengxiang-chongqingwapushidai
 * User: HongBin(Hongbin1011@gmail.com)
 * Date: 2017-04-21
 * Time: 10:54
 */

public class FinishTrainingActivity extends BaseActivity {

    private PopupWindow popupWindow;

    @Bind(R.id.id_finishtrain_bt)
    Button idFinishtrainBt;


    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, FinishTrainingActivity.class);
        activity.startActivity(intent);
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_finish_train;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        idFinishtrainBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPopupWindow();
                popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    /**
     * 获取PopipWinsow实例
     */
    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopupWindow();
        }
    }

    private void initPopupWindow() {
        //获取自定义布局文件activity_pop_bottom.xml 布局文件
        final View popipWindow_view = getLayoutInflater().inflate(R.layout.activity_pop_bottom, null, false);
        //创建Popupwindow 实例，200，LayoutParams.MATCH_PARENT 分别是宽高
        popupWindow = new PopupWindow(popipWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        //设置动画效果
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        //点击其他地方消失
        popipWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popipWindow_view != null && popipWindow_view.isShown()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
    }
}
