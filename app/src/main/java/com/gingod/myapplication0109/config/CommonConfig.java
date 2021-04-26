package com.gingod.myapplication0109.config;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.gingod.myapplication0109.R;
import com.gingod.myapplication0109.bean.FieldBean;

import java.util.ArrayList;
import java.util.List;

public class CommonConfig {
    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2016102100732497";
    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "";
    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";
    /**
     * pkcs8 格式的商户私钥。
     * <p>
     * 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * RSA2_PRIVATE。
     * <p>
     * 建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCA9Vaj4VW8GETKn3tpgqU58J1jeI3eKfCH0Jv9gHI3ufVjwc216dXoowRsetbK4JQa59KceK2twFbFuqm2xWFXKEj7Ssd7tTYDKmIvnM0hk+gDt9hegvlpuG/N1eEAcgJ6N4FbelYmcUgWohGda1JV+PmgFmobWkYMilOj6Aofc4DvG5nunCu1U3XQql7KwyCeKuPDLQpAVRurbdIknOD4WX0snP0SSaI7S7MUA8MJQEDk+2H8oRslvjBKnwA32C45AJlUxIJKlRsAvBKzh8I3rlsPkGaPd9NeA0E2Z+OLoG4BDUKvR2JnoAeSlAT7jKVfRpsbkbpJ0fvLCi/u5JEZAgMBAAECggEACZ5C0AZGdrj4qIQsmDn+SMwBcSHUsvU902NjvwyNeEqOWtphlWfTZH6EHfMrggYuBbO0qab2jTcDQcwiTFd8YxvGFbgGGNvzI1lN6W8wfYiijWRw5tut7oyrChchqP7X4HWzWDdPpZ5dWhLMbYlLos3nEGGbmF6rStQmWNs9pgrEPUZwiv221i+WwojGwICjVPq5iYl3U/a/yZl77KgIaTkMuEis/BViiKIDCc+WmB8pXejte1lx/b4nRHke5wXjWPMVcAQb+mqpffGB8uxYoKd4N7x9xUJmawevXewrHi2Uz0GyVHN8rf8t34aAYX04Jo14ATRYJ0ktqpLnfKXZgQKBgQC7X+5w652ZzfF8L6cDnFfWwY3xOwKyK7ri9N0K4/qoU9xxulZwqQWjnEXWCwO9d1Qq77aitDnk8GwUKKDE4BvD+R+YXbqEMs4MPHQpRYzthrhhvlDO5T/y+lTJS0q21/QfY6MaJPOmhdBeNxjWzWDpk9SHpqbT+E3tPTwXMqE2FQKBgQCwMFhuEcALn9GVzJyjLgg1CYvYwCbyZtMBzszSqZd7Mgdq57pU2xsBqGvnT0CGNrNx3oXuZ3bafjdzkCp3q0g7YlFgVM+LXj0BvxiSd7SsoDJDGbIbInVGVl+S29hoEypD4wtidRK7YqgTAm1b2alX4PBEIOSLNV/Dxy0ZLYdT9QKBgBZCAggF5RXFIf93haZnJwQKv/q84Mgba7rUDYHirP7b317fHK+LVHEn+h+/9TTHfQcQrKhNMmdgq+YFIXvhh0rTSA6Ia8SSaGmJsN7KNUBt9W8e9WhuURdi5zl2KSzpIEWuR0yuwQax9JuMffXeNbnpZqCqijy5zT/2OzIiQIY1AoGAXavmZ+ah/swiZo0ZPhxsGp6PrlZCnp+SUaS7+shVlBb+IJVA2HqA+jmr3JejC3groT+YXy683t855gPgwCzdQ007TIbqrQMWVkZd7wkbkqe88TXJHmoMha/IMlEV1gdScjNUyyzJH70M9MZWk/297c3Wj7IZL/XZi0HqtTcier0CgYB3HBtL0v965VPvgx9oixtKg0kaOv7SKS+ZRskv6HFyaAfBGkBBZp9CUNiSUHCskjUmuaGjApCEcZMCF6QI5ObyjOnj2UNlEgHhOFLZdTfFJepaa/JMT19V/zkxsVpque3ggWGMz8ykoTNzhAwOwFbvp+msHBwLKE1SLn6V8+v43Q==";
    public static final String RSA_PRIVATE = "";
    public static final String sign = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDTzuTmDILxHjgHkNRXA8UoPrQqNXOcYQN1zUHsy1HMFBKT/5IH9B5UPEgt+Mwo7XbHKehCpsPHjZg9T0bxO3s7JCs86xIYwiPeUEpyfSc/RnpKCSGeAd+RqMTf8SBwtz9NO3SNlKx6p/sIfdP7bhasY7TukAh858YfNhy8fr+/nc/dQTjDR2EU5NQYf++VeldOX1zE4GzZ1/rHtqKa+LqKSDavhhcFxntRhT5WvKH0fPWGG3DLfOy3vUSx8BfYSWQDpiaiQMDxbrYUcSeHNXABtJoYey9IVf5PZCB2Pa5VgKQofv3GKOVjRifAKOj32T+90yCQ8whwvXdubUGaxamBAgMBAAECggEBALEzfYMNYpsxCfUicNOfntmto/NQJrnk3A05PPdLntJa1LQm89hdnuYgTM7RQJTZHi8/20Wv2UlhJ8XbRmHZvp5bAyWDEO/PtExw5efk1un0H6lC/HIB5TYqeRNV2ysR1A7cOAWfJbG0SavwAWJv1t5AyIVx0PgWvEUuc1orvGYkrmkTJG0Q+mUJv66KcGbOIfNb9002ZNXYiPdkpViOTEf9eZEaTE55Y0RZvnBDUxE2oK0Y63h9/E+lhULk2RyTbJej8C0e9LgLe2YViVHHtePdgJauAUGjKVQVMrU4wKpMlotVky5UrcgEWg6H+fDKKm/ZB8VuZA/9UDQqdxfX03ECgYEA8nhQimrs4D0t7jHr+m0ZcP0q7Xs6N2OPwuDVVhR7hOTJizg/KmI3CrlUMlxn02ud9QDIwltSWhSfUTQGBb/M7CeviAhPSNaj04UVtxkbwQlnjQM4Jmvazd0wbPg0IMdPWsIsfpyOdixrJ9TF3mLyJ7NHU/017Xj88KTUpivooI0CgYEA36CTVIQdYOIBH5mAIhF7zc/JxFRDcaZZLpP99XcPPDgav8+dx/DuybbPrd0RE2j/MOmIQsp/G2oVzwisdl8BVbzH6Auf/EWUMIV2t/fM13KwdAyNINV5xR80+NvPAOGLHsWChmQCW56hIYhYuaUBfK/7l55SnG82ghVgZSYr0cUCgYEAgJHZqzv9dP4b38Uq6vR03BvyalDAUuSBM4XCuc1MnfAvCpp9q/y+UPpqsfWbvFjBPkv3KFACP+/VdtqOIvhMMiDtC8XUPLxLzHoyRI+Uzuwss3I0kPL559VUu0N1aLgm1OpHa6+aPwFYpJtHfJ+c5f5DYT7QHKbeAMB2XdLEnHUCgYBJc56wxew4ZENmM68IgaGeij+Fgv/cFn14dReXRpH0f6EZm4IBqDtmeetg+hmfvH8Fe89L0ONLB98972VmQ/JWTPmEWD0mSVxRS6Ug6/2WTo9yrqS0QioK+yMVEm29pldhthIXPkYf/cyDDRvHblE8uTj0JgaQZ18J/yd4Ub9W2QKBgDCBrohHL0qcISQt6I4h44hQjLL9HvZaMIh+vnPYVbI7RFRFQqXNLoZgYCF536n7wjrryYh95CnKrRAWQbrrJf1xTWOPgNBGwHHmqrg1X62qeTgYCFx5CvAyD8Cj5ZINwufX9HVRFVfO33AzhGY3Ey2f9qik0z7gl/KFifk/31vI";
    public static final int SDK_PAY_FLAG = 1;
    public static final int SDK_AUTH_FLAG = 2;

    /**
     * 网络请求成功
     */
    public static final int REQUEST_SUCCESS = 20000;
    public static final int REQUEST_EMPTY_DATA = 20012;

    /**
     * eventBus消息type
     */
    //关闭页面
    public static final String finish = "finish";
    public static final String finishPrint = "finishPrint";
    public static final String finishTask = "finishTask";
    //修改团队名
    public static final String modifyTeamName = "modifyTeamName";
    //清空聊天记录
    public static final String clearChatNotes = "clearChatNotes";
    //socket连接成功
    public static final String socketConnectSuccess = "socketConnectSuccess";
    //发送了新消息
    public static final String sendNewMsg = "sendNewMsg";
    //网络异常
    public static final String errorNetwork = "errorNetwork";
    //清空通知
    public static final String clearNotification = "clearNotification";

    //过滤日志id
    public static final String logId = "94363773";

    //文库
    public static final int libraryOnePic = 252;
    public static final int libraryTwoPic = 252 + 1;
    public static final int libraryThrPic = 252 + 2;
    public static final int libraryVideo = 252 + 3;
    public static final int libraryfile = 252 + 4;

    //初始化并接收离线消息成功
    public static final int initSuccess = 252 + 5;

    //缓存数据
    public static final int firstData = 252 + 6;
    public static final int secondData = 252 + 7;
    public static final int thirdData = 252 + 8;
    public static final int fourthData = 252 + 9;
    public static final int fifthData = 252 + 10;

    //消息标识
    public static final String android = "android";

    public static final String libraryDetail = "libraryDetail";
    public static final String libraryNum = "libraryNum";

    //通知
    public static final String notificationName = "千年设计";
    public static final String notificationId = "252";

    //获取权限
    public static final int R_CODE_PERMISSION = 252 + 11;

    /**
     * 通讯录
     */
    public static final int addressBookTitle = 252 + 12;
    public static final int addressBookContent = 252 + 13;

    /**
     * 文库管理和收藏
     */
    public static final int libraryManageOnePic = 252 + 14;
    public static final int libraryManageVideo = 252 + 15;
    public static final int libraryManagefile = 252 + 16;
    public static final int libraryCollectionOnePic = 252 + 17;
    public static final int libraryCollectionVideo = 252 + 18;
    public static final int libraryCollectionfile = 252 + 19;

    /**
     * Glide加载四边圆角图片
     */
    public static RequestOptions getRequestOptions(Transformation transformation, int roundingRadius) {
        RequestOptions roundOptions = RequestOptions.bitmapTransform(new RoundedCorners(roundingRadius));
        roundOptions.transform(transformation, new RoundedCorners(roundingRadius));
        return roundOptions;
    }

    public static RequestOptions getFitCenter(int roundingRadius) {
        return getRequestOptions(new FitCenter(), roundingRadius);
    }

    public static RequestOptions getCenterCrop(int roundingRadius) {
        return getRequestOptions(new CenterCrop(), roundingRadius);
    }

    public static List<FieldBean> getFieldBeanList() {
        List<String> fieldList = getFieldList();
        List<FieldBean> fieldBeanList = new ArrayList<>();
        for (int i = 0; i < fieldList.size(); i++) {
            String s = fieldList.get(i);
            FieldBean fieldBean = new FieldBean();
            fieldBean.classifyName = s;
            fieldBean.id = i + 1;
            fieldBeanList.add(fieldBean);
        }
        fieldBeanList.get(0).isChecked = true;
        return fieldBeanList;
    }

    /**
     * 专业
     *
     * @return
     */
    public static List<String> getFieldList() {
        List<String> fieldList = new ArrayList<>();
        fieldList.add("全部");
        fieldList.add("视频全屏");
        fieldList.add("测试签章");
        fieldList.add("测试签章");
        fieldList.add("文件筛选");
        fieldList.add("文件筛选-可查看任意文件夹");
        fieldList.add("视频全屏");
        fieldList.add("结构");
        fieldList.add("交通");
        fieldList.add("桥梁");
        fieldList.add("水利");
        fieldList.add("水电");
        fieldList.add("管廊");
        fieldList.add("电气");
        fieldList.add("勘察");
        fieldList.add("测量");
        fieldList.add("物探");
        fieldList.add("岩土");
        fieldList.add("隧道");
        fieldList.add("机械");
        fieldList.add("设备");
        fieldList.add("安防");
        fieldList.add("室内设计");
        fieldList.add("平面设计");
        fieldList.add("造价");
        fieldList.add("BIM技术");
        fieldList.add("网络通信");
        fieldList.add("IT互联网");
        fieldList.add("照明");
        fieldList.add("燃气");
        fieldList.add("暖通");
        fieldList.add("给排水");
        fieldList.add("标准规范");
        fieldList.add("技术质量管理");
        fieldList.add("工业设计");
        fieldList.add("其他");
        return fieldList;
    }

    /**
     * 文库举报理由
     *
     * @return
     */
    public static List<String> getReportReason() {
        List<String> fieldList = new ArrayList<>();
        fieldList.add("广告或垃圾信息");
        fieldList.add("抄袭或侵权");
        fieldList.add("涉黄赌毒信息");
        fieldList.add("内容质量低劣");
        fieldList.add("涉政敏感信息");
        fieldList.add("其他不合适内容");
        return fieldList;
    }

    /**
     * 获取文库添加菜单 - text
     *
     * @return
     */
    public static List<String> getLibraryAddStrList() {
        List<String> fieldList = new ArrayList<>();
        fieldList.add("文章");
        fieldList.add("资料");
        fieldList.add("图片");
        fieldList.add("视频");
        fieldList.add("文库管理");
        fieldList.add("资料收藏");
        fieldList.add("草稿箱");
        return fieldList;
    }

    /**
     * 获取文库添加菜单 - imageResId
     *
     * @return
     */
    public static List<Integer> getLibraryAddImageResList() {
        List<Integer> fieldList = new ArrayList<>();
        fieldList.add(R.mipmap.mylibrary_writearticle);
        fieldList.add(R.mipmap.mylibrary_sendmatirial);
        fieldList.add(R.mipmap.mylibrary_sendpic);
        fieldList.add(R.mipmap.mylibrary_sendvideo);
        fieldList.add(R.mipmap.mylibrary_management);
        fieldList.add(R.mipmap.mylibrary_collection);
        fieldList.add(R.mipmap.mylibrary_draft);
        return fieldList;
    }

    /**
     * 图片文件格式集合
     *
     * @return
     */
    public static List<String> getImageTypeList() {
        List<String> imageTypeList = new ArrayList<>();
        imageTypeList.add("jpg");
        imageTypeList.add("gif");
        imageTypeList.add("png");
        imageTypeList.add("jpeg");
        imageTypeList.add("bmp");
        imageTypeList.add("pic");
        imageTypeList.add("webp");
        imageTypeList.add("exif");
        imageTypeList.add("ufo");
        imageTypeList.add("pcx");
        imageTypeList.add("tif");
        imageTypeList.add("tga");
        imageTypeList.add("fpx");
        imageTypeList.add("svg");
        imageTypeList.add("psd");
        imageTypeList.add("hdri");
        imageTypeList.add("raw");
        imageTypeList.add("wmf");
        imageTypeList.add("emf");
        imageTypeList.add("ico");
        imageTypeList.add("tiff");
        return imageTypeList;
    }

    /**
     * 文件格式集合
     *
     * @return
     */
    public static List<String> getFileTypeList() {
        List<String> fileTypeList = new ArrayList<>();
        fileTypeList.add("doc");
        fileTypeList.add("docx");
        fileTypeList.add("xls");
        fileTypeList.add("xlsx");
        fileTypeList.add("ppt");
        fileTypeList.add("pptx");
        fileTypeList.add("pdf");
        fileTypeList.add("txt");
        fileTypeList.add("zip");
        fileTypeList.add("rar");
        fileTypeList.add("7z");
        return fileTypeList;
    }

    /**
     * 文档文件格式集合
     *
     * @return
     */
    public static List<String> getDocumentTypeList() {
        List<String> fileTypeList = new ArrayList<>();
        fileTypeList.add("doc");
        fileTypeList.add("docx");
        fileTypeList.add("xls");
        fileTypeList.add("xlsx");
        fileTypeList.add("ppt");
        fileTypeList.add("pptx");
        fileTypeList.add("pdf");
        return fileTypeList;
    }

    /**
     * 视频文件格式集合
     *
     * @return
     */
    public static List<String> getVideoFileTypeList() {
        List<String> videoTypeList = new ArrayList<>();
        videoTypeList.add("mp4");
        videoTypeList.add("avi");
        videoTypeList.add("wmv");
        return videoTypeList;
    }

    /**
     * 文件格式集合
     * doc,docx,ppt,pptx,xls,xlsx,wps,pdf,mp4,avi,wmv,jpg,jpeg,png,rar,7z,zip
     *
     * @return
     */
    public static List<String> getFileTypeListState1() {
        List<String> imageTypeList = new ArrayList<>();
        imageTypeList.add("doc");
        imageTypeList.add("docx");
        imageTypeList.add("ppt");
        imageTypeList.add("pptx");
        imageTypeList.add("xls");
        imageTypeList.add("xlsx");
        imageTypeList.add("wps");
        imageTypeList.add("pdf");
        imageTypeList.add("mp4");
        imageTypeList.add("avi");
        imageTypeList.add("wmv");
        imageTypeList.add("jpg");
        imageTypeList.add("jpeg");
        imageTypeList.add("png");
        imageTypeList.add("zip");
        imageTypeList.add("rar");
        imageTypeList.add("7z");
        return imageTypeList;
    }

    /**
     * 常数
     */
    public static int zero = 0;
    public static int one = 1;
    public static int two = 2;
    public static int three = 3;
    public static int four = 4;
    public static int five = 5;
}
