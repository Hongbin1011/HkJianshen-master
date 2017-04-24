package com.gz.hkjs.app.ui.detail.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CheckableImageButton;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gz.hkjs.app.R;
import com.gz.hkjs.app.api.ApiConstants;
import com.gz.hkjs.app.app.AppConstant;
import com.gz.hkjs.app.bean.TrainVedioDetail;
import com.gz.hkjs.app.ui.detail.contract.TrainingVedioDetailContract;
import com.gz.hkjs.app.ui.detail.model.TrainVedioDetailModel;
import com.gz.hkjs.app.ui.detail.presenter.TrainVedioDetailPresenter;
import com.gz.hkjs.app.util.JMClassDetail;
import com.jaydenxiao.common.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/14.
 */

public class TrainingVideoDetailActivity extends BaseActivity<TrainVedioDetailPresenter, TrainVedioDetailModel> implements TrainingVedioDetailContract.View {


    @Bind(R.id.id_train_detail_vedio_list)
    IRecyclerView idTrainDetailVedioList;
    @Bind(R.id.id_train_detail_starttraining)
    Button idTrainDetailStarttraining;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @Bind(R.id.detail_image)
    ImageView mDetailImage;
    @Bind(R.id.train_detail_lever)
    TextView mTrainDetailLever;
    @Bind(R.id.train_detail_time)
    TextView mTrainDetailTime;
    @Bind(R.id.train_detail_ka)
    TextView mTrainDetailKa;
    @Bind(R.id.app_bar)
    AppBarLayout mAppBar;
    @Bind(R.id.id_training_vediodetail_bigtextview)
    TextView mIdTrainingVediodetailBigtextview;
    @Bind(R.id.train_detail_tool)
    TextView mTrainDetailTool;
    @Bind(R.id.train_detail_suggest)
    TextView mTrainDetailSuggest;
    @Bind(R.id.train_detail_person)
    TextView mTrainDetailPerson;
    @Bind(R.id.train_detail_notice)
    TextView mTrainDetailNotice;
    @Bind(R.id.train_detail_info_show_more_layout)
    LinearLayout mTrainDetailInfoShowMoreLayout;
    @Bind(R.id.btn_show_more)
    CheckableImageButton mBtnShowMore;
    @Bind(R.id.id_training_vediodetail_groupcount)
    TextView mIdTrainingVediodetailGroupcount;
    @Bind(R.id.train_detail_fb)
    FloatingActionButton mTrainDetailFb;

    private String postId;
    private CommonRecycleViewAdapter<TrainVedioDetail.DataBean.StepBean> adapter;
    private boolean mInfoIsShow = false;

    /**
     * 入口
     *
     * @param mContext
     * @param postId
     */
    public static void startAction(Context mContext, View view, String postId, String imgUrl) {
        Intent intent = new Intent(mContext, TrainingVideoDetailActivity.class);
        intent.putExtra("postid", postId);
        intent.putExtra("imgview", imgUrl);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation((Activity) mContext, view, AppConstant.TRANSITION_ANIMATION_NEWS_PHOTOS);
            mContext.startActivity(intent, options.toBundle());
        } else {
            //让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity((Activity) mContext, intent, options.toBundle());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_training_vediodetail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        postId = getIntent().getStringExtra("postid");
        System.out.println("---------postid---------:" + postId);

        mTrainDetailInfoShowMoreLayout.setVisibility(View.GONE);
        mToolbarLayout.setTitle("");
        //通过CollapsingToolbarLayout修改字体颜色
        mToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);//设置收缩后Toolbar上字体的颜色

        adapter = new CommonRecycleViewAdapter<TrainVedioDetail.DataBean.StepBean>(this, R.layout.item_train_vedio_detail) {
            @Override
            public void convert(ViewHolderHelper helper, TrainVedioDetail.DataBean.StepBean stepBean) {

                ImageView imageView = helper.getView(R.id.id_item_trainvedio_detail_pic);

                Glide.with(mContext).load(stepBean.getLogo_url())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .placeholder(com.jaydenxiao.common.R.drawable.ic_image_loading)
                        .error(com.jaydenxiao.common.R.drawable.ic_empty_picture)
                        .centerCrop().override(1090, 1090 * 3 / 4)
                        .crossFade().into(imageView);

                helper.setText(R.id.id_item_trainvedio_detail_title, stepBean.getVideo_name());
                helper.setText(R.id.id_item_trainvedio_detail_num, stepBean.getPx_num());
                TextView textView = helper.getView(R.id.id_item_trainvedio_detail_bottomtxt);

                if (stepBean.getStep_type() != null && !TextUtils.isEmpty(stepBean.getStep_type()) && TextUtils.equals(stepBean.getStep_type(), "1")) {
                    textView.setVisibility(View.GONE);
                    StringBuilder stepInfo = new StringBuilder();
                    stepInfo.append(stepBean.getNum())
                            .append(" x ")
                            .append(stepBean.getFrequency())
                            .append("次");
                    helper.setText(R.id.id_item_trainvedio_detail_content, stepInfo.toString());
                    helper.setVisible(R.id.id_item_trainvedio_detail_content, true);
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("休息  ")
                            .append(stepBean.getNum_time())
                            .append(" ″");
                    helper.setText(R.id.id_item_trainvedio_detail_bottomtxt, stringBuilder.toString());
                    textView.setVisibility(View.VISIBLE);
                    helper.setVisible(R.id.id_item_trainvedio_detail_content, false);
                }


            }
        };


        if (adapter.getSize() <= 0) {
            mPresenter.getTrainVedioDetailDataRequest(JMClassDetail.MyJMClass(postId, AppConstant.OS, AppConstant.VERSION_NUM, ApiConstants.API_RECIPES_DETAIL));
        }


        idTrainDetailVedioList.setAdapter(adapter);
        idTrainDetailVedioList.setLayoutManager(new LinearLayoutManager(this));
        View headView = LayoutInflater.from(this).inflate(R.layout.header_trainingdetail, null);
//        idTrainDetailVedioList.addHeaderView(headView);

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
    public void returnTrainVedioDetailData(TrainVedioDetail trainDetail) {
        if (trainDetail != null) {

            mToolbarLayout.setTitle(trainDetail.getData().getName());
            Glide.with(mContext).load(trainDetail.getData().getLogo_url())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .placeholder(com.jaydenxiao.common.R.drawable.ic_image_loading)
                    .error(com.jaydenxiao.common.R.drawable.ic_empty_picture)
                    .centerCrop().override(1090, 1090 * 3 / 4)
                    .crossFade().into(mDetailImage);
//            mTrainDetailLever.setText(trainDetail.getData().get);

            if (trainDetail.getData().getStep().size() > 0) {
                adapter.addAll(trainDetail.getData().getStep());
            }
        }
    }



    @OnClick({R.id.btn_show_more, R.id.train_detail_fb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_show_more:
                if (mInfoIsShow){
                    mTrainDetailInfoShowMoreLayout.setVisibility(View.GONE);
                    mIdTrainingVediodetailBigtextview.setMaxLines(3);
                    mBtnShowMore.setImageResource(R.mipmap.home_btn_drop2);
                }else {
                    mTrainDetailInfoShowMoreLayout.setVisibility(View.VISIBLE);
                    mIdTrainingVediodetailBigtextview.setMaxLines(6);
                    mBtnShowMore.setImageResource(R.mipmap.home_btn_drop);
                }
                mInfoIsShow = !mInfoIsShow;
                break;
            case R.id.train_detail_fb:
                break;
        }
    }
}
