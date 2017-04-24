package com.gz.hkjs.app.ui.main.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gz.hkjs.app.R;
import com.gz.hkjs.app.api.ApiConstants;
import com.gz.hkjs.app.app.AppConstant;
import com.gz.hkjs.app.bean.UserHomeData;
import com.gz.hkjs.app.bean.VideoData;
import com.gz.hkjs.app.ui.detail.activity.TrainingVideoDetailActivity;
import com.gz.hkjs.app.ui.main.activity.AddTraningPlanActivity;
import com.gz.hkjs.app.ui.main.activity.FinishTrainingActivity;
import com.gz.hkjs.app.ui.main.activity.TrainDataChartViewActivity;
import com.gz.hkjs.app.ui.main.contract.TraningListContract;
import com.gz.hkjs.app.ui.main.model.TrainingListModel;
import com.gz.hkjs.app.ui.main.presenter.TrainingListPresenter;
import com.gz.hkjs.app.util.JMClassUser;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.LoadingTip;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/15.
 */

public class TrainingMainFragment extends BaseFragment<TrainingListPresenter, TrainingListModel> implements TraningListContract.View, OnRefreshListener {

    @Bind(R.id.irc_traning)
    IRecyclerView ircTraning;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    //普通类型适配器
    CommonRecycleViewAdapter<UserHomeData.DataBean.ListBean> traningListAdapter;

    //返回
    @Bind(R.id.id_back)
    ImageView idBack;
    @Bind(R.id.id_training_toolbar_title)
    ImageView idTrainingToolbarTitle;
    @Bind(R.id.id_training_right_tv)
    ImageView idTrainingRightTv;

    TextView idHomeDataLeiji;
    TextView idHomeDataWancheng;
    TextView idHomeDataLeijiDays;
    TextView idHomeDataQianka;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_traning;
    }

    //初始化
    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {

        traningListAdapter = new CommonRecycleViewAdapter<UserHomeData.DataBean.ListBean>(getContext(), R.layout.item_train_all_vedio) {
            @Override
            public void convert(ViewHolderHelper helper, final UserHomeData.DataBean.ListBean dataBean) {
                FrameLayout fm = helper.getView(R.id.rl_root_train_all);
                final ImageView img = helper.getView(R.id.item_train_all_img);
                TextView title = helper.getView(R.id.item_train_all_title);
                TextView time = helper.getView(R.id.item_train_all_time);
                TextView energy = helper.getView(R.id.item_train_all_energy);

                title.setText(dataBean.getName());
                time.setText(String.valueOf(Double.valueOf(dataBean.getWtime()) / 60).substring(0, String.valueOf(Double.valueOf(dataBean.getWtime()) / 60).indexOf(".")) + "分钟");
                energy.setText(dataBean.getEnergy() + "千卡");


                System.out.println("----------------time-------------" + dataBean.getWtime());
                Glide.with(mContext).load(dataBean.getLogo_url())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .placeholder(com.jaydenxiao.common.R.drawable.ic_image_loading)
                        .error(com.jaydenxiao.common.R.drawable.ic_empty_picture)
                        .centerCrop().override(1090, 1090 * 3 / 4)
                        .fitCenter()
                        .crossFade().into(img);

                fm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TrainingVideoDetailActivity.startAction(getContext(), img, dataBean.getId(), dataBean.getLogo_url());
                    }
                });
            }
        };

        ircTraning.setAdapter(traningListAdapter);
        ircTraning.setLayoutManager(new LinearLayoutManager(getContext()));
        View head = LayoutInflater.from(getContext()).inflate(R.layout.layout_headerview_train, null);
        View foot = LayoutInflater.from(getContext()).inflate(R.layout.layout_footbutton_train, null);

        idHomeDataLeiji = (TextView) head.findViewById(R.id.id_home_data_leiji);
        idHomeDataWancheng = (TextView) head.findViewById(R.id.id_home_data_wancheng);
        idHomeDataLeijiDays = (TextView) head.findViewById(R.id.id_home_data_leiji_days);
        idHomeDataQianka = (TextView) head.findViewById(R.id.id_home_data_qianka);

        ircTraning.addHeaderView(head);
        ircTraning.addFooterView(foot);
        ircTraning.setOnRefreshListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRxManager.post(AppConstant.NEWS_LIST_TO_TOP, "");
            }
        });

        Button add = (Button) foot.findViewById(R.id.id_add_vedio_bt);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTraningPlanActivity.startAction(getContext());
            }
        });

        //数据为空才重新发起请求
        if (traningListAdapter.getSize() <= 0) {
            //发起请求
            mPresenter.getUserHomeDataRequest(JMClassUser.MyJMClass("13", AppConstant.OS, AppConstant.VERSION_NUM, ApiConstants.API_VEDIO_LIST));
        }

        idBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrainDataChartViewActivity.startAction(getContext());
            }
        });

        idTrainingRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到另外页面
                FinishTrainingActivity.startAction(getActivity());
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        //发起请求
        ircTraning.setRefreshing(true);
        mPresenter.getUserHomeDataRequest(JMClassUser.MyJMClass("13", AppConstant.OS, AppConstant.VERSION_NUM, ApiConstants.API_VEDIO_LIST));
    }


    @Override
    public void showLoading(String title) {
        if (traningListAdapter.getPageBean().isRefresh()) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
        }
    }

    //停止刷新
    @Override
    public void stopLoading() {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
    }

    //请求失败后显示错误界面
    @Override
    public void showErrorTip(String msg) {
        if (traningListAdapter.getPageBean().isRefresh()) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
        }
    }

    //请求回调
    @Override
    public void returnUserHomeDataListData(UserHomeData.DataBean homeDataSummaries) {
        if (homeDataSummaries != null) {
            idHomeDataLeiji.setText(homeDataSummaries.getTimes());
            idHomeDataWancheng.setText(homeDataSummaries.getNum());
            idHomeDataLeijiDays.setText(homeDataSummaries.getDay());
            idHomeDataQianka.setText(homeDataSummaries.getEnergy());

            if (traningListAdapter.getPageBean().isRefresh()) {
                ircTraning.setRefreshing(false);
                traningListAdapter.replaceAll(homeDataSummaries.getList());
            } else {
                if (homeDataSummaries.getList().size() > 0) {
                    ircTraning.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    traningListAdapter.addAll(homeDataSummaries.getList());
                } else {
                    ircTraning.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                }
            }
        }
    }

    //上划到顶端
    @Override
    public void scrolltoTop() {
        ircTraning.smoothScrollToPosition(0);
    }

}
