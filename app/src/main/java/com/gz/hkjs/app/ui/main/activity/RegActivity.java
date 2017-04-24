package com.gz.hkjs.app.ui.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gz.hkjs.app.R;
import com.gz.hkjs.app.util.CacheActivity;
import com.jaydenxiao.common.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/12.
 */

public class RegActivity extends BaseActivity {


    @Bind(R.id.id_back)
    ImageView idBack;
    @Bind(R.id.id_total_toolbar_title)
    TextView idTotalToolbarTitle;
    @Bind(R.id.id_reg_button)
    Button idRegButton;
    @Bind(R.id.id_reg_xieyi)
    TextView idRegXieyi;


    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, RegActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                com.jaydenxiao.common.R.anim.fade_out);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_reg;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!CacheActivity.activityList.contains(RegActivity.this)) {
            CacheActivity.addActivity(RegActivity.this);
        }
    }

    @Override
    public void initView() {
        idTotalToolbarTitle.setText("注册");
    }

    @OnClick({R.id.id_back, R.id.id_reg_button, R.id.id_reg_xieyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_back:
                finish();
                break;
            case R.id.id_reg_button:
                //MainActivity.startAction(this);
                JibenInformitionOne.startAction(this);
                break;
            case R.id.id_reg_xieyi:

                break;
        }
    }
}
