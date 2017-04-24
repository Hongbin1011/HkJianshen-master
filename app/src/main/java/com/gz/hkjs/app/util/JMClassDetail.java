package com.gz.hkjs.app.util;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/31.
 */

public class JMClassDetail {


    public static HashMap<String, String> MyJMClass(String id, String os, String version, String service) {

        HashMap<String, String> mJmh = new HashMap<>();
        mJmh.put("id", id);
        mJmh.put("os", os);
        mJmh.put("version", version);
        return mJmh;
        //return JmTools.JM(mJmh, service);
    }
}
