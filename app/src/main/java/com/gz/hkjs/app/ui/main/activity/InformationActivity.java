package com.gz.hkjs.app.ui.main.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gz.hkjs.app.R;
import com.gz.hkjs.app.util.ChangeAddressPopwindow;
import com.gz.hkjs.app.util.CircleImageView;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.MineTitleBar;
import com.orhanobut.logger.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资料 lwy
 */

public class InformationActivity extends BaseActivity {


    //年月日选择
    private final static String TAG = "TimeDate";
    //获取日期格式器对象
    DateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd");

    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);

    @Bind(R.id.mtb_information)
    MineTitleBar mineTitleBar;

    @Bind(R.id.civ_information_user)
    CircleImageView civInformationUser;
    @Bind(R.id.rl_mine_head)
    RelativeLayout rlMineHead;
    @Bind(R.id.tv_information_user_name)
    EditText tvInformationUserName;
    @Bind(R.id.rl_mine_name)
    RelativeLayout rlMineName;
    @Bind(R.id.rb_sex_nan)
    RadioButton rbSexNan;
    @Bind(R.id.rb_sex_nv)
    RadioButton rbSexNv;
    @Bind(R.id.tv_mine_weight)
    TextView tvMineWeight;
    @Bind(R.id.tv_information_user_weight)
    EditText tvInformationUserWeight;
    @Bind(R.id.rl_mine_weight)
    RelativeLayout rlMineWeight;
    @Bind(R.id.tv_mine_height)
    TextView tvMineHeight;
    @Bind(R.id.tv_information_user_height)
    EditText tvInformationUserHeight;
    @Bind(R.id.rl_mine_height)
    RelativeLayout rlMineHeight;
    @Bind(R.id.tv_information_user_data)
    TextView tvInformationUserData;
    @Bind(R.id.tv_information_user_city)
    TextView tvInformationUserCity;
    @Bind(R.id.sv_information_user)
    ScrollView svInformationUser;
    @Bind(R.id.tv_information_save)
    TextView tvInformationSave;


    @Override
    public int getLayoutId() {
        return R.layout.mine_information;
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
        mineTitleBar.setTitleText(getString(R.string.my_information));

        //身高字体颜色
        String mWeight = "身高(cm)";
        SpannableString spannableString = new SpannableString(mWeight);
        spannableString.setSpan(new TextAppearanceSpan(this, R.style.infor_text_style), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextAppearanceSpan(this, R.style.mine_information_text_unit), 2, mWeight.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvMineWeight.setText(spannableString, TextView.BufferType.SPANNABLE);
        //体重字体颜色
        String mHeight = "体重(kg)";
        SpannableString spannableString1 = new SpannableString(mHeight);
        spannableString1.setSpan(new TextAppearanceSpan(this, R.style.infor_text_style), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString1.setSpan(new TextAppearanceSpan(this, R.style.mine_information_text_unit), 2, mHeight.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvMineHeight.setText(spannableString1, TextView.BufferType.SPANNABLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            upDateDate();

        }
    };

    private void upDateDate() {
        tvInformationUserData.setText(fmtDate.format(dateAndTime.getTime()));
    }

    @OnClick({R.id.rl_mine_head, R.id.tv_information_user_name, R.id.rb_sex_nan, R.id.rb_sex_nv, R.id.tv_information_user_weight, R.id.tv_information_user_height, R.id.tv_information_user_data, R.id.tv_information_user_city, R.id.tv_information_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_mine_head:
                break;
            case R.id.tv_information_user_name:
                break;
            case R.id.rb_sex_nan:
                break;
            case R.id.rb_sex_nv:
                break;
            case R.id.tv_information_user_weight:
                break;
            case R.id.tv_information_user_height:
                break;
            case R.id.tv_information_user_data:

                DatePickerDialog dateDlg = new DatePickerDialog(InformationActivity.this,
                        d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                Logger.i("dateDlg = " + dateDlg);

                Logger.i("dateDlg = " + dateDlg + "  dateAndTime = " + dateAndTime);
                dateDlg.show();
                upDateDate();
                break;
            case R.id.tv_information_user_city:

//                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(InformationActivity.this);
//
//                mChangeAddressPopwindow.setAddress("广东", "深圳", "福田区");
//                mChangeAddressPopwindow.showAtLocation(tvInformationUserCity, Gravity.BOTTOM, 0, 0);
//                mChangeAddressPopwindow
//                        .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {
//
//                            @Override
//                            public void onClick(String province, String city, String area) {
//                                // TODO Auto-generated method stub
//
//                                tvInformationUserCity.setText(province + city + area);
//                            }
//                        });
                break;
            case R.id.tv_information_save:
                break;
        }
    }

}
