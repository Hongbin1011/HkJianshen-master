package com.gz.hkjs.app.util;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/4/10.
 */

public class JMClassChoose {

    public static HashMap<String, String> MyJMClass(String os, String version, String service) {

        HashMap<String, String> mJmh = new HashMap<>();
        mJmh.put("os", os);
        mJmh.put("version", version);
        return mJmh;
        //return JmTools.JM(mJmh, service);
    }
}
