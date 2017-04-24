package com.gz.hkjs.app.ui.detail.presenter;

import com.gz.hkjs.app.R;
import com.gz.hkjs.app.bean.TrainVedioDetail;
import com.gz.hkjs.app.ui.detail.contract.TrainingVedioDetailContract;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class TrainVedioDetailPresenter extends TrainingVedioDetailContract.Presenter {

    @Override
    public void getTrainVedioDetailDataRequest(HashMap<String, String> map) {
        mRxManage.add(mModel.getTrainVedioDetailData(map).subscribe(new RxSubscriber<TrainVedioDetail>(mContext) {
            @Override
            protected void _onNext(TrainVedioDetail trainVedioDetail) {
                mView.returnTrainVedioDetailData(trainVedioDetail);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
