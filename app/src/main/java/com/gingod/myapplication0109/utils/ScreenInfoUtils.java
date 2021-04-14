package com.gingod.myapplication0109.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 屏幕工具类
 *
 * @author
 */
public class ScreenInfoUtils {
    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity 上下文
     * @param isBlack 状态栏字体是否设置为黑色
     */
    public static void fullScreen(Activity activity, boolean isBlack) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                /**
                 * SystemUI Flag引起的软键盘变化
                 * 在使用SOFT_INPUT_ADJUST_RESIZE，
                 * 同时View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN或View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION时，当键盘弹出时，
                 * 只会fitSystemWindow=true的view所占区域会被resize，其他view将会被软键盘覆盖。
                 */
                /**
                 * View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN：(>=api16)
                 * 作用：在不隐藏StatusBar的情况下，将view所在window的显示范围扩展到StatusBar下面。同时Activity的部分内容也因此被StatusBar覆盖遮挡。
                 *
                 * 当使用此Flag时，设置fitSystemWindow=true的view，会被系统自动添加大小为statusBar和ActionBar高度之和相同的paddingTop。
                 * 当window设置WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS时，此Flag会被系统会自动添加。
                 */
                /**
                 * View.SYSTEM_UI_FLAG_LAYOUT_STABLE:
                 * 作用: 稳定布局。当StatusBar和NavigationBar动态显示和隐藏时，系统为fitSystemWindow=true的view设置的padding大小都不会变化，所以view的内容的位置也不会发生移动
                 */
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                /**
                 * Android的状态栏的字体颜色默认为白色，只有Android6.0以后才提供官方的API选择黑色字体（View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR）。
                 * 所以当6.0以下使用透明状态栏时，若StatusBar下面的View的也为白色背景时，则会造成看不到StatusBar的相关信息。
                 */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isBlack) {
                    option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(option);
                /**
                 * WindowMananger.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS：
                 * 1.用于未StatusBar和NavigationBar设置背景颜色。
                 * 2.原理：将StatusBar和NavigationBar设置为透明背景，并且将StatusBar和NavigationBar所在空间设置
                 * 为Window.getStatusBarColor() 和Window.getNavigationBarColor()方法获得的颜色。
                 * 最低版本支持：Android5.0 (api 21)
                 */
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity 上下文
     */
    public static void fullScreen(Activity activity) {
        fullScreen(activity, true);
    }

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     * @return 100
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取屏幕宽度
     *
     * @param activity 上下文
     * @return 100
     */
    public static int getWindowWidth(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕高度(全面屏手机获取高度有问题)
     *
     * @param activity 上下文
     * @return 100
     */
    public static int getWindowHeight(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getHeight();
    }

    /**
     * 获取屏幕高度(全面屏手机获取高度有问题)
     *
     * @param context 上下文
     * @return 100
     */
    public static int getFullActivityHeight(@Nullable Context context) {
        if (!isAllScreenDevice(context)) {
            return getScreenHeight(context);
        }
        return getScreenRealHeight(context);
    }

    @NonNull
    private volatile static Point[] mRealSizes = new Point[2];

    public static int getScreenRealHeight(@Nullable Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return getScreenHeight(context);
        }

        int orientation = context.getResources().getConfiguration().orientation;

        if (mRealSizes[orientation] == null) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager == null) {
                return getScreenHeight(context);
            }
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getRealSize(point);
            mRealSizes[orientation] = point;
        }
        return mRealSizes[orientation].y;
    }

    public static int getScreenHeight(@Nullable Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }
        return 0;
    }

    private volatile static boolean mHasCheckAllScreen;
    private volatile static boolean mIsAllScreenDevice;

    public static boolean isAllScreenDevice(Context context) {
        if (mHasCheckAllScreen) {
            return mIsAllScreenDevice;
        }
        mHasCheckAllScreen = true;
        mIsAllScreenDevice = false;
        // 低于 API 21的，都不会是全面屏。。。
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return false;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getRealSize(point);
            float width, height;
            if (point.x < point.y) {
                width = point.x;
                height = point.y;
            } else {
                width = point.y;
                height = point.x;
            }
            if (height / width >= 1.97f) {
                mIsAllScreenDevice = true;
            }
        }
        return mIsAllScreenDevice;
    }
}
