package com.gz.hkjs.app.ui.detail.model;

import com.gz.hkjs.app.api.Api;
import com.gz.hkjs.app.api.HostType;
import com.gz.hkjs.app.bean.TrainVedioDetail;
import com.gz.hkjs.app.ui.detail.contract.TrainingVedioDetailContract;
import com.jaydenxiao.common.baserx.RxSchedulers;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/4/14.
 */

public class TrainVedioDetailModel implements TrainingVedioDetailContract.Model {
    @Override
    public Observable<TrainVedioDetail> getTrainVedioDetailData(HashMap<String, String> map) {
        return Api.getDefault(HostType.NORMAL_HOSTTYPE)
                .getTrainVedioDetail(Api.getCacheControl(), map)
//                .map(new Func1<TrainVedioDetail, TrainVedioDetail.DataBean>() {
//                    @Override
//                    public TrainVedioDetail.DataBean call(TrainVedioDetail trainVedioDetail) {
//                        return trainVedioDetail.getData();
//                    }
//                })
//                .map(new Func1<TrainVedioDetail.DataBean, List<TrainVedioDetail.DataBean.StepBean>>() {
//                    @Override
//                    public List<TrainVedioDetail.DataBean.StepBean> call(TrainVedioDetail.DataBean dataBean) {
//                        return dataBean.getStep();
//                    }
//                })
                .compose(RxSchedulers.<TrainVedioDetail>io_main());
    }
}
