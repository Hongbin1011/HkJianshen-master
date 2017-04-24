package com.gz.hkjs.app.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aspsine.irecyclerview.baseadapter.SectionedRecyclerViewAdapter;
import com.gz.hkjs.app.R;
import com.gz.hkjs.app.bean.ChooseItem;
import com.gz.hkjs.app.util.ChooseUtils;

import java.util.List;


/**
 * Created by lyd10892 on 2016/8/23.
 */

public class ChooseItemAdapter extends SectionedRecyclerViewAdapter<HeaderHolder, DescHolder, RecyclerView.ViewHolder> {


    public List<ChooseItem.DataBeanX> allTagList;
    private Context mContext;
    private LayoutInflater mInflater;

    private SparseBooleanArray mBooleanMap;

    public ChooseItemAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBooleanMap = new SparseBooleanArray();
    }

    public void setData(List<ChooseItem.DataBeanX> allTagList) {
        this.allTagList = allTagList;
        notifyDataSetChanged();
    }

    @Override
    protected int getSectionCount() {
        return ChooseUtils.isEmpty(allTagList) ? 0 : allTagList.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = allTagList.get(section).getData().size();
        if (count >= 8 && !mBooleanMap.get(section)) {
            count = 8;
        }

        return ChooseUtils.isEmpty(allTagList.get(section).getData()) ? 0 : count;
    }

    //是否有footer布局
    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected HeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new HeaderHolder(mInflater.inflate(R.layout.hotel_title_item, parent, false));
    }


    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected DescHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new DescHolder(mInflater.inflate(R.layout.hotel_desc_item, parent, false));
    }


    @Override
    protected void onBindSectionHeaderViewHolder(final HeaderHolder holder, final int section) {
        holder.titleView.setText(allTagList.get(section).getTitle());
    }


    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(DescHolder holder, int section, int position) {
        holder.descView.setText(allTagList.get(section).getData().get(position).getTitle());
        if ("无机械(徒手)".equals(allTagList.get(section).getData().get(position).getTitle())) {
            holder.descView.setText("徒手");
        }
    }
}
