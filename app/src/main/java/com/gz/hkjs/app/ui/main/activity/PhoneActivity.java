package com.gz.hkjs.app.ui.main.activity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gz.hkjs.app.R;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.MineTitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 更换手机号 lwy
 */

public class PhoneActivity extends BaseActivity {
    @Bind(R.id.mtb_phone)
    MineTitleBar mineTitleBar;
    //    @Bind(R.id.iv_title_back)
//    ImageView ivTitleBack;
//    @Bind(R.id.tv_title_change)
//    TextView tvTitleChange;
//    @Bind(R.id.tv_title_right)
//    TextView tvTitleRight;
    @Bind(R.id.et_set_phone)
    EditText etSetPhone;
    @Bind(R.id.et_set_auth_code)
    EditText etSetAuthCode;
    @Bind(R.id.tv_get_auth_code)
    TextView tvGetAuthCode;

    @Override
    public int getLayoutId() {
        return R.layout.mine_change_phone;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mineTitleBar.setLeftBack(true);
        mineTitleBar.setOnLeftImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mineTitleBar.setTitleText(getString(R.string.my_password_change));
//        ivTitleBack.setVisibility(View.VISIBLE);
//        tvTitleChange.setText(getString(R.string.my_phone_change));
        etSetPhone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});//手机号最多11位

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({ R.id.et_set_phone, R.id.et_set_auth_code, R.id.tv_get_auth_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_set_phone:
                break;
            case R.id.et_set_auth_code:
                break;
            case R.id.tv_get_auth_code:
                break;
        }
    }
}
