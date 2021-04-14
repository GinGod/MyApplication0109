package com.gingod.myapplication0109.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.billy.android.swipe.SwipeConsumer;
import com.gingod.myapplication0109.R;

/**
 * 自定义LinearLayout
 *
 * @author
 */
public class CustomLinearLayout extends LinearLayout {
    public View item_delete, tv_item_information_modify, tv_item_information_delete;
    public SwipeConsumer swipeConsumer;

    public CustomLinearLayout(Context context) {
        super(context);
        init();
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        item_delete = LayoutInflater.from(getContext()).inflate(R.layout.item_information_delete, null);
        tv_item_information_modify = item_delete.findViewById(R.id.tv_item_information_modify);
        tv_item_information_delete = item_delete.findViewById(R.id.tv_item_information_delete);
    }
}
