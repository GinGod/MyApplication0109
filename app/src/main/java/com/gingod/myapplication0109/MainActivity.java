package com.gingod.myapplication0109;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gingod.myapplication0109.base.BaseSimpleActivity;
import com.gingod.myapplication0109.common.SelectFileActivity;
import com.gingod.myapplication0109.common.SelectMultiFileActivity;
import com.gingod.myapplication0109.config.CommonConfig;
import com.orhanobut.logger.Logger;
import com.othershe.combinebitmap.CombineBitmap;
import com.othershe.combinebitmap.layout.DingLayoutManager;
import com.othershe.combinebitmap.layout.WechatLayoutManager;
import com.othershe.combinebitmap.listener.OnProgressListener;
import com.othershe.combinebitmap.listener.OnSubItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;


public class MainActivity extends BaseSimpleActivity {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    private String[] IMG_URL_ARR = {
            "http://10.66.5.124:8080//uploadnet/pic/personal/datapic/16070667992191607066799219.jpg",
            "http://10.66.5.124:8080//uploadnet/pic/personal/datapic/16081090591361608109059136.jpg",
            "http://10.66.5.124:8080//uploadnet/pic/personal/datapic/16080922844951608092284495.jpg",
            "http://10.66.5.124:8080//uploadnet/pic/personal/datapic/16080820054381608082005438.jpg",
            "http://img.hb.aicdn.com/a1f189d4a420ef1927317ebfacc2ae055ff9f212148fb-iEyFWS_fw658",
            "http://img.hb.aicdn.com/69b52afdca0ae780ee44c6f14a371eee68ece4ec8a8ce-4vaO0k_fw658",
            "http://img.hb.aicdn.com/9925b5f679964d769c91ad407e46a4ae9d47be8155e9a-seH7yY_fw658",
            "http://img.hb.aicdn.com/e22ee5730f152c236c69e2242b9d9114852be2bd8629-EKEnFD_fw658",
            "http://img.hb.aicdn.com/73f2fbeb01cd3fcb2b4dccbbb7973aa1a82c420b21079-5yj6fx_fw658",
    };

    private String test = "/uploadnet/opt/pic/1616299321859video_1616299296522.jpg|||/uploadnet/opt/video/1616299321859video_1616299296522.m3u8|||/uploadnet/library/data/U3sb2OwQ/1616299299920video_1616299296522.mp4|||/uploadnet/opt/video/1616299321859video_1616299296522.mp4";

    private Context context;
    private String url = "http://img.hb.aicdn.com/eca438704a81dd1fa83347cb8ec1a49ec16d2802c846-laesx2_fw658";
    private View tv_main;
    private ImageView iv_main;
    private ImageView iv_main2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initValues() {
        context = this;
        tv_main = findViewById(R.id.tv_main);
        iv_main = findViewById(R.id.iv_main);
        iv_main2 = findViewById(R.id.iv_main2);

        Logger.e(StringUtils.getOneIndexOfStr(test) + " " + StringUtils.getTwoIndexOfStr(test)
                + " " + StringUtils.getThreeIndexOfStr(test) + " " + StringUtils.getFourIndexOfStr(test) + " " + StringUtils.getNameOfUrl(test));

        try {
            initRv();
            initPermission();
            pickLocalFile();
            containWithE();
            doGlide();
            loadWechatBitmap(iv_main2, 9);
            list();
            anim();
            webView();
            sortList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initRv() {
        rvMain.setLayoutManager(new LinearLayoutManager(mActivity));
        BaseQuickAdapter baseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_dragger, CommonConfig.getFieldList()) {
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
                baseViewHolder.setText(R.id.tv_item_dragger, s);
            }
        };
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                switch (position) {
                    case 0:
                        openActivity(TestPicWidthWebViewActivity.class);
                        break;
                    case 1:
                        openActivity(TestVideoFullActivity.class);
                        break;
                    case 2:
                        openActivity(SignActivity.class);
                        break;
                    case 3:
                        openActivity(OpenFileActivity2.class);
                        break;
                    case 4:
                        openActivity(SelectFileActivity.class);
                        break;
                    case 5:
                        openActivity(SelectMultiFileActivity.class);
                        break;
                    case 6:
                        openActivity(OpenFileActivity.class);
                        break;
                }
            }
        });
        rvMain.setAdapter(baseQuickAdapter);
    }

    private void sortList() {
        List list = new ArrayList();
        list.add("bbb");
        list.add("111");
        list.add("aaa");
        list.add("我们");
        list.add("22");
        list.add("0");
        list.add("按按");
        Logger.e("sortList: " + JSON.toJSONString(list));
// 获取中文环境
        Comparator comparator = Collator.getInstance(Locale.CHINA);
        // 排序实现
        Collections.sort(list, (e1, e2) -> {
            String str1 = e1.toString().substring(0, 1);
            String str2 = e2.toString().substring(0, 1);
            Logger.e("sortList: " + str1 + " " + str2);
            return Collator.getInstance(Locale.ENGLISH).compare(e1, e2);
        });
        Logger.e("sortList: " + JSON.toJSONString(list));
    }

    private void webView() {
        tv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url", "https://www.baidu.com");
                bundle.putString("url", "file:///android_asset/zx-editor-master/index.html");
//                bundle.putString("url", "file:///android_asset/kindeditor/index.html");
//                bundle.putString("url", "http://kindeditor.net/ke4/examples/default.html");
                openActivity(WebviewActivity.class, bundle, false);
            }
        });
    }

    private void anim() {
        BasisAnimUtils.anim(mActivity, iv_main2, R.anim.anim_view_shake_upanddown);
        mBasisHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv_main2.getAnimation().cancel();
            }
        }, 252 * 10);
    }

    private void list() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    if (i == 0) {
                        Logger.i("" + i);
                    }
                    if (i == 999999) {
                        Logger.i("" + i);
                    }
                }
            }
        }).start();
    }

    //申请必要的权限
    private void initPermission() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        boolean isPer = EasyPermissions.hasPermissions(this, perms);
        if (isPer) {

        } else {
//            EasyPermissions.requestPermissions(this,
//                    "Need permissions for camera & microphone & read_external_storage", 0, perms);

            EasyPermissions.requestPermissions(this,
                    "需要相机和麦克风的权限以及外部存储器的读取权限", 0, perms);
        }
        Logger.e(14 % 7 + " " + 15 % 7 + " " + 16 % 7 + " " + 17 % 7 + " " + 18 % 7 + " " + 19 % 7 + " " + 20 % 7);
    }

    private void pickLocalFile() {
        iv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFile();
            }
        });
    }

    private void doGlide() {
        Glide.with(this).load(url).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(context).load(bitmap).into(iv_main);
                    }
                });
                Log.e("11111", "111");
                return false;
            }
        }).submit();

//        Glide.with(context).load(url).error(R.mipmap.ic_launcher).into(iv_main);
//        Glide.with(context).load(R.mipmap.ic_launcher).into(iv_main);
    }

    /**
     * 打开系统的文件选择器
     */
    public void pickFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        this.startActivityForResult(intent, 1);
    }

    private void containWithE() {
        String str = "1.2345E8";
        String strNull = null;
        BigDecimal bigDecimal = new BigDecimal(str);
        long l = bigDecimal.longValue();
        Log.e("11", "aaa " + l
                        + " " + new BigDecimal("3.3").longValue()
                        + " " + new BigDecimal("3.0").longValue()
                        + " " + new BigDecimal("3").longValue()
                        + " " + "3.0".contains(".")
                        + " " + "30".contains(".")
                //+ " " + new BigDecimal("null").longValue()
                //+ " " + new BigDecimal("").longValue()
                //+ " " + new BigDecimal(strNull).longValue()
        );
    }

    // 获取文件的真实路径
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data == null) {
                // 用户未选择任何文件，直接返回
                return;
            }
            // 获取用户选择文件的URI
            Uri uri = data.getData();
//            // 通过ContentProvider查询文件路径
//            ContentResolver resolver = this.getContentResolver();
//            Cursor cursor = resolver.query(uri, null, null, null, null);
//            if (cursor == null) {
//                // 未查询到，说明为普通文件，可直接通过URI获取文件路径
//                String path = uri.getPath();
//                Logger.e(path + "");
//                Log.e("11", "aaa " + path);
//                return;
//            }
//            if (cursor.moveToFirst()) {
//                // 多媒体文件，从数据库中获取文件的真实路径
//                String path = cursor.getString(cursor.getColumnIndex("_data"));
//                Logger.e(path + "");
//                Log.e("11", "aaa " + path);
//            }
//            cursor.close();
            String path = getPathFromUri(this, uri);
            Logger.e(path + "");
            Log.e("11", "aaa " + path);
        }
    }

    public static String getPathFromUri(final Context context, final Uri uri) {
        if (uri == null) {
            return null;
        }
        // 判斷是否為Android 4.4之後的版本
        final boolean after44 = Build.VERSION.SDK_INT >= 19;
        if (after44 && DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是Android 4.4之後的版本，而且屬於文件URI
            final String authority = uri.getAuthority();
            // 判斷Authority是否為本地端檔案所使用的
            if ("com.android.externalstorage.documents".equals(authority)) {
                // 外部儲存空間
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] divide = docId.split(":");
                final String type = divide[0];
                if ("primary".equals(type)) {
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/").concat(divide[1]);
                    return path;
                } else {
                    String path = "/storage/".concat(type).concat("/").concat(divide[1]);
                    return path;
                }
            } else if ("com.android.providers.downloads.documents".equals(authority)) {
                // 下載目錄
                final String docId = DocumentsContract.getDocumentId(uri);
                if (docId.startsWith("raw:")) {
                    final String path = docId.replaceFirst("raw:", "");
                    return path;
                }
                final Uri downloadUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(docId));
                String path = queryAbsolutePath(context, downloadUri);
                return path;
            } else if ("com.android.providers.media.documents".equals(authority)) {
                // 圖片、影音檔案
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] divide = docId.split(":");
                final String type = divide[0];
                Uri mediaUri = null;
                if ("image".equals(type)) {
                    mediaUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    mediaUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    mediaUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                } else {
                    return null;
                }
                mediaUri = ContentUris.withAppendedId(mediaUri, Long.parseLong(divide[1]));
                String path = queryAbsolutePath(context, mediaUri);
                return path;
            }
        } else {
            // 如果是一般的URI
            final String scheme = uri.getScheme();
            String path = null;
            if ("content".equals(scheme)) {
                // 內容URI
                path = queryAbsolutePath(context, uri);
            } else if ("file".equals(scheme)) {
                // 檔案URI
                path = uri.getPath();
            }
            return path;
        }
        return null;
    }

    public static String queryAbsolutePath(final Context context, final Uri uri) {
        final String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                String[] columnNames = cursor.getColumnNames();
                Logger.e(columnNames.toString());
                final int index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                return cursor.getString(index);
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    private String[] getUrls(int count) {
        String[] urls = new String[count];
        System.arraycopy(IMG_URL_ARR, 0, urls, 0, count);
        return urls;
    }


    private void loadDingBitmap(final ImageView imageView, int count) {
        CombineBitmap.init(this)
                .setLayoutManager(new DingLayoutManager())
                .setSize(180)
                .setGap(2)
                .setUrls(getUrls(count))
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onComplete(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                })
                .build();
    }

    private void loadWechatBitmap(ImageView imageView, int count) {
        CombineBitmap.init(MainActivity.this)
                .setLayoutManager(new WechatLayoutManager())
                .setSize(180)
                .setGap(3)
                .setGapColor(Color.parseColor("#E8E8E8"))
                .setUrls(getUrls(count))
                .setImageView(imageView)
                .setOnSubItemClickListener(new OnSubItemClickListener() {
                    @Override
                    public void onSubItemClick(int index) {
                        Log.e("SubItemIndex", "--->" + index);
                        openActivity(DraggerActivity.class);
                    }
                })
                .build();

        Logger.e(IMG_URL_ARR.toString());
        Logger.e(JSON.toJSONString(IMG_URL_ARR));
    }


}