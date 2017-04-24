package com.gz.hkjs.app.ui.main.model;

import com.gz.hkjs.app.api.Api;
import com.gz.hkjs.app.api.HostType;
import com.gz.hkjs.app.bean.RecipesSummary;
import com.gz.hkjs.app.ui.main.contract.RecipesListContract;
import com.jaydenxiao.common.baserx.RxSchedulers;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/3/16.
 */

public class RecipesListModel implements RecipesListContract.Model {

    @Override
    public Observable<List<RecipesSummary.DataBean>> getRecipesListData(HashMap<String,String> map) {
        return Api.getDefault(HostType.NORMAL_HOSTTYPE)
                .getRecipesList(Api.getCacheControl(), map)
                .map(new Func1<RecipesSummary, List<RecipesSummary.DataBean>>() {
                    @Override
                    public List<RecipesSummary.DataBean> call(RecipesSummary RecipesSummary) {
                        return RecipesSummary.getData();
                    }
                })
                .compose(RxSchedulers.<List<RecipesSummary.DataBean>>io_main());
    }
}
