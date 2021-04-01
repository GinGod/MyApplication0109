package com.gingod.myapplication0109;

import android.annotation.SuppressLint;


import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * String工具类
 *
 * @author
 */

public class StringUtils {

    private StringUtils() {
    }

    /**
     * 替代字符串
     *
     * @param originalString 原始字符串
     * @param length         指定长度
     * @return 返回替代源字符串的字符串
     */
    public static String subString(String originalString, int length) {
        String result = "";

        if (originalString != null && !"".equalsIgnoreCase(originalString)) {
            if (length <= 0 || length >= originalString.length()) {
                result = originalString;
            } else {
                result = originalString.substring(0, length) + "...";
            }
        }

        return result;
    }

    /**
     * 判断字符串对象是否为null或者为空
     *
     * @param originalString 初始字符串
     * @return true-空或者为null、false-非空或者不为null
     */
    public static boolean isNullOrEmpty(String originalString) {
        if (originalString == null) {
            return true;
        }
        String str = originalString.trim();
        return str == null || str.length() == 0;
    }


    /**
     * 判断字符串对象是否为null或者为空
     * 初始字符串
     *
     * @return true-空或者为null、false-非空或者不为null
     */
    public static boolean isEmpty(String strOriginal) {
        if (strOriginal == null) {
            return true;
        }
        String str = strOriginal.trim();
        return str == null || str.length() == 0;
    }


//    /**
//     * 判断是否是一个中文汉字
//     * @param c 字符
//     * @return true-表示是中文汉字，false-表示是英文字母
//     */
//    public static boolean isChineseChar(char c) {
//        boolean result = false;
//
//        try {
//            // 如果字节数大于1是汉字，以这种方式区别英文字母和中文汉字并不是十分严谨
//            result = String.valueOf(c).getBytes(ConfigHelper.TextCode.GBK).length > 1;
//        }
//        catch (UnsupportedEncodingException MyIceServer) {
//            LogHelper.exportLog(CommonHelper.getCName(new Exception()), CommonHelper.getMName(new Exception()),
//                    "UnsupportedEncodingException:" + MyIceServer.getMessage(), true);
//        }
//
//        return result;
//    }

    /**
     * 按字节截取字符串
     *
     * @param orignal  原始字符串
     * @param textCode 字符串编码
     * @param start    开始位置
     * @param length   截取位数
     * @return 截取后的字符串
     */
    public static String substring(String orignal, String textCode, int start, int length) {
        String result = "";

        try {
            if (orignal != null && !"".equals(orignal)) {
                byte[] bytes = orignal.getBytes(textCode);

                if (length > 0 && length < bytes.length) {
                    byte[] temps = new byte[length];
                    for (int i = start; i < start + length; i++) {
                        temps[i - start] = bytes[i];
                    }

                    result = new String(temps, textCode);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 如果对象值为null则返回null
     *
     * @param obj
     * @return
     */
    public static String valueOf(Object obj) {
        if (obj == null) {
            return null;
        }
        return String.valueOf(obj);
    }

    private static final String SEP1 = "、";
    private static final String SEP2 = "|";
    private static final String SEP3 = "=";
    private static final String SEP = ",";
    private static final String SEPA = "|||";

    /**
     * List转换String
     *
     * @param list :需要转换的List
     * @return String转换后的字符串
     */
    public static String ListToString(List<?> list) {
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == null || list.get(i) == "") {
                    continue;
                }
                // 如果值是list类型则调用自己
                if (list.get(i) instanceof List) {
                    sb.append(ListToString((List<?>) list.get(i)));
                    sb.append(SEP);
                } else if (list.get(i) instanceof Map) {
                    sb.append(MapToString((Map<?, ?>) list.get(i)));
                    sb.append(SEP);
                } else {
                    if (i > 0) {
                        sb.append(SEP);
                    }
                    sb.append(list.get(i));

                }
            }
        }
        return "" + sb.toString();
    }

    /**
     * Map转换String
     *
     * @param map :需要转换的Map
     * @return String转换后的字符串
     */
    public static String MapToString(Map<?, ?> map) {
        StringBuffer sb = new StringBuffer();
        // 遍历map
        for (Object obj : map.keySet()) {
            if (obj == null) {
                continue;
            }
            Object key = obj;
            Object value = map.get(key);
            if (value instanceof List<?>) {
                sb.append(key.toString() + SEP1 + ListToString((List<?>) value));
                sb.append(SEP2);
            } else if (value instanceof Map<?, ?>) {
                sb.append(key.toString() + SEP1
                        + MapToString((Map<?, ?>) value));
                sb.append(SEP2);
            } else {
                sb.append(key.toString() + SEP3 + value.toString());
                sb.append(SEP2);
            }
        }
        return "M" + sb.toString();
    }

    /**
     * String转换Map
     *
     * @param mapText                   :需要转换的字符串
     * @param :字符串中的分隔符每一个key与value中的分割
     * @param :字符串中每个元素的分割
     * @return Map<?, ?>
     */
    public static Map<String, Object> StringToMap(String mapText) {

        if (mapText == null || "".equals(mapText)) {
            return null;
        }
        mapText = mapText.substring(1);

        mapText = mapText;

        Map<String, Object> map = new HashMap<String, Object>();
        String[] text = mapText.split("\\" + SEP2); // 转换为数组
        for (String str : text) {
            String[] keyText = str.split(SEP3); // 转换key与value的数组
            if (keyText.length < 1) {
                continue;
            }
            String key = keyText[0]; // key
            String value = keyText[1]; // value
            if (value.charAt(0) == 'M') {
                Map<?, ?> map1 = StringToMap(value);
                map.put(key, map1);
            } else if (value.charAt(0) == 'L') {
                List<?> list = StringToList(value);
                map.put(key, list);
            } else {
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * String转换List
     *
     * @param listText :需要转换的文本
     * @return List<?>
     */
    public static List<Object> StringToList2(String listText) {
        if (listText == null || "".equals(listText)) {
            return null;
        }
//        listText = listText.substring(1);
//
//        listText = listText;

        List<Object> list = new ArrayList<>();
        String[] text = listText.split(SEP);
        for (String str : text) {
//            if (str.charAt(0) == 'M') {
//                Map<?, ?> map = StringToMap(str);
//                list.add(map);
//            } else if (str.charAt(0) == 'L') {
//                List<?> lists = StringToList2(str);
//                list.add(lists);
//            } else {
//                list.add(str);
//            }

            list.add(str);
        }
        return list;
    }

    /**
     * String转换List
     *
     * @param listText :需要转换的文本
     * @return List<?>
     */
    public static List<Object> StringToList3(String listText) {
        if (listText == null || "".equals(listText)) {
            return null;
        }

        List<Object> list = new ArrayList<>();
        String[] text = listText.split("[ ||| ]");
        for (String str : text) {
            if (str != null && str.length() > 0) {
                if (str.charAt(0) == 'M') {
                    Map<?, ?> map = StringToMap(str);
                    list.add(map);
                } else if (str.charAt(0) == 'L') {
                    List<?> lists = StringToList3(str);
                    list.add(lists);
                } else {
                    list.add(str);
                }
            }

        }
        return list;
    }

    /**
     * String转换List
     *
     * @param listText :需要转换的文本
     * @return List<?>
     */
    public static List<Object> StringToList(String listText) {
        if (listText == null || "".equals(listText)) {
            return null;
        }
//        listText = listText.substring(1);
//
//        listText = listText;

        List<Object> list = new ArrayList<>();
        String[] text = listText.split(SEP1);
        for (String str : text) {
            if (str.charAt(0) == 'M') {
                Map<?, ?> map = StringToMap(str);
                list.add(map);
            } else if (str.charAt(0) == 'L') {
                List<?> lists = StringToList(str);
                list.add(lists);
            } else {
                list.add(str);
            }
        }
        return list;
    }

    public static String toString(InputStream in, String encoding) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int count = -1;
        while ((count = in.read(buffer)) != -1) {
            outStream.write(buffer, 0, count);
        }
        return new String(outStream.toByteArray(), encoding);
    }

    public static String paddingLeft(String s, String paddingS, int count) {
        if (count <= 0) {
            return s;
        }
        if (isNullOrEmpty(paddingS)) {
            return s;
        }

        if (s == null) {
            s = "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(paddingS);
        }
        sb.append(s);

        return sb.toString();
    }

    public static String paddingRight(String s, String paddingS, int count) {
        if (count <= 0) {
            return s;
        }
        if (isNullOrEmpty(paddingS)) {
            return s;
        }

        if (s == null) {
            s = "";
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < count; i++) {
            sb.append(paddingS);
        }

        return sb.toString();
    }

    public static String toString(Collection<String> lst, String split) {
        return toString(lst, null, null, split);
    }

    public static String toString(Collection<String> lst, String itemPrev, String itemPost, String split) {
        if (lst == null || lst.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int lastIndex = lst.size() - 1;
        if (itemPrev == null) {
            itemPrev = "";
        }
        if (itemPost == null) {
            itemPost = "";
        }
        for (String s : lst) {
            i++;
            sb.append(itemPrev);
            sb.append(s);
            sb.append(itemPost);
            if (!isNullOrEmpty(split) && i < lastIndex) {
                sb.append(split);
            }
        }
        return sb.toString();
    }

    public static String startString(String s, int count) {
        if (isNullOrEmpty(s)) {
            return "";
        }
        if (s.length() <= count) {
            return s;
        }
        return s.substring(0, count);
    }

    public static String endString(String s, int count) {
        if (isNullOrEmpty(s)) {
            return "";
        }
        int beginIndex = s.length() - count;
        int endIndex = beginIndex + count;
        return s.substring(beginIndex, endIndex);
    }

    /**
     * 以字节数来截取字符串
     *
     * @param s
     * @param startIndex
     * @param toIndex
     * @return
     * @throws UnsupportedEncodingException
     */
    @SuppressLint("NewApi")
    public static String subStringInByte(String s, int startIndex, int toIndex) throws UnsupportedEncodingException {
        byte[] bytes = s.getBytes("GBK");
        byte[] sub = Arrays.copyOfRange(bytes, startIndex, toIndex);
        return new String(sub, "GBK");
    }

    /**
     * 以字节数来截取字符串
     *
     * @param s
     * @param startIndex
     * @return
     * @throws UnsupportedEncodingException
     */
    @SuppressLint("NewApi")
    public static String subStringInByte(String s, int startIndex) throws UnsupportedEncodingException {
        byte[] bytes = s.getBytes("GBK");
        byte[] sub = Arrays.copyOfRange(bytes, startIndex, bytes.length);
        return new String(sub, "GBK");
    }


    /**
     * 如果s为null，则取value的值
     *
     * @param s
     * @param value
     * @return
     */
    public static String nullValue(String s, String value) {
        return s == null ? value : s;
    }

    /**
     * 如果s为空，则取value的值
     *
     * @param s
     * @param value
     * @return
     */
    public static String emptyValue(String s, String value) {
        return isNullOrEmpty(s) ? value : s;
    }


    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * 隐藏姓名的前几位
     *
     * @param name
     * @return
     */
    public String hideName(String name) {
        StringBuilder stringBuilder = new StringBuilder(name);
        int leg = name.length();
        String str = "";
        for (int i = 0; i < leg - 1; i++) {
            str = str + "*";
        }

        stringBuilder.replace(0, name.length() - 1, str);
        return stringBuilder.toString();

    }

    /**
     * 根据|||分割, 返回第一个分割结果
     *
     * @return
     */
    public static String getNameOfUrl(String origin) {
        try {
            String one;
            if (origin.contains("/")) {
                one = origin.substring(origin.lastIndexOf("/" + 1));
            } else {
                one = origin;
            }
            return one;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据|||分割, 返回第一个分割结果
     *
     * @return
     */
    public static String getOneIndexOfStr(String origin) {
        try {
            String one;
            if (origin.contains("|||")) {
                one = origin.substring(0, origin.indexOf("|||"));
            } else {
                one = origin;
            }
            return one;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据|||分割, 返回第二个分割结果
     *
     * @return
     */
    public static String getTwoIndexOfStr(String origin) {
        try {
            String oneSub = getNextStr(origin);
            return getOneIndexOfStr(oneSub);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据|||分割, 返回第三个分割结果
     *
     * @return
     */
    public static String getThreeIndexOfStr(String origin) {
        try {
            String oneSub = getNextStr(origin);
            return getTwoIndexOfStr(oneSub);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据|||分割, 返回第三个分割结果
     *
     * @return
     */
    public static String getFourIndexOfStr(String origin) {
        try {
            String oneSub = getNextStr(origin);
            return getThreeIndexOfStr(oneSub);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 返回第一个|||后字符串
     *
     * @return
     */
    @NotNull
    private static String getNextStr(String origin) {
        String oneSub = "";
        if (origin.contains("|||")) {
            oneSub = origin.substring(origin.indexOf("|||") + 3);
        }
        return oneSub;
    }

}
