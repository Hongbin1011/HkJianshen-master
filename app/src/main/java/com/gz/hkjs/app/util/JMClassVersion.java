package com.gz.hkjs.app.util;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/4/1.
 */

public class JMClassVersion {

    public static HashMap<String, String> MyJMClass(String mPackage, String version, String appname) {

        HashMap<String, String> mJmh = new HashMap<>();
        mJmh.put("package", mPackage);
        mJmh.put("version", version);
        mJmh.put("appname", appname);
        return mJmh;
        //return JmTools.JM(mJmh, service);
    }
}
