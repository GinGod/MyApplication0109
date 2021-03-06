package com.gingod.myapplication0109.utils;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.gingod.myapplication0109.R;
import com.gingod.myapplication0109.WebviewActivity;
import com.gingod.myapplication0109.base.Api;
import com.gingod.myapplication0109.base.MessageUtils;
import com.gingod.myapplication0109.bean.FieldBean;
import com.gingod.myapplication0109.bean.FileInfo;
import com.gingod.myapplication0109.config.CommonConfig;
import com.gingold.basislibrary.Base.BasisBaseUtils;
import com.gingold.basislibrary.utils.BasisDisplayUtils;
import com.gingold.basislibrary.utils.BasisSPUtils;
import com.gingold.basislibrary.utils.BasisTimesUtils;
import com.gingold.basislibrary.utils.dialog.BasisDSClickListener;
import com.gingold.basislibrary.utils.dialog.BasisDialogUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions3.Permission;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import io.reactivex.rxjava3.functions.Consumer;

/**
 * ???????????????????????????
 *
 * @author
 */
public class CommonUtils {


    /**
     * ??????????????????????????????
     */
    public static void setOnTextChangeListener(EditText tv_add, View iv_add_close) {
        iv_add_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_add.setText("");
            }
        });
        tv_add.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (MessageUtils.isEmpty(s.toString())) {
                    iv_add_close.setVisibility(View.GONE);
                } else {
                    iv_add_close.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * ??????????????????????????????
     */
    public static void setDownFilePic(Context context, ImageView iv_item_file_list, String fileDownloadUrl, String fileName) {
        try {
            if (isPicture(fileName)) {
                Glide.with(context).load(Api.URL_BASE + fileDownloadUrl).error(R.mipmap.attach_question).into(iv_item_file_list);
            } else {
                Glide.with(context).load(FileUtil.getFileTypeImageId2(context, fileName)).centerCrop().error(R.mipmap.attach_question).into(iv_item_file_list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????????????????????????????
     */
    public static boolean isPicture(String name) {
        try {
            List<String> imageTypeList = CommonConfig.getImageTypeList();
            for (int i = 0; i < imageTypeList.size(); i++) {
                String type = imageTypeList.get(i);
                if (name.endsWith(type)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * ??????????????????????????????
     */
    public static String getFileLocalPath(String url, String fileName) {
        try {
            if (MessageUtils.isEmpty(fileName)) {
                fileName = url.substring(url.lastIndexOf("/" + 1));
            }
            return Environment.getExternalStorageDirectory() + "/File/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * ????????????????????????
     */
    public static FileInfo getFileInfo(File file) {
        try {
            String name = file.getName();
            if ((name.endsWith(".doc") || name.endsWith(".docx") || name.endsWith(".xls")
                    || name.endsWith(".xlsx") || name.endsWith(".ppt") || name.endsWith(".pptx")
                    || name.endsWith(".pdf") || name.endsWith(".dwg") || name.endsWith(".txt")
                    || name.endsWith(".zip") || name.endsWith(".rar") || name.endsWith(".7z")
                    || name.endsWith(".apk"))) {
                //?????????????????? ?????????????????????
                return FileUtil.getFileInfoFromFile(new File(file.getAbsolutePath()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ????????????????????????
     *
     * @return
     */
    public static FileInfo getFileInfoState1(File file) {
        try {
            String name = file.getName();
            List<String> fileTypeListState1 = CommonConfig.getFileTypeListState1();
            for (int i = 0; i < fileTypeListState1.size(); i++) {
                if (name.toLowerCase().endsWith(fileTypeListState1.get(i))) {
                    return FileUtil.getFileInfoFromFile(new File(file.getAbsolutePath()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ??????????????????????????????
     */
    public static void openPrivacyActivity(Context context) {
        try {
            Intent intent = new Intent(context, WebviewActivity.class);
            intent.putExtra("url", "https://privacy.1000dpt.com/");
            intent.putExtra("title", "??????????????????");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????????????????
     */
    public static void checkCameraPermission(FragmentActivity activity, String message, OnRequestPermissionResult onRequestPermissionResult) {
        checkPermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, activity, message, onRequestPermissionResult);
    }

    /**
     * ??????????????????
     */
    public static void checkAudioPermission(FragmentActivity activity, String message, OnRequestPermissionResult onRequestPermissionResult) {
        checkPermission(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, activity, message, onRequestPermissionResult);
    }

    /**
     * ??????????????????
     */
    public static void checkStoragePermission(FragmentActivity activity, String message, OnRequestPermissionResult onRequestPermissionResult) {
        checkPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, activity, message, onRequestPermissionResult);
    }

    /**
     * ????????????
     */
    public static void checkPermission(String[] permissionStrs, FragmentActivity activity, String message, OnRequestPermissionResult onRequestPermissionResult) {
        try {
            new RxPermissions(activity).requestEach(permissionStrs).subscribe(new Consumer<Permission>() {
                @Override
                public void accept(Permission permission) {
                    try {
                        //????????????
                        if (permission.granted) {
                            if (onRequestPermissionResult != null && permissionStrs[0].equals(permission.name)) {
                                onRequestPermissionResult.onRequestSuccess();
                            }
                            //??????????????????
                        } else if (MessageUtils.isNotEmpty(message)) {
                            BasisDialogUtils.build(activity, "??????", message, new BasisDSClickListener() {
                                @Override
                                public void onConfirm() {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.setData(Uri.parse("package:" + activity.getPackageName()));
                                    activity.startActivityForResult(intent, CommonConfig.R_CODE_PERMISSION);
                                }
                            }).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????????????????
     *
     * @param path
     * @return
     */
    public static String getVideoTime(String path) {
        try {
            MediaMetadataRetriever media = new MediaMetadataRetriever();
            media.setDataSource(path);
            String duration = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            Long time = Long.parseLong(duration);
            return Utils.long2String(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * ??????????????????
     *
     * @param path
     * @return
     */
    public static Bitmap getVideoBitmap(String path) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(path);
        return media.getFrameAtTime();
    }

    /**
     * ??????????????????
     */
    public interface OnRequestPermissionResult {
        void onRequestSuccess();
    }

    /**
     * ???????????????????????????
     *
     * @return
     */
    public static String hidePhone(String phone) {
        try {
            StringBuilder stringBuilder = new StringBuilder(phone);
            int leg = phone.length();
            int thr = 3;
            int fou = 4;
            StringBuilder str1 = new StringBuilder();
            for (int i = thr; i < leg - fou; i++) {
                str1.append("*");
            }
            stringBuilder.replace(thr, phone.length() - fou, str1.toString());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phone;
    }


    /**
     * ????????????
     */
    public static Date getDateStr(String day) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date nowDate = df.parse(day);
            return nowDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * ??????????????????????????????
     */
    public static Map<String, String> buildOrderParamMap(String app_id, boolean rsa2) {
        Map<String, String> keyValues = new HashMap<String, String>();
        keyValues.put("app_id", app_id);
        keyValues.put("biz_content",
                "{\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"0.01\",\"subject\":\"1\",\"body\":\"??????????????????\",\"out_trade_no\":\""
                        + getOutTradeNo() + "\"}");
        keyValues.put("charset", "utf-8");
        keyValues.put("method", "alipay.trade.app.pay");
        keyValues.put("sign_type", "RSA2");
        keyValues.put("timestamp", BasisTimesUtils.getDeviceTime());
        keyValues.put("version", "1.0");
        return keyValues;
    }

    /**
     * ??????????????????????????????
     *
     * @param map ??????????????????
     * @return
     */
    public static String buildOrderParam(Map<String, String> map) {
        List<String> keys = new ArrayList<String>(map.keySet());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            sb.append(buildKeyValue(key, value, true));
            sb.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        sb.append(buildKeyValue(tailKey, tailValue, true));

        return sb.toString();
    }

    /**
     * ???????????????
     *
     * @param key
     * @param value
     * @param isEncode
     * @return
     */
    private static String buildKeyValue(String key, String value, boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                sb.append(value);
            }
        } else {
            sb.append(value);
        }
        return sb.toString();
    }


    /**
     * ????????????????????????????????????
     *
     * @return
     */
    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * ??????????????????
     *
     * @return
     */
    public static String getClassifyNumOrder(List<FieldBean> myFieldBeanList) {
        StringBuilder classifyNumOrder = new StringBuilder();
        for (int i = 1; i < myFieldBeanList.size() - 1; i++) {
            FieldBean fieldBean = myFieldBeanList.get(i);
            if (MessageUtils.isNotEmpty(classifyNumOrder.toString())) {
                classifyNumOrder.append(",").append(fieldBean.id);
            } else {
                classifyNumOrder.append(fieldBean.id);
            }
        }
        return classifyNumOrder.toString();
    }

    /**
     * ???????????????(??????)
     *
     * @return yyyy-MM-dd HH:mm:ss:SSS
     */
    public static String getDeviceTimeOfSSS() {
        String date = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            date = df.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            //???????????????long?????????
            date = System.currentTimeMillis() + "";
        }
        return date;
    }


    /**
     * ??????
     */
    public static void setSendUnabled(TextView view, String message) {
        if (view != null) {
            view.setEnabled(false);
            view.setBackgroundResource(R.drawable.bg_tv_cicle_send_presss_yes);
            if (MessageUtils.isNotEmpty(message)) {
                view.setText(message);
            }
        }
    }


    /**
     * ??????????????????
     */
    public static void imageOrVideoCapture(FragmentActivity context, File pictureFile, String action, int requestCode) {
        Intent intent;
        Uri pictureUri;
        // ??????????????????
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = new Intent(action);
            //?????????????????????
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //""???????????????????????????????????????package???.provider???????????????????????????
            pictureUri = FileProvider.getUriForFile(context,
                    context.getPackageName() + ".fileprovider", pictureFile);
        } else {
            intent = new Intent(action);
            pictureUri = Uri.fromFile(pictureFile);
        }
        // ?????????,?????????????????????pictureUri??????????????????
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * ??????????????????
     */
    public static void imageCapture(FragmentActivity context, File pictureFile, int requestCode) {
        imageOrVideoCapture(context, pictureFile, MediaStore.ACTION_IMAGE_CAPTURE, requestCode);
    }

    /**
     * ??????????????????
     */
    public static void VideoCapture(FragmentActivity context, File pictureFile, int requestCode) {
        imageOrVideoCapture(context, pictureFile, MediaStore.ACTION_VIDEO_CAPTURE, requestCode);
    }


    /**
     * pdf??????
     *
     * @param mActivity
     * @param pdfView
     * @param localPath
     * @param defaultPage
     * @param onPageScrollListener
     */
    public static void initOpenPdfFile(Context mActivity, PDFView pdfView, String localPath, int defaultPage, OnPageScrollListener onPageScrollListener) {
        try {
            pdfView.fromFile(new File(localPath))
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(defaultPage)
                    .onPageScroll(onPageScrollListener)
                    .enableAnnotationRendering(false)
                    .password(null)
                    .scrollHandle(null)
                    .enableAntialiasing(true)
                    .spacing(com.gingold.basislibrary.utils.BasisDisplayUtils.dp2px(mActivity, 2))
                    .load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????pdf????????????Bitmap
     *
     * @param mActivity
     * @param file
     * @param page
     * @return
     */
    public static Bitmap getPdfPageBitmap(Context mActivity, File file, int page) {
        try {
            ParcelFileDescriptor open = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
            PdfRenderer pdfRenderer = new PdfRenderer(open);
            return CommonUtils.renderPage(mActivity, pdfRenderer.openPage(page));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ???????????????Bitmap
     *
     * @param mActivity
     * @param page
     * @return
     */
    public static Bitmap renderPage(Context mActivity, PdfRenderer.Page page) {
        try {
            Bitmap bitmap = Bitmap.createBitmap(BasisDisplayUtils.getScreenWidth(mActivity), page.getHeight() * BasisDisplayUtils.getScreenWidth(mActivity) / page.getWidth(), Bitmap.Config.ARGB_8888);
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            page.close();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * ???assets??????????????????sd???????????????
     *
     * @param context    ?????????
     * @param assetsPath assets????????????
     */
    public static String putAssetsToSDCard(Context context, String assetsPath) {
        try {
            InputStream mIs = context.getAssets().open(assetsPath);
            byte[] mByte = new byte[1024 * 8];
            int bt = 0;
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + assetsPath);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                return file.getAbsolutePath();
            }
            FileOutputStream fos = new FileOutputStream(file);
            while ((bt = mIs.read(mByte)) != -1) {
                fos.write(mByte, 0, bt);
            }
            fos.flush();
            mIs.close();
            fos.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * ??????6?????????
     *
     * @param o
     * @return
     */
    public static String getFormat6(Object o) {
        return new DecimalFormat("0.000000").format(o);
    }
}
