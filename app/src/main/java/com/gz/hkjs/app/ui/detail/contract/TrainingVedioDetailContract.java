package com.gz.hkjs.app.ui.detail.contract;

import com.gz.hkjs.app.bean.TrainVedioDetail;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/14.
 */

public interface TrainingVedioDetailContract {


    interface Model extends BaseModel {
        //请求获取发现
//        Observable<List<TrainVedioDetail.DataBean.StepBean>> getTrainVedioDetailData(HashMap<String, String> map);
        Observable<TrainVedioDetail> getTrainVedioDetailData(HashMap<String, String> map);
    }

    interface View extends BaseView {
        void returnTrainVedioDetailData(TrainVedioDetail trainVedioDetail);
//        void returnTrainVedioDetailData(List<TrainVedioDetail.DataBean.StepBean> TrainVedioStepBean);
    }

    abstract static class Presenter extends BasePresenter<TrainingVedioDetailContract.View, TrainingVedioDetailContract.Model> {

        //发起发现请求
        public abstract void getTrainVedioDetailDataRequest(HashMap<String, String> map);
    }
}
