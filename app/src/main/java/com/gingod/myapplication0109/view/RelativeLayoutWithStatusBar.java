package com.gingod.myapplication0109.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.gingod.myapplication0109.utils.ScreenInfoUtils;


/**
 * 包含状态栏高度的RelativeLayout
 */
public class RelativeLayoutWithStatusBar extends RelativeLayout {
    public RelativeLayoutWithStatusBar(Context context) {
        super(context);
        init(context, null, 0);
    }

    public RelativeLayoutWithStatusBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public RelativeLayoutWithStatusBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    //添加状态栏padding
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        int statusBarHeight = ScreenInfoUtils.getStatusBarHeight(getContext());
        setPadding(getPaddingLeft(), getPaddingTop() + statusBarHeight, getPaddingRight(), getPaddingBottom());
    }

    /**
     * 取消状态栏
     */
    public void removeStatusBar() {
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        invalidate();
    }
}
