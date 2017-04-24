package com.gz.hkjs.app.ui.main.contract;

import com.gz.hkjs.app.bean.RecipesSummary;
import com.gz.hkjs.app.ui.main.model.RecipesListModel;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/3/16.
 */

public interface RecipesListContract {

    interface Model extends BaseModel {
        Observable<List<RecipesSummary.DataBean>> getRecipesListData(HashMap<String,String> map);
    }

    interface View extends BaseView {
        //返回获取的新闻
        void returnRecipesListData(List<RecipesSummary.DataBean> dataBeen);

        //返回顶部
        void scrolltoTop();
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        //发起获取新闻请求
        public abstract void getRecipesListDataRequest(HashMap<String,String> map);
    }

}
