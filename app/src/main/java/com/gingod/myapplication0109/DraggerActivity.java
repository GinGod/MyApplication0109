package com.gingod.myapplication0109;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.gingod.myapplication0109.base.BaseSimpleActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DraggerActivity extends BaseSimpleActivity {
    @BindView(R.id.rv_dragger)
    RecyclerView rv_dragger;

    private DraggerAdapter draggerAdapter;
    private List<AnimData> mData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dragger;
    }

    @Override
    public void initValues() {
        initAdapter();
    }

    private void initAdapter() {
        rv_dragger.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mData = generateData(50);
        draggerAdapter = new DraggerAdapter(mData);
        draggerAdapter.getDraggableModule().setDragEnabled(true);
        draggerAdapter.getDraggableModule().setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Logger.e("onItemDragStart: " + pos);
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
                Logger.e("onItemDragMoving: " + from + " - " + to);

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                Logger.e("onItemDragEnd: " + pos);
            }
        });
        rv_dragger.setAdapter(draggerAdapter);
    }

    private List<AnimData> generateData(int size) {
        ArrayList<AnimData> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            AnimData animData = new AnimData();
            animData.text = i + "";
            data.add(animData);
        }
        return data;
    }

    @OnClick(R.id.tv_dragger_anim)
    public void onClick() {
        for (int i = 0; i < mData.size(); i++) {
            AnimData animData = mData.get(i);
            animData.isAnim = !animData.isAnim;
        }
        draggerAdapter.notifyDataSetChanged();
    }
}
