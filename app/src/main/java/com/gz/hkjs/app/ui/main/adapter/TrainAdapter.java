package com.gz.hkjs.app.ui.main.adapter;

import android.content.Context;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.gz.hkjs.app.R;
import com.gz.hkjs.app.bean.VideoData;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */

public class TrainAdapter extends MultiItemRecycleViewAdapter<VideoData.DataBean> {

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_PHOTO_ITEM = 1;

    public TrainAdapter(Context context, final List<VideoData.DataBean> datas) {
        super(context, datas, new MultiItemTypeSupport<VideoData.DataBean>() {

            @Override
            public int getItemViewType(int position, VideoData.DataBean dataBean) {

                if (dataBean == null) {
                    return TYPE_ITEM;
                }
                return TYPE_PHOTO_ITEM;
            }

            @Override
            public int getLayoutId(int type) {
                if (type == TYPE_PHOTO_ITEM) {
                    return R.layout.item_train_normal;
                }
                return R.layout.item_train_nocontent;
            }
        });
    }

    @Override
    public void convert(ViewHolderHelper holder, VideoData.DataBean videoData) {
        switch (holder.getLayoutId()) {
            case R.layout.item_train_nocontent:
                setItemValues(holder, videoData, getPosition(holder));
                break;
            case R.layout.item_train_normal:
                setPhotoItemValues(holder, videoData, getPosition(holder));
                break;
        }
    }

    /**
     * 普通样式
     *
     * @param holder
     * @param videoData
     * @param position
     */
    private void setItemValues(final ViewHolderHelper holder, final VideoData.DataBean videoData, final int position) {


    }

    /**
     * 图文样式
     *
     * @param holder
     * @param position
     */
    private void setPhotoItemValues(final ViewHolderHelper holder, final VideoData.DataBean videoData, int position) {


    }
}
