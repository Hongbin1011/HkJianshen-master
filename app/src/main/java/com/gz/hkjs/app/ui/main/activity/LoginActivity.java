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
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/11.
 */

public class LoginActivity extends BaseActivity {


    @Bind(R.id.id_back)
    ImageView idBack;
    @Bind(R.id.id_total_toolbar_title)
    TextView idTotalToolbarTitle;
    @Bind(R.id.id_login_button)
    Button idLoginButton;
    @Bind(R.id.id_quick_reg)
    TextView idQuickReg;

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                com.jaydenxiao.common.R.anim.fade_out);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activty_login;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!CacheActivity.activityList.contains(LoginActivity.this)) {
            CacheActivity.addActivity(LoginActivity.this);
        }
    }

    @Override
    public void initView() {
        idBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        idTotalToolbarTitle.setText("登录");
        idLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.startAction(LoginActivity.this);
                finish();
            }
        });

        idQuickReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegActivity.startAction(LoginActivity.this);
            }
        });

    }
}
