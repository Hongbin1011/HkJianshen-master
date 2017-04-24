package com.gz.hkjs.app.ui.detail.model;

import com.gz.hkjs.app.api.Api;
import com.gz.hkjs.app.api.HostType;
import com.gz.hkjs.app.bean.FindSummary;
import com.gz.hkjs.app.bean.RecipesDetail;
import com.gz.hkjs.app.ui.detail.contract.RecipesDetailContract;
import com.jaydenxiao.common.baserx.RxSchedulers;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/3/16.
 */

public class RecipesDetailModel implements RecipesDetailContract.Model {
    @Override
    public Observable<RecipesDetail.DataBean> getRecipesDetailData(HashMap<String,String> map) {
        return Api.getDefault(HostType.NORMAL_HOSTTYPE)
                .getRecipesDetail(Api.getCacheControl(), map)
                .map(new Func1<RecipesDetail, RecipesDetail.DataBean>() {
                    @Override
                    public RecipesDetail.DataBean call(RecipesDetail recipesDetail) {
                        return recipesDetail.getData();
                    }
                })
                .compose(RxSchedulers.<RecipesDetail.DataBean>io_main());
    }
}
