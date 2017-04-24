package com.gz.hkjs.app.jm;


import com.alibaba.fastjson.JSONObject;
import com.gz.hkjs.app.app.AppConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wbb on 2016/5/25.
 */
public class JmTools {

    public final static String DKAETYA = "@#-*￥djcc^&+#@*";
    public static String DJCCUSERKEY = "cxmxdjcc";//加入md5验算
    public static String DJCCURLKEY = "xwCMX2349WEREWisicms";

    /**
     * 输入加密 T
     *
     * @param str
     * @return
     */
    public static String DKAETYA16(String str) {

        String a = str.substring(0, 16);

        return a;
    }

    /**
     * 加密方法
     *
     * @param hashMap
     * @return
     */
    public static HashMap<String, String> JM(HashMap<String, String> hashMap, String service) {

        hashMap.put("version", AppConstant.APPNAME);
        hashMap.put("service", service);
        String s = JmTools.NRJM(hashMap, service);
        hashMap.put("signkey", s);
        hashMap.put("s", s);
        Iterator iter = hashMap.keySet().iterator();
        JSONObject jsonObject = new JSONObject();

        while (iter.hasNext()) {
            String key = (String) iter.next();
            jsonObject.put(key, hashMap.get(key));
        }

        long time = System.currentTimeMillis() / 1000;
        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("data", JmTools.encryptionEnhanced(time + "", jsonObject.toJSONString()));
        hashMap1.put("T", time + "");
        return hashMap1;
    }

    /**
     * json 解密
     *
     * @param object
     * @return
     */
    public static String DecryptKey(JSONObject object) {

        String result = object.getString("result");

        String t = object.getString("T");

        String keyString = Security.md5(t + DKAETYA);

        String key = DKAETYA16(keyString);

        //解密
        String data = Security.decrypt(key, result);

        return data;
    }

    /**
     * 字符串 加密
     *
     * @param time
     * @param mString
     * @return
     */
    public static String encryptionEnhanced(String time, String mString) {

        String key = Security.md5(time + DKAETYA);

        String key16 = DKAETYA16(key);

        String date = Security.encrypt(key16, mString);

        return date;
    }

    /**
     * 加密 url signkey
     *
     * @param hashMap
     * @param method
     * @return
     */
    public static String NRJM(HashMap<String, String> hashMap, String method) {
        List<String> listKey = new ArrayList();
        Iterator it = hashMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            listKey.add(key);
        }
        List<String> listSort = Sorting.listSort(listKey);
        String key = "";
        StringBuilder sb = new StringBuilder();
        for (String string : listSort) {
            String name = hashMap.get(string);
            key = sb.append(name).toString();
        }
        return Security.md5(key + DJCCURLKEY);
    }

    /**
     * 用户加密
     *
     * @param hashMap
     * @param methond
     * @return
     */
    public static String USER_NRJM(HashMap<String, String> hashMap, String methond) {
//        hashMap.put("service", methond);
        List<String> listKey = new ArrayList();
        Iterator it = hashMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            listKey.add(key);
        }
        List<String> listSort = Sorting.listSort(listKey);
        String key = "";
        StringBuilder sb = new StringBuilder();
        for (String string : listSort) {
            String name = hashMap.get(string);
            key = sb.append(name).toString();
        }
        return Security.md5(key + DJCCUSERKEY);
    }

}
