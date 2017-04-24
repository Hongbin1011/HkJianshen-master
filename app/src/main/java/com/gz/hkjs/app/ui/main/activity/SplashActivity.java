package com.gz.hkjs.app.ui.main.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Handler;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.gz.hkjs.app.R;
import com.gz.hkjs.app.api.Api;
import com.gz.hkjs.app.api.ApiConstants;
import com.gz.hkjs.app.api.HostType;
import com.gz.hkjs.app.app.AppConstant;
import com.gz.hkjs.app.bean.Version;
import com.gz.hkjs.app.util.JMClass;
import com.gz.hkjs.app.util.JMClassVersion;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.lxh.userlibrary.UserCenter;

import butterknife.Bind;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * des:启动页
 * Created by xsf
 * on 2016.09.15:16
 */
public class SplashActivity extends BaseActivity {
    @Bind(R.id.iv_logo)
    ImageView ivLogo;
//    @Bind(R.id.tv_name)
//    TextView tvName;

    @Override
    public int getLayoutId() {
        return R.layout.activty_splash;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        SetTranslanteBar();
        UserCenter.init(this.getApplicationContext(),"@#-*￥cxmx^&+#@*","cxmxjianshen","10","3");//TODO

        new Handler().postDelayed(new Runnable() {
            public void run() {
                //execute the task
                //GetVersionData();
                LoginActivity.startAction(SplashActivity.this);
                finish();
            }
        }, 4000);

//        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
//        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
//        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
//        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(tvName, alpha, scaleX, scaleY);
//        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(ivLogo, alpha, scaleX, scaleY);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(objectAnimator1, objectAnimator2);
//        animatorSet.setInterpolator(new AccelerateInterpolator());
//        animatorSet.setDuration(3000);
//        animatorSet.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//                GetVersionData();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });
//        animatorSet.start();
    }

    private void GetVersionData() {
        Api.getDefault(HostType.NORMAL_HOSTTYPE)
                .getVersionData(Api.getCacheControl(), JMClassVersion.MyJMClass("", AppConstant.VERSION_NUM, ""))
                .subscribeOn(Schedulers.io())//在非UI线程中获取数据
                .observeOn(AndroidSchedulers.mainThread())//在UI线程中执行更新UI
                .subscribe(new Observer<Version>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--------versiThrowableon---------:" + e);

                    }

                    @Override
                    public void onNext(Version version) {
                        System.out.println("--------version---------:" + version.getData().getApp_version());
                        //SPUtils.setSharedStringData(SplashActivity.this, AppConstant.VERSION, version.getData().getApp_version());
                        MainActivity.startAction(SplashActivity.this);
                        finish();
                    }
                });
    }

}
