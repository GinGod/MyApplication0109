package com.gingod.myapplication0109;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.security.interfaces.ECKey;
import java.util.List;

public class DraggerAdapter extends BaseQuickAdapter<AnimData, BaseViewHolder> implements DraggableModule {
    public DraggerAdapter(List<AnimData> data) {
        super(R.layout.item_dragger, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AnimData animData) {
        baseViewHolder.setText(R.id.tv_item_dragger, animData.text);

        View view = baseViewHolder.getView(R.id.tv_item_dragger);
        if (animData.isAnim) {
            BasisAnimUtils.anim(getContext(), view, R.anim.anim_view_shake_upanddown);
        } else {
            if (view.getAnimation() != null) {
                view.getAnimation().cancel();
            }
        }
    }
}
