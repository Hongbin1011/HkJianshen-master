package com.gz.hkjs.app.ui.detail.presenter;

import com.gz.hkjs.app.R;
import com.gz.hkjs.app.bean.FindDetail;
import com.gz.hkjs.app.ui.detail.contract.FindDetailContract;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/21.
 */

public class FindsDetailPresenter extends FindDetailContract.Presenter {
    @Override
    public void getOneFindsDataRequest(HashMap<String, String> map) {
        mRxManage.add(mModel.getOneFindsData(map).subscribe(new RxSubscriber<FindDetail.DataBean>(mContext) {
            @Override
            protected void _onNext(FindDetail.DataBean newsDetail) {
                mView.returnOneFindData(newsDetail);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
