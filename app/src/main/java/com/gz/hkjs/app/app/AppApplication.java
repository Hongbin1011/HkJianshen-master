package com.gz.hkjs.app.app;

import android.content.Context;
import android.content.SharedPreferences;


import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.gz.hkjs.app.BuildConfig;
import com.jaydenxiao.common.baseapp.BaseApplication;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.TimeUtil;

/**
 * APPLICATION
 */
public class AppApplication extends BaseApplication {
    private static SharedPreferences mPreferences;
    //OSS的Bucket
    public static final String OSS_BUCKET = "cnys-pic";
    //设置OSS数据中心域名或者cname域名
    public static final String OSS_ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
    //key
    public static OSS oss;
    private static final String ACCESS_ID = "LTAI6L8wYzFhiR3R";
    private static final String ACCESS_KEY = "19r0RmmSkxBlWS8M03sKc2fgHwDsQY";
    private static Context mContext;

    public static synchronized SharedPreferences getPreferences() {
        return mPreferences;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化logger
        LogUtils.logInit(BuildConfig.LOG_DEBUG);
        mContext = getApplicationContext();
        //初始化OSS配置
        initOSSConfig();


        mPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);

        addDefaultCache(mPreferences);
    }

    private void initOSSConfig() {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(ACCESS_ID, ACCESS_KEY);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        if (BuildConfig.DEBUG) {
            OSSLog.enableLog();
        }
        oss = new OSSClient(getApplicationContext(), AppApplication.OSS_ENDPOINT, credentialProvider, conf);
    }


    private void addDefaultCache(SharedPreferences mPreferences) {
        String defaultCache = mPreferences.getString("cache", "-1");
        SharedPreferences.Editor editor = mPreferences.edit();
        if (defaultCache.equals("-1")) {
            editor.putString("cache", "0");
            String str = TimeUtil.getSystemTime("yyyyMMdd");
            editor.putString("date", str);
        }
        editor.commit();
    }
}
