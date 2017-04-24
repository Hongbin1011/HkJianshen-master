package com.gz.hkjs.app.ui.main.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gz.hkjs.app.R;
import com.gz.hkjs.app.app.AppConstant;
import com.gz.hkjs.app.bean.TabEntity;
import com.gz.hkjs.app.ui.main.fragment.FindMainFragment;
import com.gz.hkjs.app.ui.main.fragment.MineMainFragment;
import com.gz.hkjs.app.ui.main.fragment.RecipesMainFragment;
import com.gz.hkjs.app.ui.main.fragment.TrainingMainFragment;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.LogUtils;

import java.util.ArrayList;

import butterknife.Bind;
import rx.functions.Action1;


public class MainActivity extends BaseActivity {


    private static final String TAG = "MainActivity";
//    private long exitTime = 0;

    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;

    private String[] mTitles = {"训练", "食谱", "发现", "我"};
    private int[] mIconUnselectIds = {
            R.mipmap.s_xunlian, R.mipmap.s_shipu, R.mipmap.s_faxian, R.mipmap.s_me};
    private int[] mIconSelectIds = {
            R.mipmap.xunlian, R.mipmap.shipu, R.mipmap.faxian, R.mipmap.me};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private TrainingMainFragment trainingMainFragment;
    private RecipesMainFragment recipesMainFragment;
    private FindMainFragment findMainFragment;
    private MineMainFragment mineMainFragment;
    private static int tabLayoutHeight;

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                com.jaydenxiao.common.R.anim.fade_out);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        //初始化菜单
        initTab();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化frament
        initFragment(savedInstanceState);
        tabLayout.measure(0, 0);
        tabLayoutHeight = tabLayout.getMeasuredHeight();

        //监听菜单显示或隐藏
        mRxManager.on(AppConstant.MENU_SHOW_HIDE, new Action1<Boolean>() {
            @Override
            public void call(Boolean hideOrShow) {
                startAnimation(hideOrShow);
            }
        });
    }

    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            trainingMainFragment = (TrainingMainFragment) getSupportFragmentManager().findFragmentByTag("trainingMainFragment");
            recipesMainFragment = (RecipesMainFragment) getSupportFragmentManager().findFragmentByTag("recipesMainFragment");
            findMainFragment = (FindMainFragment) getSupportFragmentManager().findFragmentByTag("findMainFragment");
            mineMainFragment = (MineMainFragment) getSupportFragmentManager().findFragmentByTag("mineMainFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            trainingMainFragment = new TrainingMainFragment();
            recipesMainFragment = new RecipesMainFragment();
            findMainFragment = new FindMainFragment();
            mineMainFragment = new MineMainFragment();

            transaction.add(R.id.fl_body, trainingMainFragment, "trainingMainFragment");
            transaction.add(R.id.fl_body, recipesMainFragment, "recipesMainFragment");
            transaction.add(R.id.fl_body, findMainFragment, "findMainFragment");
            transaction.add(R.id.fl_body, mineMainFragment, "mineMainFragment");
        }

        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    private void initTab() {

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
        //LogUtils.logd("主页菜单position" + position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //训练
            case 0:
                transaction.hide(recipesMainFragment);
                transaction.hide(findMainFragment);
                transaction.hide(mineMainFragment);
                transaction.show(trainingMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            //食谱
            case 1:
                transaction.hide(trainingMainFragment);
                transaction.hide(findMainFragment);
                transaction.hide(mineMainFragment);
                transaction.show(recipesMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            //发现
            case 2:
                transaction.hide(mineMainFragment);
                transaction.hide(trainingMainFragment);
                transaction.hide(recipesMainFragment);
                transaction.show(findMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            //我
            case 3:
                transaction.hide(trainingMainFragment);
                transaction.hide(recipesMainFragment);
                transaction.hide(findMainFragment);
                transaction.show(mineMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    /**
     * 菜单显示隐藏动画
     *
     * @param showOrHide
     */
    private void startAnimation(boolean showOrHide) {
        final ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha;
        if (!showOrHide) {
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 1, 0);
        } else {
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 0, 1);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height = (int) valueAnimator.getAnimatedValue();
                tabLayout.setLayoutParams(layoutParams);
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator, alpha);
        animatorSet.start();
    }

    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //奔溃前保存位置
       // LogUtils.loge("onSaveInstanceState进来了1");
        if (tabLayout != null) {
            //LogUtils.loge("onSaveInstanceState进来了2");
            outState.putInt(AppConstant.HOME_CURRENT_TAB_POSITION, tabLayout.getCurrentTab());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
