package com.gingod.myapplication0109.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.DownloadListener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gingod.myapplication0109.GlideWithCacheUtils;
import com.gingod.myapplication0109.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.othershe.combinebitmap.CombineBitmap;
import com.othershe.combinebitmap.layout.DingLayoutManager;
import com.othershe.combinebitmap.listener.OnProgressListener;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 消息操作工具类
 */
public class MessageUtils {
    private static String textMsgFeedback = "textMsgFeedback";
    private static String textMsg = "textMsg";
    private static String fileMsg = "fileMsg";
    private static String cardMsg = "cardMsg";
    private static String imgMsg = "imgMsg";
    private static String videoMsg = "videoMsg";
    private static String videoApplyMsg = "videoApplyMsg";
    private static String revokeMsg = "revokeMsg";
    private static String audioMsg = "audioMsg";
    private static String audioApplyMsg = "audioApplyMsg";


    public static String getTeamHeadPic(String value) {
        String newresult = "";
        try {
            // 第一步，获取MessageDigest对象，参数为MD5字符串，表示这是一个MD5算法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 第二步，输入源数据，参数类型为byte[]
            md5.update(value.getBytes("UTF-8"));
            // 第三步，计算MD5值
            BigInteger bigInt = new BigInteger(1, md5.digest());
            newresult = bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newresult;
    }

    /**
     * 将String[]转为String
     */
    public static String getArrString(String[] arr) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < arr.length; i++) {
                if (i == 0) {
                    stringBuffer.append("[\"" + arr[i] + "\"");
                } else {
                    stringBuffer.append(",\"" + arr[i] + "\"");
                }
                if (i == arr.length - 1) {
                    stringBuffer.append("]");
                }
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }



    /**
     * 将科学计算法的Str转换为Long的Str
     */
    public static String parseLongStrWithE(String numStr) {
        try {
            return new BigDecimal(numStr).longValue() + "";
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }

    /**
     * 将科学计算法的Str转换为Long
     */
    public static Long parseLongWithE(String numStr) {
        try {
            return new BigDecimal(numStr).longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    /**
     * 将科学计算法的Str转换为int
     */
    public static int parseIntWithE(String numStr) {
        try {
            return new BigDecimal(numStr).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }



    public static String formatToStr(Object object) {
        try {
            if (object == null) {
                return "";
            } else if (object instanceof Double) {
                return ((Double) object).longValue() + "";
            } else if (object instanceof Integer) {
                return ((Integer) object).longValue() + "";
            } else if (object instanceof Long) {
                return ((Long) object).longValue() + "";
            } else if (object instanceof Float) {
                return ((Float) object).longValue() + "";
            } else {
                return object.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 返回字符串
     *
     * @param object
     * @return
     */
    public static String formatToNormalStr(Object object) {
        try {
            if (object == null) {
                return "";
            } else if (object instanceof Double) {
                return ((Double) object).doubleValue() + "";
            } else if (object instanceof Integer) {
                return ((Integer) object).longValue() + "";
            } else if (object instanceof Long) {
                return ((Long) object).longValue() + "";
            } else if (object instanceof Float) {
                return ((Float) object).doubleValue() + "";
            } else {
                return object.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断str为空, 或者为字符串 "null"
     */
    public static boolean isEmpty(String string) {
        return TextUtils.isEmpty(string) || "null".equals(string);
    }

    /**
     * 判断str为空, 或者为字符串 "null"
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * 将json数据转为Map
     */
    public static Map<String, Object> jsontomap(String str_json) {
        Map<String, Object> res = null;
        try {
            res = JSON.parseObject(str_json, Map.class);
//            res = new Gson().fromJson(str_json, new TypeToken<Map<String, Object>>() {
//            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            res = new HashMap<>();
        }
        return res;
    }

    /**
     * 将json数据转为Map
     */
    public static Map<String, Object> jsontomapByGson(String str_json) {
        Map<String, Object> res = null;
        try {
            res = new Gson().fromJson(str_json, new TypeToken<Map<String, Object>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            res = new HashMap<>();
        }
        return res;
    }

    /**
     * 将map数据转为json
     */
    public static String maptojson(Map map) {
        String str = null;
        try {
            str = new JSONObject(map).toString();
        } catch (Exception e) {
            e.printStackTrace();
            str = "";
        }
        return str;
    }

    /**
     * 将map或者str数据转为str
     */
    public static String maptojson(Object map) {
        String str = null;
        try {
            if (map == null) {
                str = "";
            } else if (map instanceof Map) {
                str = new JSONObject((Map<String, Object>) map).toString();
            } else if (map instanceof String) {
                str = map.toString();
            } else {
                str = new Gson().toJson(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            str = "";
        }
        return str;
    }

    /**
     * 匹配单个emotion
     */
    public static boolean matchEmotion(String name) {
        try {
            String regexEmotion = "\\[([\u4e00-\u9fa5\\w])+\\]";
            Pattern patternEmotion = Pattern.compile(regexEmotion);
            Matcher matcherEmotion = patternEmotion.matcher(name);
            return matcherEmotion.find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
