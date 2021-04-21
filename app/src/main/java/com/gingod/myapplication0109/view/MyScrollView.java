package com.gingod.myapplication0109.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    private boolean canScroll = true;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public boolean isCanScroll() {
        return canScroll;
    }

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (canScroll) {
//            return super.dispatchTouchEvent(ev);
//        } else {
//            return true;
//        }
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if (canScroll) {
//            return super.onInterceptTouchEvent(ev);
//        } else {
//            return true;
//        }
//    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (canScroll) {
            return super.onTouchEvent(ev);
        } else {
            return true;
        }
    }
}
