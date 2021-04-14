package com.gingod.myapplication0109;

import android.app.Application;

import androidx.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class MyApplication extends Application {
    private static MyApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLogger();
    }

    public synchronized static MyApplication getInstance() {
        return instance;
    }

    /**
     * 初始化Looger参数
     */
    private void initLogger() {
        // (Optional) Whether to show thread info or not. Default true(线程)// (Optional) How many method line to show. Default 2(关联方法层级)
        // (Optional) Hides internal method calls up to offset. (方法层级偏移)// (Optional) Global tag for every log. Default PRETTY_LOGGER(日志tag)
        try {
            PrettyFormatStrategy prettyFormatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(false)
                    .methodCount(0)
                    .methodOffset(0)
                    .tag("MyApp")
                    .build();

            Logger.addLogAdapter(new AndroidLogAdapter(prettyFormatStrategy) {
                @Override
                public boolean isLoggable(int priority, @Nullable String tag) {
                    return true;//true 打印日志;  false 取消打印
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
