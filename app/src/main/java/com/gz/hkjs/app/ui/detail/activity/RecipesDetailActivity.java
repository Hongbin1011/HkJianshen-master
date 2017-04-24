package com.gz.hkjs.app.ui.detail.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gz.hkjs.app.R;
import com.gz.hkjs.app.api.ApiConstants;
import com.gz.hkjs.app.app.AppConstant;
import com.gz.hkjs.app.bean.RecipesDetail;
import com.gz.hkjs.app.ui.detail.contract.RecipesDetailContract;
import com.gz.hkjs.app.ui.detail.model.RecipesDetailModel;
import com.gz.hkjs.app.ui.detail.presenter.RecipesDetailPresenter;
import com.gz.hkjs.app.util.JMClassDetail;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baserx.RxSchedulers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/16.
 */

public class RecipesDetailActivity extends BaseActivity<RecipesDetailPresenter, RecipesDetailModel> implements RecipesDetailContract.View {

    @Bind(R.id.news_detail_photo_iv)
    ImageView newsDetailPhotoIv;
    @Bind(R.id.mask_view)
    View maskView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.id_item_content_recipes_detail_recilist)
    LinearLayout mLyDetailRecilist;
    @Bind(R.id.id_item_content_recipes_detail_steps)
    LinearLayout mLyDetailSteps;
    @Bind(R.id.id_include_cook_stepnum)
    TextView idIncludeCookStepnum;
    @Bind(R.id.id_item_content_recipes_detail_level)
    TextView mRecipesDetailLevel;
    @Bind(R.id.id_item_content_recipes_detail_ctime)
    TextView mRecipesDetailCtime;
    @Bind(R.id.id_item_content_recipes_detail_energy)
    TextView mRecipesDetailEnergy;

    private String postId;
    private String mNewsTitle;
    private String mEnergy;
    private String ctime;
    private String exaggerate;

    /**
     * 入口
     *
     * @param mContext
     * @param postId
     */
    public static void startAction(Context mContext, View view, String postId, String imgUrl) {
        Intent intent = new Intent(mContext, RecipesDetailActivity.class);
        intent.putExtra(AppConstant.NEWS_POST_ID, postId);
        intent.putExtra(AppConstant.NEWS_IMG_RES, imgUrl);

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
        return R.layout.activity_recipes_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        SetTranslanteBar();
        postId = getIntent().getStringExtra(AppConstant.NEWS_POST_ID);
        mPresenter.getRecipesDetailDataRequest(JMClassDetail.MyJMClass(postId, AppConstant.OS, AppConstant.VERSION_NUM, ApiConstants.API_RECIPES_DETAIL));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });

        //toolbar.inflateMenu(R.menu.news_detail);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.action_web_view:
//                        NewsBrowserActivity.startAction(NewsDetailActivity.this, mShareLink, mNewsTitle);
//                        break;
//                    case R.id.action_browser:
//                        Intent intent = new Intent();
//                        intent.setAction("android.intent.action.VIEW");
////                        if (canBrowse(intent)) {
////                            Uri uri = Uri.parse(mShareLink);
////                            intent.setData(uri);
////                            startActivity(intent);
////                        }
//                        break;
//                }
//                return true;
//            }
//        });
    }

    @Override
    public void returnRecipesDetailData(RecipesDetail.DataBean recipesDetail) {
        idIncludeCookStepnum.setText("(" + recipesDetail.getStep().size() + "步)");
        mNewsTitle = recipesDetail.getName();

        ctime = recipesDetail.getUse_time();
        exaggerate = recipesDetail.getExaggerate();
        mEnergy = recipesDetail.getEnergy();

        if ("0".equals(exaggerate)) {
            mRecipesDetailLevel.setText("初级");
        } else if ("1".equals(exaggerate)) {
            mRecipesDetailLevel.setText("中级");
        } else {
            mRecipesDetailLevel.setText("高级");
        }

        mRecipesDetailCtime.setText(ctime + "分钟");
        mRecipesDetailEnergy.setText(mEnergy + "千卡");
        setNewsDetailPhotoIv(recipesDetail.getLogo_url());
        setToolBarLayout(mNewsTitle);
        setNewsDetailBodyTv(recipesDetail);
    }


    private void setNewsDetailPhotoIv(String imgSrc) {
        Glide.with(this).load(imgSrc)
                .fitCenter()
                .error(com.jaydenxiao.common.R.drawable.ic_empty_picture)
                .crossFade().into(newsDetailPhotoIv);
    }

    private void setToolBarLayout(String newsTitle) {
        toolbarLayout.setTitle(newsTitle);
        toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));
        toolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.primary_text_white));
    }

    private void setNewsDetailBodyTv(final RecipesDetail.DataBean recipesDetail) {
        mRxManager.add(Observable.timer(500, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.<Long>io_main())
                .subscribe(new Subscriber<Long>() {

                    @Override
                    public void onCompleted() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        setRecipesListBody(recipesDetail.getIngredients());
                        setRecipesStepsBody(recipesDetail.getStep());
                    }
                }));
    }

    private void setRecipesListBody(List<RecipesDetail.DataBean.IngredientsBean> ingredients) {
        for (int i = 0; i < ingredients.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.include_reci_list, null);
            TextView tv1 = (TextView) view.findViewById(R.id.id_include_recilist_food);
            TextView tv2 = (TextView) view.findViewById(R.id.id_include_recilist_size);
            View line = view.findViewById(R.id.id_include_recilist_line);

            tv1.setText(ingredients.get(i).getName());
            tv2.setText(ingredients.get(i).getNum());

            if (i == ingredients.size() - 1) {
                line.setVisibility(View.INVISIBLE);
            }
            mLyDetailRecilist.addView(view);
        }
    }

    private void setRecipesStepsBody(List<RecipesDetail.DataBean.StepBean> stepBean) {

        for (int i = 0; i < stepBean.size(); i++) {

            View view = LayoutInflater.from(this).inflate(R.layout.include_cook_step, null);

            TextView step = (TextView) view.findViewById(R.id.id_include_cook_step);
            TextView content = (TextView) view.findViewById(R.id.id_include_cook_content);
            ImageView img = (ImageView) view.findViewById(R.id.id_include_cook_img);
            View line = view.findViewById(R.id.id_include_cook_line);

            step.setText("步骤 " + stepBean.get(i).getPx_num() + ":");
            content.setText(stepBean.get(i).getDsc());

            Glide.with(this).load(stepBean.get(i).getLogo_url())
                    .fitCenter()
                    .centerCrop()
                    .error(com.jaydenxiao.common.R.drawable.ic_empty_picture)
                    .crossFade().into(img);

            if (i == stepBean.size() - 1) {
                line.setVisibility(View.INVISIBLE);
            }
            mLyDetailSteps.addView(view);
        }
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
}
