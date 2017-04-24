package com.gz.hkjs.app.ui.main.contract;

import com.gz.hkjs.app.bean.UserHomeData;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by Administrator on 2017/3/16.
 */

public interface TraningListContract {

    /**
     * des:获取首页用户数据的contract
     */
    interface Model extends BaseModel {
        Observable<UserHomeData.DataBean> getUserHomeDataListData(HashMap<String, String> map);
    }

    interface View extends BaseView {
        void returnUserHomeDataListData(UserHomeData.DataBean homeDataSummaries);

        //返回顶部
        void scrolltoTop();
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getUserHomeDataRequest(HashMap<String, String> map);
    }
}
