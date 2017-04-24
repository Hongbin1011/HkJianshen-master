package com.gz.hkjs.app.ui.main.model;

import com.gz.hkjs.app.api.Api;
import com.gz.hkjs.app.api.HostType;
import com.gz.hkjs.app.bean.UserHomeData;
import com.gz.hkjs.app.bean.VideoData;
import com.gz.hkjs.app.ui.main.contract.TraningListContract;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.commonutils.TimeUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by Administrator on 2017/3/16.
 */

public class TrainingListModel implements TraningListContract.Model {



    @Override
    public Observable<UserHomeData.DataBean> getUserHomeDataListData(HashMap<String, String> map) {
        return Api.getDefault(HostType.NORMAL_HOSTTYPE)
                .getHomeDataList(Api.getCacheControl(), map)
                .map(new Func1<UserHomeData, UserHomeData.DataBean>() {
                    @Override
                    public UserHomeData.DataBean call(UserHomeData userHomeData) {
                        return userHomeData.getData();
                    }
                }).compose(RxSchedulers.<UserHomeData.DataBean>io_main());
    }
}
