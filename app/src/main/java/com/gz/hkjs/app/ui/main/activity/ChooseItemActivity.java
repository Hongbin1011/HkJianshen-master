package com.gz.hkjs.app.ui.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.irecyclerview.baseadapter.SectionedSpanSizeLookup;
import com.gz.hkjs.app.R;
import com.gz.hkjs.app.api.ApiConstants;
import com.gz.hkjs.app.app.AppConstant;
import com.gz.hkjs.app.bean.ChooseItem;
import com.gz.hkjs.app.ui.main.adapter.ChooseItemAdapter;
import com.gz.hkjs.app.ui.main.contract.ChooseItemContract;
import com.gz.hkjs.app.ui.main.model.ChooseItemModel;
import com.gz.hkjs.app.ui.main.presenter.ChooseItemPresenter;
import com.gz.hkjs.app.util.JMClassChoose;
import com.gz.hkjs.app.widget.StepViewHorizontal;
import com.jaydenxiao.common.base.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/10.
 */

public class ChooseItemActivity extends BaseActivity<ChooseItemPresenter, ChooseItemModel> implements ChooseItemContract.View {


    @Bind(R.id.id_back)
    ImageView idBack;
//    @Bind(R.id.id_irc_choose)
//    RecyclerView idIrcChoose;
    @Bind(R.id.id_total_toolbar_title)
    TextView idTotalToolbarTitle;
    @Bind(R.id.id_right_tv)
    TextView idRightTv;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.hflowview1) StepViewHorizontal mStepViewHorizontal;
    private String[] titles = {"基础", "进阶", "强化", "突破", "极限"};

    private ChooseItemAdapter mAdapter;

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ChooseItemActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in_choose,
                R.anim.fade_out_choose);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chooseitem;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        idBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        idTotalToolbarTitle.setText("自选课程");
        idRightTv.setText("筛选");

        mStepViewHorizontal.setProgress(1,5,titles);
//        mStepViewHorizontal.setChecked(true); //设置可点击  默认不开点击
        mStepViewHorizontal.setStepClickListener(new StepViewHorizontal.OnStepClickListener() {
            @Override
            public void mOnStepClickListener(View v, int step) {
                mStepViewHorizontal.setProgress(step,5,titles);
            }
        });
        //mPresenter.getChooseItemRequest(JMClassChoose.MyJMClass(AppConstant.OS, AppConstant.VERSION_NUM, ApiConstants.API_Choose_LIST));
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void returnChooseItemData(List<ChooseItem.DataBeanX> videoSummaries) {
        if (videoSummaries != null) {
            mAdapter = new ChooseItemAdapter(this);
            GridLayoutManager manager = new GridLayoutManager(this, 4);
            //设置header
            manager.setSpanSizeLookup(new SectionedSpanSizeLookup(mAdapter, manager));
            //idIrcChoose.setLayoutManager(manager);
            //idIrcChoose.setAdapter(mAdapter);
            mAdapter.setData(videoSummaries);
        }
    }
}
