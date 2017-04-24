package com.gz.hkjs.app.ui.detail.model;

import com.gz.hkjs.app.api.Api;
import com.gz.hkjs.app.api.HostType;
import com.gz.hkjs.app.bean.FindDetail;
import com.gz.hkjs.app.ui.detail.contract.FindDetailContract;
import com.jaydenxiao.common.baserx.RxSchedulers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/3/21.
 */

public class FindsDetailModel implements FindDetailContract.Model {
    @Override
    public Observable<FindDetail.DataBean> getOneFindsData(HashMap<String,String> map) {
        return Api.getDefault(HostType.NORMAL_HOSTTYPE).getNewDetail(Api.getCacheControl(), map)
                .map(new Func1<FindDetail, FindDetail.DataBean>() {
                    @Override
                    public FindDetail.DataBean call(FindDetail findDetail) {
                        return findDetail.getData();
                    }
                })
                .compose(RxSchedulers.<FindDetail.DataBean>io_main());
    }
}
