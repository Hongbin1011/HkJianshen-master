package com.gz.hkjs.app.ui.main.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.gz.hkjs.app.bean.RecipesSummary;
import com.gz.hkjs.app.ui.detail.activity.RecipesDetailActivity;
import com.gz.hkjs.app.ui.main.contract.RecipesListContract;
import com.gz.hkjs.app.ui.main.model.RecipesListModel;
import com.gz.hkjs.app.ui.main.presenter.RecipesListPresenter;
import com.gz.hkjs.app.util.JMClass;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/15.
 */

public class RecipesMainFragment extends BaseFragment<RecipesListPresenter, RecipesListModel> implements RecipesListContract.View, OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.irc_recipes)
    IRecyclerView ircRecipes;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private int mStartPage = 1;

    private CommonRecycleViewAdapter<RecipesSummary.DataBean> adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recipes;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {

        ntb.setTvLeftVisiable(false);
        ntb.setTitleText(getString(R.string.recipes));

        adapter = new CommonRecycleViewAdapter<RecipesSummary.DataBean>(getContext(), R.layout.item_recipes) {
            @Override
            public void convert(ViewHolderHelper helper, final RecipesSummary.DataBean recipes) {
                final ImageView imageView = helper.getView(R.id.id_item_recips_img);
                RelativeLayout rootrl = helper.getView(R.id.rl_root);

                Glide.with(mContext).load(recipes.getLogo_url())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .placeholder(com.jaydenxiao.common.R.drawable.ic_image_loading)
                        .error(com.jaydenxiao.common.R.drawable.ic_empty_picture)
                        .centerCrop().override(1090, 1090 * 3 / 4)
                        .crossFade().into(imageView);

                TextView title = helper.getView(R.id.id_item_recips_title);
                title.setText(recipes.getName());
                TextView energy = helper.getView(R.id.id_item_recips_energy);
                energy.setText(recipes.getEnergy() + "千卡");
                TextView tag1 = helper.getView(R.id.id_item_recips_foodtag1);
                TextView tag2 = helper.getView(R.id.id_item_recips_foodtag2);
                List<RecipesSummary.DataBean.FoodTagsBean> tags = recipes.getFood_tags();

                if (tags.size() > 1) {
                    tag1.setText(tags.get(0).getName());
                    tag2.setText(tags.get(1).getName());
                } else {
                    tag1.setText(tags.get(0).getName());
                    tag2.setVisibility(View.INVISIBLE);
                }

                TextView dsc = helper.getView(R.id.id_item_recips_dsc);
                dsc.setText(recipes.getDsc());

                rootrl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RecipesDetailActivity.startAction(mContext, imageView, recipes.getId(), recipes.getLogo_url());
                    }
                });
            }
        };

        ircRecipes.setAdapter(adapter);
        ircRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        ircRecipes.setOnLoadMoreListener(this);
        ircRecipes.setOnRefreshListener(this);

        mPresenter.getRecipesListDataRequest(JMClass.MyJMClass(AppConstant.OS, String.valueOf(mStartPage), AppConstant.VERSION_NUM, ApiConstants.API_RECIPES_LIST));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRxManager.post(AppConstant.NEWS_LIST_TO_TOP, "");
            }
        });
    }

    @Override
    public void onRefresh() {
        adapter.getPageBean().setRefresh(true);
        //发起请求
        mStartPage = 1;
        ircRecipes.setRefreshing(true);
        mPresenter.getRecipesListDataRequest(JMClass.MyJMClass(AppConstant.OS, String.valueOf(mStartPage), AppConstant.VERSION_NUM, ApiConstants.API_RECIPES_LIST));
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        adapter.getPageBean().setRefresh(false);
        //发起请求
        ircRecipes.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        mPresenter.getRecipesListDataRequest(JMClass.MyJMClass(AppConstant.OS, String.valueOf(mStartPage), AppConstant.VERSION_NUM, ApiConstants.API_RECIPES_LIST));
    }

    @Override
    public void showLoading(String title) {
        if (adapter.getPageBean().isRefresh())
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
    }

    @Override
    public void stopLoading() {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
    }

    @Override
    public void showErrorTip(String msg) {
        if (adapter.getPageBean().isRefresh()) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
            loadedTip.setTips(msg);
            ircRecipes.setRefreshing(false);
        } else {
            ircRecipes.setLoadMoreStatus(LoadMoreFooterView.Status.ERROR);
        }
    }

    @Override
    public void returnRecipesListData(List<RecipesSummary.DataBean> recipesSummaries) {
        if (recipesSummaries != null) {
            mStartPage += 1;
            System.out.println("-------recipesSummaries------dddd--:" + recipesSummaries.size());
            if (adapter.getPageBean().isRefresh()) {
                ircRecipes.setRefreshing(false);
                adapter.replaceAll(recipesSummaries);
            } else {
                if (recipesSummaries.size() > 0) {
                    ircRecipes.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    adapter.addAll(recipesSummaries);
                } else {
                    ircRecipes.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                }
            }
        }
    }

    @Override
    public void scrolltoTop() {
        ircRecipes.smoothScrollToPosition(0);
    }
}
