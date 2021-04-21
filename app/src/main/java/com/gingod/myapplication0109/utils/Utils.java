package com.gingod.myapplication0109.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    /**
     * dp转dip
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5F);
    }

    /**
     * px 转 dp
     */
    public static int px2dp(Context context, float px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5F);
    }


    /**
     * 返回当前时间的格式为 yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * 返回当前时间的格式为 yyyy-MM-dd
     *
     * @return
     */
    public static String getCurDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * 返回当前时间的格式为 yyyyMMdd
     *
     * @return
     */
    public static String getCurDate2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * 返回当前时间的格式为 yyyyMMddhhMMssSSS
     *
     * @return
     */
    public static String getIdTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        System.out.println(System.currentTimeMillis());
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * 将时间的格式为 yyyyMMddhhMMssSSS 的时间转为Long
     */
    public static Long getIdLongTime(String id) {
        Long time = -1L;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date parse = sdf.parse(id);
            time = parse.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    //毫秒转秒
    public static String long2String(long time) {
        try {
            //毫秒转秒
            int sec = (int) time / 1000;
            int min = sec / 60;    //分钟
            sec = sec % 60;        //秒
            if (min < 10) {    //分钟补0
                if (sec < 10) {    //秒补0
                    return "0" + min + ":0" + sec;
                } else {
                    return "0" + min + ":" + sec;
                }
            } else {
                if (sec < 10) {    //秒补0
                    return min + ":0" + sec;
                } else {
                    return min + ":" + sec;
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 毫秒转化时分秒毫秒
     *
     * @param ms
     * @return
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "d");
        }
        if (hour > 0) {
            sb.append(hour + "h");
        }
        if (minute > 0) {
            sb.append(minute + "′");
        }
        if (second > 0) {
            sb.append(second + "″");
        }
        return sb.toString();
    }

    /**
     * 毫秒转化时秒
     *
     * @param ms
     * @return
     */
    public static String formatSeconds(Long ms) {
        Integer ss = 1000;

        Long second = ms / ss;

        StringBuffer sb = new StringBuffer();

        if (second > 0) {
            sb.append(second + "″");
        }
        return sb.toString();
    }

    /**
     * 将文字转换为图片
     */
    public static Bitmap drawTextToBitmap(String text, float textSize, int color) {
        TextPaint textPaint = new TextPaint();
        // textPaint.setARGB(0x31, 0x31, 0x31, 0);
        textPaint.setColor(color);
        textPaint.setTextSize(textSize);
        int width = (int) (textPaint.measureText(text));
        StaticLayout layout = new StaticLayout(text, textPaint, width,
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        Bitmap bitmap = Bitmap.createBitmap(layout.getWidth() + 10,
                layout.getHeight() + 0, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(2, 0);
        canvas.drawColor(Color.WHITE);

        layout.draw(canvas);
        return bitmap;
    }
}
