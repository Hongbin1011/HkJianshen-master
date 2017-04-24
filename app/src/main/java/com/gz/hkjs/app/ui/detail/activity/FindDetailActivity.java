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
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gz.hkjs.app.R;
import com.gz.hkjs.app.api.ApiConstants;
import com.gz.hkjs.app.app.AppConstant;
import com.gz.hkjs.app.bean.FindDetail;
import com.gz.hkjs.app.ui.detail.contract.FindDetailContract;
import com.gz.hkjs.app.ui.detail.model.FindsDetailModel;
import com.gz.hkjs.app.ui.detail.presenter.FindsDetailPresenter;
import com.gz.hkjs.app.util.JMClassDetail;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.commonutils.TimeUtil;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/16.
 */

public class FindDetailActivity extends BaseActivity<FindsDetailPresenter, FindsDetailModel> implements FindDetailContract.View {

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
    @Bind(R.id.id_webview)
    WebView idWebview;
    @Bind(R.id.id_item_find_ctime)
    TextView idItemFindCtime;
    @Bind(R.id.id_item_find_title)
    TextView idItemFindTitle;

    private String postId;
    private String mNewsTitle;

    /**
     * 入口
     *
     * @param mContext
     * @param postId
     */
    public static void startAction(Context mContext, View view, String postId, String imgUrl) {
        Intent intent = new Intent(mContext, FindDetailActivity.class);
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
        return R.layout.activity_find_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        SetTranslanteBar();
        postId = getIntent().getStringExtra(AppConstant.NEWS_POST_ID);
        mPresenter.getOneFindsDataRequest(JMClassDetail.MyJMClass(postId, AppConstant.OS, AppConstant.VERSION_NUM, ApiConstants.API_FIND_DETAIL));
        setSupportActionBar(toolbar);
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
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.action_web_view:
//                                NewsBrowserActivity.startAction(NewsDetailActivity.this, mShareLink, mNewsTitle);
//                                break;
//                            case R.id.action_browser:
//                                Intent intent = new Intent();
//                                intent.setAction("android.intent.action.VIEW");
//                                if (canBrowse(intent)) {
//                                    Uri uri = Uri.parse(mShareLink);
//                                    intent.setData(uri);
//                                    startActivity(intent);
//                                }
//                                break;
//                        }
//                ic_back true;
//            }
//        });
    }

    @Override
    public void returnOneFindData(final FindDetail.DataBean findDetail) {

        mNewsTitle = findDetail.getTitle();
        String newsTime = TimeUtil.formatDate(findDetail.getCtime());
        setToolBarLayout(mNewsTitle);
        setNewsDetailPhotoIv(getIntent().getStringExtra(AppConstant.NEWS_IMG_RES));
        idItemFindCtime.setText(newsTime);
        idItemFindTitle.setText(mNewsTitle);
        sinitWebView(idWebview);

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
                        idWebview.loadData(getHtmlData(findDetail.getContent()), "text/html; charset=UTF-8", null);
                    }
                }));
    }

    private void setToolBarLayout(String newsTitle) {
        toolbarLayout.setTitle(newsTitle);
        toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));
        toolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.primary_text_white));
    }

    private void setNewsDetailPhotoIv(String imgSrc) {
        Glide.with(this).load(imgSrc)
                .fitCenter()
                .error(com.jaydenxiao.common.R.drawable.ic_empty_picture)
                .crossFade().into(newsDetailPhotoIv);
    }

    private String getHtmlData(String bodyHTML) {

        bodyHTML = bodyHTML.replace("&gt;", ">");
        bodyHTML = bodyHTML.replace("&lt;", "<");
        bodyHTML = bodyHTML.replace("&nbsp;", " ");
        bodyHTML = bodyHTML.replace("&quot;", "\"");
        bodyHTML = bodyHTML.replace("&#39;", "'");
        bodyHTML = bodyHTML.replace("", "");
        bodyHTML = bodyHTML.replace("</P><P>", "\n");
        bodyHTML = bodyHTML.replace("<BR>", "\n");

        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{width:100% !important; min-width:100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body><div style='width:100%;over-flow:hidden;'>" + bodyHTML + "</div></body></html>";
    }

    /**
     * 初始化webView
     *
     * @param webView
     */
    private void sinitWebView(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAppCacheEnabled(true);
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
