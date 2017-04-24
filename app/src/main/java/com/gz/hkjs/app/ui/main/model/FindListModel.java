package com.gz.hkjs.app.ui.main.model;

import com.gz.hkjs.app.api.Api;
import com.gz.hkjs.app.api.ApiConstants;
import com.gz.hkjs.app.api.HostType;
import com.gz.hkjs.app.bean.FindSummary;
import com.gz.hkjs.app.ui.main.contract.FindListContract;
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

public class FindListModel implements FindListContract.Model {

    /**
     * 获取发现列表
     *
     * @param map
     * @return
     */
    @Override
    public Observable<List<FindSummary.DataBean>> getFindListData(HashMap<String, String> map) {
        return Api.getDefault(HostType.NORMAL_HOSTTYPE).getFindsList(Api.getCacheControl(), map)
                .map(new Func1<FindSummary, List<FindSummary.DataBean>>() {
                    @Override
                    public List<FindSummary.DataBean> call(FindSummary findSummary) {
                        return findSummary.getData();
                    }
                })
                .compose(RxSchedulers.<List<FindSummary.DataBean>>io_main());
    }
}
