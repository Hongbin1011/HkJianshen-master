package com.gz.hkjs.app.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gz.hkjs.app.R;
import com.jaydenxiao.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收藏lwy
 */

public class CollectActivity extends BaseActivity {

    @Bind(R.id.iv_collect_back)
    ImageView ivCollectBack;
    @Bind(R.id.rb_recipe)
    RadioButton rbRecipe;
    @Bind(R.id.rb_article)
    RadioButton rbArticle;
    @Bind(R.id.rg_check)
    RadioGroup rgCheck;
    @Bind(R.id.vp_collect)
    ViewPager vpCollect;
    private String[] mTitles = {"食谱", "文章"};
    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Override
    public int getLayoutId() {
        return R.layout.mine_collect;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initTable();
        initFragment();
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();

    }

    private void initTable() {
        titleList = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.iv_collect_back, R.id.rb_recipe, R.id.rb_article, R.id.rg_check,R.id.vp_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_collect_back:
                finish();
                break;
            case R.id.rg_check:
                rgCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_recipe:
                                titleList.add(mTitles[0]);
                                break;
                            case R.id.rb_article:
                                titleList.add(mTitles[1]);
                                break;
                        }
                    }
                });
                break;
        }
    }

}
