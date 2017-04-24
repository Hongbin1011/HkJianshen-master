package com.gz.hkjs.app.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.gz.hkjs.app.R;
import com.gz.hkjs.app.bean.TrainData;
import com.gz.hkjs.app.ui.main.linechart.BrokenLineCusVisitView;
import com.jaydenxiao.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Company: chuangxinmengxiang-chongqingwapushidai
 * User: HongBin(Hongbin1011@gmail.com)
 * Date: 2017-04-21
 * Time: 11:08
 */

public class TrainDataChartViewActivity extends BaseActivity {


    @Bind(R.id.id_back)
    ImageView idBack;
    @Bind(R.id.id_total_toolbar_title)
    TextView idTotalToolbarTitle;

    private List<TrainData> mdata = new ArrayList<>();
    private BrokenLineCusVisitView brokenline;


    /**
     * 入口
     *
     * @param mContext
     */
    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, TrainDataChartViewActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trainning_tongji_data;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

        idTotalToolbarTitle.setText("训练统计");

        brokenline = (BrokenLineCusVisitView) findViewById(R.id.brokenline);
        for (int i = 1; i < 32; i++) {
            TrainData brokenline = new TrainData(i + "", (int) (Math.random() * 200));

            mdata.add(brokenline);
        }
        brokenline.setMdata(mdata);
    }
}
