package com.gingod.myapplication0109;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

class Test {
    public static final String CHANNEL_ID = "XXXX";
    private static NotificationManager mNotificationManager;
    private static List<PushMessageBean> notifyList;

    public synchronized static void cleanMsgNotify(int notice) {
        if (mNotificationManager == null
                || notifyList == null || notifyList.size() == 0) {
            return;
        }
        for (int i = notifyList.size() - 1; i >= 0; i--) {
            PushMessageBean t = notifyList.get(i);
            if (t.notice == notice) {
                mNotificationManager.cancel(t.notifyId);
                notifyList.remove(i);
            }
        }
    }

    public void sendNotification(Context context, PushMessageBean message) {
        if (TextUtils.isEmpty(message.message)) {
            return;
        }
        NotificationCompat.Builder mBuilder;
        int notifyId = (int) System.currentTimeMillis();
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        registerNotificationChannel();
        mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setAutoCancel(true)
                .setContentText(message.message)
//                .setSmallIcon(R.drawable.ic_launchers_round)
//                .setVibrate(new long[]{1000})
//                .setColor(context.getResources().getColor(R.color.color_primary))
//                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message.message));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {//7.0以上不需要title
            mBuilder.setContentTitle(context.getResources().getString(R.string.app_name));
        }
        message.notifyId = notifyId;
        saveNotification(message);
        mNotificationManager.notify(notifyId, mBuilder.build());
    }

    private void saveNotification(PushMessageBean message) {
        if (notifyList == null) {
            notifyList = new ArrayList<>();
        }
        notifyList.add(message);
    }

    private void registerNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = mNotificationManager.getNotificationChannel(CHANNEL_ID);
            if (notificationChannel == null) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                        CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH);
                channel.enableLights(true); //是否在桌面icon右上角展示小红点
                channel.setLightColor(Color.RED); //小红点颜色
                //channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
                mNotificationManager.createNotificationChannel(channel);
            }
        }
    }

    public class Person {

    }

    public class Man extends Person {

    }

    public void test1() {
        test2(new Man());
        ArrayList<String> list = new ArrayList();
        test3(list);
    }

    public void test2(Person person) {

    }

    public void test3(List<String> list) {

    }
}
