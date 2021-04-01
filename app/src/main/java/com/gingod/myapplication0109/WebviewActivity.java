package com.gingod.myapplication0109;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.gingod.myapplication0109.base.BaseSimpleActivity;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 公用H5页面
 * 带入两个数据，
 * 1: url
 * 2: title
 */
public class WebviewActivity extends BaseSimpleActivity {
    private WebView mWebview;
    WebSettings webSettings;
    private String html_url;
    private String html_title;
    private boolean loadState = false;//作业考试是否加载成功

    private String test = "/uploadnet/opt/pic/1616299321859video_1616299296522.jpg|||/uploadnet/opt/video/1616299321859video_1616299296522.m3u8|||/uploadnet/library/data/U3sb2OwQ/1616299299920video_1616299296522.mp4|||/uploadnet/opt/video/1616299321859video_1616299296522.mp4";


    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;

    @Override
    protected int getLayoutId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        return R.layout.activity_webview;
    }

    @Override
    public void initValues() {
        mWebview = findViewById(R.id.webview);
        initViews();
        AndroidBug5497Workaround.assistActivity(mActivity);
        Logger.e(StringUtils.getOneIndexOfStr(test) + " " + StringUtils.getTwoIndexOfStr(test)
                + " " + StringUtils.getThreeIndexOfStr(test) + " " + StringUtils.getFourIndexOfStr(test));
    }

    @Override
    public void initData() {
        initDatas();
    }

    public void initViews() {
        Bundle bundle = getIntent().getExtras();

        if (null != bundle) {
            html_url = bundle.getString("url");
            html_title = bundle.getString("title");
        }

        if (getIntent().getBooleanExtra("titleState", true)) {//是否显示标题栏
        }

        webSettings = mWebview.getSettings();
        /**设置网页字体不跟随系统字体发生改变*/
        webSettings.setTextZoom(100);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSavePassword(false);  // 不保存密码（弹窗）
        webSettings.setBlockNetworkImage(false); // 解决图片不显示
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) { //5.0及以上
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebview.addJavascriptInterface(new JavascriptInterfaces(this), "hexue_jsinterface");

        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAllowContentAccess(true); // 是否可访问Content Provider的资源，默认值 true
        webSettings.setAllowFileAccess(true);    // 是否可访问本地文件，默认值 true
        // 是否允许通过file url加载的Javascript读取本地文件，默认值 false
        webSettings.setAllowFileAccessFromFileURLs(false);
        // 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false
        webSettings.setAllowUniversalAccessFromFileURLs(false);

        //清理缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebview.clearCache(true);
    }

    public void initDatas() {

        //设置WebChromeClient类
        mWebview.setWebChromeClient(new WebChromeClient() {

            // For Android 3.0+
            public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                takePhoto();//拍照
            }

            //For Android 4.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                takePhoto();//拍照
            }

            // For Android 5.0+
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                mUploadCallbackAboveL = filePathCallback;
                takePhoto();//拍照
                return true;
            }


            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (TextUtils.isEmpty(html_title)) {
//                    title_base_title.setText(title);
                }
            }


            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

            }
        });


        //设置WebViewClient类
        mWebview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
            }
        });


        Map<String, String> map = new HashMap<String, String>();
//        map.put("access_token", LoginUtil.getLoginToken(this));
        mWebview.loadUrl(html_url, map);
    }

    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        try {
            if (mWebview != null) {
                mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
                mWebview.clearHistory();
                mWebview.setWebViewClient(null);
                mWebview.setWebChromeClient(null);
                mWebview.removeAllViews();
                ViewParent viewParent = mWebview.getParent();
                if (viewParent != null) {
                    ((ViewGroup) viewParent).removeAllViews();
                }
                mWebview.destroy();
                mWebview = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    /**
     * js通信接口
     * <p>
     * webview.addJavascriptInterface(Object obj, String interfaceName)
     * js中调用 window.interfaceName.dosomething
     */
    public class JavascriptInterfaces {

        private Context context;

        public JavascriptInterfaces(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public String getTokenId() {
            return "";
        }

        @JavascriptInterface
        public void closePage() {//h5提交试卷后直接退出
            finish();
        }

        @JavascriptInterface
        public void loadSuccess() {//h5加载数据成功回调
            loadState = true;
        }


    }

    @Override
    public void onBackPressed() {
        if (loadState) {//h5加载数据成功, 点击返回时触发提交试卷dialog
            mWebview.loadUrl("javascript:window.$submitTestPaper()");
        } else {//h5加载数据未成功, 直接退出
            super.onBackPressed();
        }
    }

    //拍照
    public void takePhoto() {
    }

    //上传Url
    private void upLoadUri(Uri uri) {
        try {
            if (uri == null) {
                uri = Uri.fromFile(new File(""));
            }
            if (mUploadCallbackAboveL != null) {
                mUploadCallbackAboveL.onReceiveValue(new Uri[]{uri});
                mUploadCallbackAboveL = null;
            } else if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(uri);
                mUploadMessage = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
