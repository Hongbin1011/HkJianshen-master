/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.gz.hkjs.app.api;

public class ApiConstants {
    public static final String NETEAST_HOST = "http://tapi.jianshen.1122.com/";
    public static final String ENDDETAIL_URL = "?service=";

    // 发现列表
    public static final String API_VERSION = ENDDETAIL_URL + "Version.Index";
    public static final String API_FIND_LIST = ENDDETAIL_URL + "Information.Infolist";
    public static final String API_RECIPES_LIST = ENDDETAIL_URL + "Foods.Get";
    public static final String API_FIND_DETAIL = ENDDETAIL_URL + "Information.Detail";
    public static final String API_RECIPES_DETAIL = ENDDETAIL_URL + "Foods.Detail";
    public static final String API_VEDIO_LIST = ENDDETAIL_URL + "Lists.Get";
    public static final String API_Choose_LIST = ENDDETAIL_URL + "Lists.Screen";
    public static final String API_TRAIN_VEDIO_DETAIL_LIST = ENDDETAIL_URL + "Lists.Detail";
    public static final String API_USER_HOME_DATA = ENDDETAIL_URL + "Homes.Get";



    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.NORMAL_HOSTTYPE:
                host = NETEAST_HOST;
                break;
            case HostType.VEDIONEWS_HOSTTYPE:
                host = "http://c.m.163.com/";
                break;
            default:
                host = "";
                break;
        }
        return host;
    }
}
