package com.gingod.myapplication0109.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 多个元素横向排列，在空间不足的小屏手机上，保证显示右边的元素，挤压左边的TextView
 */
public class EllipsizeLayout extends LinearLayout {
    public EllipsizeLayout(Context context) {
        this(context, null);
    }

    public EllipsizeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            if (getOrientation() == HORIZONTAL
                    && (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY)) {
                int totalLength = 0;
                boolean outOfSpec = false;
                TextView ellipView = null;
                final int count = getChildCount();

                for (int ii = 0; ii < count && !outOfSpec; ++ii) {
                    final View child = getChildAt(ii);
                    if (child != null && child.getVisibility() != GONE) {
                        if (child instanceof TextView) {
                            final TextView tv = (TextView) child;
                            if (tv.getEllipsize() != null) {
                                if (ellipView == null) {
                                    ellipView = tv;
                                    // clear maxWidth on mEllipView before measure
                                    ellipView.setMaxWidth(Integer.MAX_VALUE);
                                } else {
                                    outOfSpec = true;
                                }
                            }
                        }
                        final LayoutParams lp = (LayoutParams) child
                                .getLayoutParams();
                        //a |= b;  |= 运算符和 += 这一类的运算符一样，拆解开就是 a = a | b;
                        outOfSpec |= (lp.weight > 0f);
                        measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                        totalLength += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                    }
                }
                outOfSpec |= (ellipView == null) || (totalLength == 0);
                final int parentWidth = MeasureSpec.getSize(widthMeasureSpec);

                if (!outOfSpec && totalLength > parentWidth) {
                    int maxWidth = ellipView.getMeasuredWidth() - (totalLength - parentWidth);
                    int minWidth = 0;
                    if (maxWidth < minWidth) {
                        maxWidth = minWidth;
                    }
                    ellipView.setMaxWidth(maxWidth);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
