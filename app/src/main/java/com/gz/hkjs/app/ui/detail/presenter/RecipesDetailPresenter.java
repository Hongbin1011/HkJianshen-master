package com.gz.hkjs.app.ui.detail.presenter;

import com.gz.hkjs.app.R;
import com.gz.hkjs.app.bean.RecipesDetail;
import com.gz.hkjs.app.ui.detail.contract.RecipesDetailContract;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/16.
 */

public class RecipesDetailPresenter extends RecipesDetailContract.Presenter {
    @Override
    public void getRecipesDetailDataRequest(HashMap<String,String> map) {

        mRxManage.add(mModel.getRecipesDetailData(map).subscribe(new RxSubscriber<RecipesDetail.DataBean>(mContext) {
            @Override
            protected void _onNext(RecipesDetail.DataBean recipesDetail) {
                mView.returnRecipesDetailData(recipesDetail);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
