package com.gz.hkjs.app.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gz.hkjs.app.R;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.MineTitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 账号安全 lwy
 */

public class AccountActivity extends BaseActivity {


    @Bind(R.id.mtb_account)
    MineTitleBar mtbAccount;
    @Bind(R.id.civ_account_phone)
    ImageView civAccountPhone;
    @Bind(R.id.tv_account_phone)
    TextView tvAccountPhone;
    @Bind(R.id.bt_change_phone)
    Button btChangePhone;
    @Bind(R.id.bt_change_password)
    Button btChangePassword;
    @Bind(R.id.tv_phone_exit_login)
    TextView tvPhoneExitLogin;
    @Bind(R.id.ll_account_top)
    LinearLayout llAccountTop;
    @Bind(R.id.tv_account_version)
    TextView tvAccountVersion;
    private String phone="";

    @Override
    public int getLayoutId() {
        return R.layout.mine_account;
    }

    @Override
    public void initPresenter() {


    }

    @Override
    public void initView() {
        mtbAccount.setLeftBack(true);
        mtbAccount.setOnLeftImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mtbAccount.setTitleText(getString(R.string.my_account));
        if (!TextUtils.isEmpty(phone) && phone.length() > 7) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < phone.length(); i++) {
                char c = phone.charAt(i);
                if (i >= 3 && i <= 7) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            tvAccountPhone.setText(sb.toString());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.mtb_account, R.id.bt_change_phone, R.id.bt_change_password, R.id.tv_phone_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mtb_account:
                break;
            case R.id.bt_change_phone:
                startActivity(new Intent(this,PhoneActivity.class));
                break;
            case R.id.bt_change_password:
                startActivity(new Intent(this,PasswordActivity.class));
                break;
            case R.id.tv_phone_exit_login:
                break;
        }
    }
}
