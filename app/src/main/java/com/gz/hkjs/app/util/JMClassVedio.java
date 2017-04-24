package com.gz.hkjs.app.util;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/31.
 */

public class JMClassVedio {


    public static HashMap<String, String> MyJMClass(String os, String page, String version, String load_type, String service) {

        HashMap<String, String> mJmh = new HashMap<>();
        mJmh.put("os", os);
        mJmh.put("page", page);
        mJmh.put("version", version);
        mJmh.put("load_type", load_type);
        return mJmh;
        //return JmTools.JM(mJmh, service);
    }
}
