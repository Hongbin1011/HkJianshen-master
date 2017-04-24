package com.gz.hkjs.app.ui.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.gz.hkjs.app.R;
import com.gz.hkjs.app.util.CacheActivity;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.StatusBarCompat;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/4/13.
 */

public class JibenInformitionTwo extends BaseActivity {


    @Bind(R.id.button_jinen_2)
    Button buttonJinen2;

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, JibenInformitionTwo.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                com.jaydenxiao.common.R.anim.fade_out);
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(){
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.start_color));
    }
    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color){
        StatusBarCompat.setStatusBarColor(this,color);
    }
    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar(){
        StatusBarCompat.translucentStatusBar(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!CacheActivity.activityList.contains(JibenInformitionTwo.this)) {
            CacheActivity.addActivity(JibenInformitionTwo.this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_jibenxinxi_two;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        buttonJinen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JibenInformitionThree.startAction(JibenInformitionTwo.this);
            }
        });
    }

}
