package com.gz.hkjs.app.ui.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gz.hkjs.app.R;
import com.gz.hkjs.app.app.AppApplication;
import com.gz.hkjs.app.ui.main.activity.AccountActivity;
import com.gz.hkjs.app.ui.main.activity.CollectActivity;
import com.gz.hkjs.app.ui.main.activity.FeedbackActivity;
import com.gz.hkjs.app.ui.main.activity.InformationActivity;
import com.gz.hkjs.app.util.CacheDataManager;
import com.gz.hkjs.app.util.CircleImageView;
import com.gz.hkjs.app.util.DataCleanUtil;
import com.gz.hkjs.app.util.XUtilNet;
import com.gz.hkjs.app.widget.SwitchView;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.baseapp.BaseApplication;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/15.
 */

public class MineMainFragment extends BaseFragment {


    public static final int PHOTOZOOM = 0; // 相册/拍照
    public static final int PHOTOTAKE = 1; // 相册/拍照
    public static final int IMAGE_COMPLETE = 2; // 结果
    @Bind(R.id.ntb_mine)
    NormalTitleBar ntbMine;

    @Bind(R.id.tv_mine_name)
    TextView tvMineName;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.sv_mine_img)
    SwitchView svMineImg;
    @Bind(R.id.tv_cache)
    TextView tvCache;

    SharedPreferences mSharp = AppApplication.getPreferences();
    @Bind(R.id.rl_collect)
    RelativeLayout rlCollect;
    @Bind(R.id.rl_account)
    RelativeLayout rlAccount;
    @Bind(R.id.rl_score)
    RelativeLayout rlScore;
    @Bind(R.id.rl_feedback)
    RelativeLayout rlFeedback;
    @Bind(R.id.civ_user_img)
    CircleImageView civUserImg;
    @Bind(R.id.tv_mine_information)
    TextView tvMineInformation;
    private String cacheSize;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_my;
    }

    @Override
    public void initPresenter() {

        //获取缓存大小（格式化size大小）
        try {
            cacheSize = CacheDataManager.getTotalCacheSize(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvCache.setText(cacheSize);


    }

    @Override
    protected void initView() {

        boolean mSharpBoolean = mSharp.getBoolean("mine_switch", true);
        if (mSharpBoolean) {
            svMineImg.setOpened(mSharpBoolean);
        }
        if (XUtilNet.isWifiConnected()){
            svMineImg.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
                //打开
                @Override
                public void toggleToOn(View view) {
                    svMineImg.setOpened(true);
                    mSharp.edit().putBoolean("mine_switch", true).commit();
                }
                //关闭
                @Override
                public void toggleToOff(View view) {
                    svMineImg.setOpened(false);
                    mSharp.edit().putBoolean("mine_switch", false).commit();
                }
            });
        }


        ntbMine.setTvLeftVisiable(false);
        ntbMine.setTitleText(getString(R.string.mine));

    }


    private void initHeadView() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initHeadView();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void cleanCache() {
        Context context = BaseApplication.getInstance();
        File cacheDir = context.getExternalCacheDir(); // /storage/emulated/0/android/data/cache
        if (cacheDir == null) {
            cacheDir = context.getCacheDir();
        }
//        File imageDiskCache = new File(Utils.getImageCacheDir(this));// ImageLoader缓存在硬件上的图片位置
        DataCleanUtil.cleanInternalCache(getContext());// /data/data/com.xxx.xxx/cache
        if (cacheDir != null && cacheDir.isDirectory()) {
            DataCleanUtil.cleanCustomCache(cacheDir.getPath());
        }

//        if (imageDiskCache != null && imageDiskCache.isDirectory()) {
//            DataCleanUtil.cleanCustomCache(imageDiskCache.getPath());
//        }
        Toast.makeText(getContext(), "已清除" + cacheSize + "缓存", Toast.LENGTH_SHORT).show();
        //更新界面
        tvCache.setText("0.00 B");
    }


    @OnClick({R.id.civ_user_img, R.id.tv_mine_name, R.id.bt_login, R.id.tv_mine_information, R.id.rl_collect, R.id.rl_account, R.id.sv_mine_img, R.id.tv_cache, R.id.rl_score, R.id.rl_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //头像的点击事件
            case R.id.civ_user_img:

                break;
            //名字
            case R.id.tv_mine_name:
                break;
            //登录情况下编辑资料
            case R.id.tv_mine_information:
                startActivity(new Intent(getActivity(), InformationActivity.class));
                break;
            //我的界面未登录情况下显示的登录点击事件
            case R.id.bt_login:
                break;
            //收藏的点击事件
            case R.id.rl_collect:
                startActivity(new Intent(getActivity(), CollectActivity.class));
                break;
            //账号安全
            case R.id.rl_account:
                startActivity(new Intent(getActivity(),AccountActivity.class));
                break;
            //开关wifi
            case R.id.sv_mine_img:

                break;
            //清理缓存的点击事件
            case R.id.tv_cache:
                if (tvCache.getText().toString().equals("0.00 B")) {
                    ToastUitl.showShort("没有缓存可以清理了");
                } else {
                    cleanCache();
                }
                break;
            //评分
            case R.id.rl_score:
                break;
            //意见反馈
            case R.id.rl_feedback:
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
        }
    }

}
