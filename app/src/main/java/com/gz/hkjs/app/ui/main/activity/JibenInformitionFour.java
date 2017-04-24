package com.gz.hkjs.app.ui.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ggx.ruler_lib.RulerView;
import com.ggx.ruler_lib.RulerView2;
import com.gz.hkjs.app.R;
import com.gz.hkjs.app.util.CacheActivity;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.StatusBarCompat;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/4/13.
 */

public class JibenInformitionFour extends BaseActivity {


    @Bind(R.id.button_jinen_4)
    Button buttonJinen4;
    @Bind(R.id.id_jiben_tall)
    TextView idJibenTall;
    @Bind(R.id.rv1)
    RulerView rv1;
    @Bind(R.id.id_jiben_weight)
    TextView idJibenWeight;
    @Bind(R.id.rv2)
    RulerView2 rv2;

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, JibenInformitionFour.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                com.jaydenxiao.common.R.anim.fade_out);
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.start_color));
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
        return R.layout.activity_jibenxinxi_four;
    }


    @Override
    public void initPresenter() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!CacheActivity.activityList.contains(JibenInformitionFour.this)) {
            CacheActivity.addActivity(JibenInformitionFour.this);
        }
    }

    @Override
    public void initView() {

        buttonJinen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.startAction(JibenInformitionFour.this);
                finish();
                CacheActivity.finishSingleActivityByClass(JibenInformitionOne.class);
                CacheActivity.finishSingleActivityByClass(JibenInformitionTwo.class);
                CacheActivity.finishSingleActivityByClass(JibenInformitionThree.class);
                CacheActivity.finishSingleActivityByClass(LoginActivity.class);
                CacheActivity.finishSingleActivityByClass(RegActivity.class);
            }
        });


        rv1.setCallback(new RulerView.RulerCallback() {
            @Override
            public void resultNum(int num) {
                idJibenTall.setText("" + num);
            }
        });

        rv2.setCallback(new RulerView2.RulerCallback() {
            @Override
            public void resultNum(int num) {
                idJibenWeight.setText("" + num);
            }
        });
    }
}
