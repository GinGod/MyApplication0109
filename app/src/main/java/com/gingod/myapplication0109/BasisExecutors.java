package com.gingod.myapplication0109;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理工具
 */
public class BasisExecutors {
    private static final String TAG = "BasisExecutors";
    /**
     * 磁盘IO线程池
     **/
    private final ExecutorService diskIO;
    /**
     * 网络IO线程池
     **/
    private final ExecutorService networkIO;
    /**
     * 批量网络IO线程池
     **/
    private final ExecutorService networkBatchIO;
    /**
     * UI线程
     **/
    private final Executor mainThread;
    /**
     * 定时任务线程池
     **/
    private final ScheduledExecutorService scheduledExecutor;

    private volatile static BasisExecutors BasisExecutors;

    public static BasisExecutors getInstance() {
        if (BasisExecutors == null) {
            synchronized (BasisExecutors.class) {
                if (BasisExecutors == null) {
                    BasisExecutors = new BasisExecutors();
                }
            }
        }
        return BasisExecutors;
    }

    public BasisExecutors(ExecutorService diskIO, ExecutorService networkIO, ExecutorService networkBatchIO, Executor mainThread, ScheduledExecutorService scheduledExecutor) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.networkBatchIO = networkBatchIO;
        this.mainThread = mainThread;
        this.scheduledExecutor = scheduledExecutor;
    }

    public BasisExecutors() {
        this(diskIoExecutor(), networkExecutor(), networkBatchExecutor(), new MainThreadExecutor(), scheduledThreadPoolExecutor());
    }

    /**
     * 定时(延时)任务线程池
     * <p>
     * 替代Timer,执行定时任务,延时任务
     */
    public ScheduledExecutorService scheduledExecutor() {
        return scheduledExecutor;
    }

    /**
     * 磁盘IO线程池（单线程）
     * <p>
     * 和磁盘操作有关的进行使用此线程(如读写数据库,读写文件)
     * 禁止延迟,避免等待
     * 此线程不用考虑同步问题
     */
    public ExecutorService diskIO() {
        return diskIO;
    }

    /**
     * 网络IO线程池
     * <p>
     * 网络请求,异步任务等适用此线程
     * 不建议在这个线程 sleep 或者 wait
     */
    public ExecutorService networkIO() {
        return networkIO;
    }

    /**
     * 批量下载网络IO线程池
     */
    public ExecutorService networkBatchIO() {
        return networkBatchIO;
    }

    /**
     * UI线程
     * <p>
     * Android 的MainThread
     * UI线程不能做的事情这个都不能做
     */
    public Executor mainThread() {
        return mainThread;
    }

    private static ScheduledExecutorService scheduledThreadPoolExecutor() {
        return new ScheduledThreadPoolExecutor(16, r -> new Thread(r, "scheduled_executor"), (r, executor) -> Log.e(TAG, "rejectedExecution: scheduled executor queue overflow"));
    }

    private static ExecutorService diskIoExecutor() {
        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), r -> new Thread(r, "disk_executor"), (r, executor) -> Log.e(TAG, "rejectedExecution: disk io executor queue overflow"));
    }

    private static ExecutorService networkExecutor() {
        return new ThreadPoolExecutor(3, 6, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), r -> new Thread(r, "network_executor"), (r, executor) -> Log.e(TAG, "rejectedExecution: network executor queue overflow"));
    }

    private static ExecutorService networkBatchExecutor() {
        return new ThreadPoolExecutor(5, 5, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), r -> new Thread(r, "network_executor"), (r, executor) -> Log.e(TAG, "rejectedExecution: network executor queue overflow"));
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }

        /**
         * 延迟任务
         */
        public void execute(@NonNull Runnable command, long time) {
            mainThreadHandler.postDelayed(command, time);
        }

        /**
         * 取消任务
         */
        public void removeCallbacks(@NonNull Runnable command) {
            mainThreadHandler.removeCallbacks(command);
        }

        /**
         * 取消所有任务
         */
        public void removeCallbacks() {
            mainThreadHandler.removeCallbacksAndMessages(null);
        }
    }
}
