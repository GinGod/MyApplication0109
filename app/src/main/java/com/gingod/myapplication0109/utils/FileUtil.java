package com.gingod.myapplication0109.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.storage.StorageManager;

import com.alibaba.fastjson.JSON;
import com.gingod.myapplication0109.MyApplication;
import com.gingod.myapplication0109.R;
import com.gingod.myapplication0109.base.MessageUtils;
import com.gingod.myapplication0109.bean.FileInfo;
import com.gingod.myapplication0109.config.CommonConfig;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


/**
 * Created by CWJ on 2017/3/20.
 */

public class FileUtil {

    /****
     * 计算文件大小
     *
     * @param length
     * @return
     */
    public static String getFileSzie(Long length) {
        if (length >= 1048576) {
            return (length / 1048576) + "MB";
        } else if (length >= 1024) {
            return (length / 1024) + "KB";
        } else if (length < 1024) {
            return length + "B";
        } else {
            return "0KB";
        }
    }

    /**
     * 转换文件大小
     */
    public static String FormetFileSize(long fileS) {
        try {
            DecimalFormat df = new DecimalFormat("#.00");
            String fileSizeString = "";
            String wrongSize = "0B";
            if (fileS == 0) {
                return wrongSize;
            }
            if (fileS < 1024) {
                fileSizeString = df.format((double) fileS) + "B";
            } else if (fileS < 1048576) {
                fileSizeString = df.format((double) fileS / 1024) + "KB";
            } else if (fileS < 1073741824) {
                fileSizeString = df.format((double) fileS / 1048576) + "MB";
            } else {
                fileSizeString = df.format((double) fileS / 1073741824) + "GB";
            }
            return fileSizeString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 字符串时间戳转时间格式
     *
     * @param timeStamp
     * @return
     */
    public static String getStrTime(String timeStamp) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        long l = Long.valueOf(timeStamp) * 1000;
        timeString = sdf.format(new Date(l));
        return timeString;
    }

    /**
     * 读取文件的最后修改时间的方法
     */
    public static String getFileLastModifiedTime(File f) {
        Calendar cal = Calendar.getInstance();
        long time = f.lastModified();
        SimpleDateFormat formatter = new
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTimeInMillis(time);
        return formatter.format(cal.getTime());
    }

    /**
     * 获取扩展内存的路径
     *
     * @param mContext
     * @return
     */
    public static String getStoragePath(Context mContext) {

        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getFileTypeImageId(Context mContext, String fileName) {
        int id;
        if (checkSuffix(fileName, new String[]{"doc", "docx"})) {
            id = R.mipmap.word;
        } else if (checkSuffix(fileName, new String[]{"ppt", "pptx"})) {
            id = R.mipmap.ppt;
        } else if (checkSuffix(fileName, new String[]{"xls", "xlsx"})) {
            id = R.mipmap.xlsx;
        } else if (checkSuffix(fileName, new String[]{"pdf"})) {
            id = R.mipmap.pdf;
        } else {
            id = R.mipmap.pwother;
        }
        return id;
    }

    public static int getFileTypeImageId2(Context mContext, String fileName) {
        int id = R.mipmap.attach_question;
        try {
            if (checkSuffix(fileName, new String[]{"doc", "docx"})) {
                id = R.mipmap.attach_docx;
            } else if (checkSuffix(fileName, new String[]{"ppt", "pptx"})) {
                id = R.mipmap.attach_pptx;
            } else if (checkSuffix(fileName, new String[]{"xls", "xlsx"})) {
                id = R.mipmap.attach_xlxs;
            } else if (checkSuffix(fileName, new String[]{"pdf"})) {
                id = R.mipmap.attach_pdf;
            } else if (checkSuffix(fileName, new String[]{"txt"})) {
                id = R.mipmap.attach_txt;
            } else if (checkSuffix(fileName, new String[]{"zip"})) {
                id = R.mipmap.attach_zip;
            } else if (checkSuffix(fileName, new String[]{"rar"})) {
                id = R.mipmap.attach_rar;
            } else if (checkSuffix(fileName, new String[]{"mp4", "avi", "wmv"})) {
                id = R.mipmap.attach_vedio;
            } else if (checkSuffix(fileName, new String[]{"jpg", "gif", "png", "jpeg", "bmp", "pic"})) {
                id = R.mipmap.attach_question;
            } else {
                id = R.mipmap.attach_question;
            }
        } catch (Exception e) {
            e.printStackTrace();
            id = R.mipmap.attach_question;
        }
        return id;
    }

    public static boolean checkSuffix(String fileName,
                                      String[] fileSuffix) {
        for (String suffix : fileSuffix) {
            if (fileName != null) {
                if (fileName.toLowerCase().endsWith(suffix)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断当前文件是否是图片
     */
    public static boolean getFileIsImage(String fileName) {
        try {
            if (MessageUtils.isEmpty(fileName)) {
                return false;
            }
            fileName = fileName.toLowerCase();
            List<String> imageTypeList = CommonConfig.getImageTypeList();
            for (int i = 0; i < imageTypeList.size(); i++) {
                String type = imageTypeList.get(i);
                if (fileName.endsWith(type) || fileName.equals(type)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断当前文件是否是文件
     */
    public static boolean getFileIsFile(String fileName) {
        try {
            if (MessageUtils.isEmpty(fileName)) {
                return false;
            }
            fileName = fileName.toLowerCase();
            List<String> fileTypeList = CommonConfig.getFileTypeList();
            for (int i = 0; i < fileTypeList.size(); i++) {
                String type = fileTypeList.get(i);
                if (fileName.endsWith(type) || fileName.equals(type)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断当前文件是否是文档
     */
    public static boolean getFileIsDocument(String fileName) {
        try {
            if (MessageUtils.isEmpty(fileName)) {
                return false;
            }
            fileName = fileName.toLowerCase();
            List<String> fileTypeList = CommonConfig.getDocumentTypeList();
            for (int i = 0; i < fileTypeList.size(); i++) {
                String type = fileTypeList.get(i);
                if (fileName.endsWith(type) || fileName.equals(type)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断当前文件是否是视频
     */
    public static boolean getFileIsVideo(String fileName) {
        try {
            if (MessageUtils.isEmpty(fileName)) {
                return false;
            }
            fileName = fileName.toLowerCase();
            List<String> fileTypeList = CommonConfig.getVideoFileTypeList();
            for (int i = 0; i < fileTypeList.size(); i++) {
                String type = fileTypeList.get(i);
                if (fileName.endsWith(type) || fileName.equals(type)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 文件过滤,将手机中隐藏的文件给过滤掉
     */
    public static File[] fileFilter(File file) {
        File[] files = file.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return !pathname.isHidden();
            }
        });
        return files;
    }

    private static List<FileInfo> FilesInfo(File fileDir, Context mContext) {
        List<FileInfo> videoFilesInfo = new ArrayList<>();
        File[] listFiles = fileFilter(fileDir);
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    FilesInfo(file, mContext);
                } else {
                    FileInfo fileInfo = getFileInfoFromFile(file);
                    videoFilesInfo.add(fileInfo);
                }
            }
        }
        return videoFilesInfo;
    }

    public static List<FileInfo> getFileInfosFromFileArray(File[] files) {
        List<FileInfo> fileInfos = new ArrayList<>();
        for (File file : files) {
            FileInfo fileInfo = getFileInfoFromFile(file);
            fileInfos.add(fileInfo);
        }
        Collections.sort(fileInfos, new FileNameComparator());
        return fileInfos;
    }

    /**
     * 根据文件名进行比较排序
     */
    public static class FileNameComparator implements Comparator<FileInfo> {
        protected final static int
                FIRST = -1,
                SECOND = 1;

        @Override
        public int compare(FileInfo lhs, FileInfo rhs) {
            return lhs.getFileName().compareToIgnoreCase(rhs.getFileName());
        }
    }

    /**
     * 获取文件信息
     */
    public static FileInfo getFileInfoFromFile(File file) {
        FileInfo fileInfo = new FileInfo();
        try {
            fileInfo.setFileName(file.getName());
            fileInfo.setFilePath(file.getPath());
            fileInfo.setFileSize(file.length());
            fileInfo.setTime(FileUtil.getFileLastModifiedTime(file));
            int lastDotIndex = file.getName().lastIndexOf(".");
            if (lastDotIndex > 0) {
                String fileSuffix = file.getName().substring(lastDotIndex + 1);
                fileInfo.setSuffix(fileSuffix);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return fileInfo;
    }

    /**
     * 将字符串转为MD5
     */
    public static String getMD5Str(String value) {
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
     * 头像缓存
     */
    public static File getImageCacheFile(String fileName) {
        return getFile(fileName, 1);
    }

    /**
     * 图片下载
     */
    public static File getImageDownloadFile(String fileName) {
        return getFile(fileName, 0);
    }

    /**
     * 文件下载
     */
    public static File getDownloadFile(String fileName) {
        return getFile(fileName, 2);
    }

    /**
     * 获取文件
     */
    public static File getFile(String fileName, int type) {
        String storePath = null;
        if (type == 0) {
            storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Pictures";
        } else if (type == 1) {
            storePath = MyApplication.getInstance().getExternalFilesDir(null) + File.separator + "topic";
        } else {
            storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "File";

        }
        File appDir = new File(storePath);
        if (!appDir.exists() || appDir.isFile()) {
            boolean isDir = appDir.mkdirs();
            if (!isDir) {
                Logger.e("文件创建失败");
            }
        }
        return new File(appDir, fileName);
    }

    /**
     * 将Bitmap储存在本地文件中
     */
    public static Boolean saveBitmapToFile(Bitmap bitmap, File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return isSuccess;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 图片缓存文件是否存在
     */
    public static boolean isImageCacheFileExists(String[] url) {
        return isImageCacheFileExists(JSON.toJSONString(url));
    }

    /**
     * 图片缓存文件是否存在
     */
    public static boolean isImageCacheFileExists(String url) {
        try {
            String fileName = FileUtil.getMD5Str(url) + ".jpg";
            File cacheFile = FileUtil.getImageCacheFile(fileName);
            if (cacheFile != null && cacheFile.exists() && cacheFile.isFile() && cacheFile.length() > 5252) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
