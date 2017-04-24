package com.gz.hkjs.app.ui.detail.contract;

import com.gz.hkjs.app.bean.FindDetail;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/3/21.
 */

public interface FindDetailContract {

    interface Model extends BaseModel {
        Observable<FindDetail.DataBean> getOneFindsData(HashMap<String, String> map);
    }

    interface View extends BaseView {
        void returnOneFindData(FindDetail.DataBean findDetail);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getOneFindsDataRequest(HashMap<String, String> map);
    }
}
