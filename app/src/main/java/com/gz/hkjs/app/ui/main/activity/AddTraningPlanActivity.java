package com.gz.hkjs.app.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gz.hkjs.app.R;
import com.gz.hkjs.app.api.ApiConstants;
import com.gz.hkjs.app.app.AppConstant;
import com.gz.hkjs.app.bean.VideoData;
import com.gz.hkjs.app.ui.detail.activity.TrainingVideoDetailActivity;
import com.gz.hkjs.app.ui.main.contract.AddTrainingPlanContract;
import com.gz.hkjs.app.ui.main.model.AddTrainingPlanModel;
import com.gz.hkjs.app.ui.main.presenter.AddTrainingPlanPresenter;
import com.gz.hkjs.app.util.JMClassVedio;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.LoadingTip;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/10.
 */

public class AddTraningPlanActivity extends BaseActivity<AddTrainingPlanPresenter, AddTrainingPlanModel> implements AddTrainingPlanContract.View, OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.irc_traning_all)
    IRecyclerView ircTraningAll;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.id_back)
    ImageView idBack;
    @Bind(R.id.id_right_tv)
    TextView idAllvedioChoose;
    @Bind(R.id.id_total_toolbar_title)
    TextView idTotalToolbarTitle;

    CommonRecycleViewAdapter<VideoData.DataBean> traningListAdapter;


    private int mStartPage = 1;

    /**
     * 入口
     *
     * @param mContext
     */
    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, AddTraningPlanActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_alltrainingvedio;
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
        idAllvedioChoose.setText("筛选");
        idAllvedioChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseItemActivity.startAction(AddTraningPlanActivity.this);
            }
        });

        idTotalToolbarTitle.setText("自选课程");
        traningListAdapter = new CommonRecycleViewAdapter<VideoData.DataBean>(this, R.layout.item_train_all_vedio) {
            @Override
            public void convert(ViewHolderHelper helper, final VideoData.DataBean dataBean) {
                final ImageView bg = helper.getView(R.id.item_train_all_img);
                FrameLayout fm = helper.getView(R.id.rl_root_train_all);
                TextView title = helper.getView(R.id.item_train_all_title);
                TextView time = helper.getView(R.id.item_train_all_time);
                TextView energy = helper.getView(R.id.item_train_all_energy);

                Glide.with(mContext).load(dataBean.getLogo_url())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .placeholder(com.jaydenxiao.common.R.drawable.ic_image_loading)
                        .error(com.jaydenxiao.common.R.drawable.ic_empty_picture)
                        .centerCrop().override(1090, 1090 * 3 / 4)
                        .fitCenter()
                        .crossFade().into(bg);

                title.setText(dataBean.getName());
                time.setText(dataBean.getWtime() + "分钟");
                energy.setText(dataBean.getEnergy() + "70千卡");

                fm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TrainingVideoDetailActivity.startAction(mContext, bg, dataBean.getId(), dataBean.getLogo_url());
                    }
                });
            }
        };

        ircTraningAll.setAdapter(traningListAdapter);
        ircTraningAll.setLayoutManager(new LinearLayoutManager(this));
        ircTraningAll.setOnRefreshListener(this);
        ircTraningAll.setOnLoadMoreListener(this);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRxManager.post(AppConstant.NEWS_LIST_TO_TOP, "");
            }
        });

        //数据为空才重新发起请求
        if (traningListAdapter.getSize() <= 0) {
            //发起请求
            mStartPage = 1;
            mPresenter.getVideosScreenRequest(JMClassVedio.MyJMClass(AppConstant.OS, String.valueOf(mStartPage), AppConstant.VERSION_NUM, "1", ApiConstants.API_VEDIO_LIST));
        }
    }

    @Override
    public void onRefresh() {
        mStartPage = 1;
        //发起请求
        ircTraningAll.setRefreshing(true);
        mPresenter.getVideosScreenRequest(JMClassVedio.MyJMClass(AppConstant.OS, String.valueOf(mStartPage), AppConstant.VERSION_NUM, "1", ApiConstants.API_VEDIO_LIST));
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        traningListAdapter.getPageBean().setRefresh(false);
        //发起请求
        ircTraningAll.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        mPresenter.getVideosScreenRequest(JMClassVedio.MyJMClass(AppConstant.OS, String.valueOf(mStartPage), AppConstant.VERSION_NUM, "1", ApiConstants.API_VEDIO_LIST));
    }

    @Override
    public void showLoading(String title) {
        if (traningListAdapter.getPageBean().isRefresh()) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
        }
    }

    @Override
    public void stopLoading() {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
    }

    @Override
    public void showErrorTip(String msg) {
        if (traningListAdapter.getPageBean().isRefresh()) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
        }
    }

    @Override
    public void returnVideosScreenData(List<VideoData.DataBean> videoDatas) {
        if (videoDatas != null) {
            mStartPage += 1;
            if (traningListAdapter.getPageBean().isRefresh()) {
                ircTraningAll.setRefreshing(false);
                traningListAdapter.replaceAll(videoDatas);
            } else {
                if (videoDatas.size() > 0) {
                    ircTraningAll.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    traningListAdapter.addAll(videoDatas);
                } else {
                    ircTraningAll.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                }
            }
        }
    }

    @Override
    public void scrolltoTop() {
        ircTraningAll.smoothScrollToPosition(0);
    }
}
