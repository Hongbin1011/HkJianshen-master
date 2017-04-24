package com.gz.hkjs.app.util;

import com.gz.hkjs.app.jm.JmTools;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/31.
 */

public class JMClass {

    public static HashMap<String, String> MyJMClass(String os, String page, String version, String service) {

        HashMap<String, String> mJmh = new HashMap<>();
        mJmh.put("os", os);
        mJmh.put("page", page);
        mJmh.put("version", version);
        return mJmh;
        //return JmTools.JM(mJmh, service);
    }
}
