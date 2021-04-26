package com.gingod.myapplication0109.base;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.gingod.myapplication0109.WebviewActivity;


/**
 * BaseWebviewActivity
 * 带入两个数据，
 * 1: url
 * 2: title
 *
 * @author
 */
public abstract class BaseWebviewActivity2 extends BaseSimpleActivity {
    public static final String WEBVIEW_URL = "WEBVIEW_URL";
    public static final String WEBVIEW_TITLE = "WEBVIEW_TITLE";
    public WebView mWebview;
    public WebSettings webSettings;
    public String html_url;
    public String html_title;
    /**
     * 是否加载成功
     */
    public boolean loadState = false;
    /**
     * 是否需要对图片宽度进行调整
     */
    public boolean resetImgState = false;
    public ProgressBar progressBar;

    public String javascriptInterfacesName = "android";

    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 2;
    public String evaluationId, recipientType, recipientId, index;

    public View mCustomView;
    public WebChromeClient.CustomViewCallback mCustomViewCallback;

    public boolean screenOrientationState = false;

    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            html_url = bundle.getString(WEBVIEW_URL);
            html_title = bundle.getString(WEBVIEW_TITLE);
            if (tv_base_title != null && areNotEmpty(html_title)) {
                setTVText(html_title, tv_base_title);
            }
        }
        //是否显示标题栏
        if (getIntent().getBooleanExtra("titleState", true)) {
        }

        webSettings = mWebview.getSettings();
        /**设置网页字体不跟随系统字体发生改变*/
        webSettings.setTextZoom(100);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setDomStorageEnabled(true);
        //这里需要设置为true，才能让Webivew支持<meta>标签的viewport属性
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        // 不保存密码（弹窗）
        webSettings.setSavePassword(false);
        // 解决图片不显示
        webSettings.setBlockNetworkImage(false);
        //5.0及以上
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setDefaultTextEncodingName("UTF-8");
        // 是否可访问Content Provider的资源，默认值 true
        webSettings.setAllowContentAccess(true);
        // 是否可访问本地文件，默认值 true
        webSettings.setAllowFileAccess(true);
        // 是否允许通过file url加载的Javascript读取本地文件，默认值 false
        webSettings.setAllowFileAccessFromFileURLs(true);
        // 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        //缩放操作
//        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
//        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        webSettings.setDisplayZoomControls(true); //隐藏原生的缩放控件

        mWebview.addJavascriptInterface(new JavascriptInterfaces(), javascriptInterfacesName);

        initWebViewClient();
        loadUrl();
    }

    /**
     * 默认缓存加载
     */
    public void loadWithCache() {
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setAppCacheEnabled(true);
    }

    /**
     * 默认缓存加载
     */
    public void loadDefaultCache() {
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
    }

    /**
     * 不从缓存加载
     */
    public void loadNoCache() {
        //清理缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    /**
     * 加载Url
     */
    public void loadUrl() {
        loge(html_url);
        mWebview.loadUrl(html_url);
    }

    /**
     * 初始化webViewClient
     */
    public void initWebViewClient() {
        //设置WebChromeClient类
        mWebview.setWebChromeClient(new WebChromeClient() {

            // For 3.0+ Devices (Start)
            // onActivityResult attached before constructor
            protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
            }


            // For Lollipop 5.0+ Devices
            @Override
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;

                Intent intent = fileChooserParams.createIntent();
                //此处增加参数  允许多选文件
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                try {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e) {
                    uploadMessage = null;
                    Toast.makeText(getBaseContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }

            //For Android 4.1 only
            protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            }

            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
//                if (TextUtils.isEmpty(html_title)) {
//                    if (tv_base_title != null) {
//                        setTVText(title, tv_base_title);
//                    }
//                }
            }

            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                loge(newProgress + "");
                if (progressBar != null) {
                    progressBar.setProgress(newProgress);
                    if (newProgress == 100) {
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                showCustomView(view, callback);
                super.onShowCustomView(view, callback);
            }

            @Override
            public void onHideCustomView() {
                hideCustomView();
                super.onHideCustomView();
            }
        });

        //设置WebViewClient类
        mWebview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loge(url);
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
                loge("onPageStarted");
            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                loge("onPageFinished");
                if (resetImgState) {
                    imgReset();
                }
            }
        });
    }

    /**
     * 设置全屏播放
     *
     * @param view
     * @param callback
     */
    private void showCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        try {
            loge("onShowCustomView");
            // if a view already exists then immediately terminate the new one
            if (mCustomView != null && mCustomViewCallback != null) {
                mCustomViewCallback.onCustomViewHidden();
                return;
            }
            // 1. Stash the current state
            mCustomView = view;
            // 2. Stash the custom view callback
            mCustomViewCallback = callback;
            // 3. Add the custom view to the view hierarchy
            FrameLayout decor = (FrameLayout) getWindow().getDecorView();
            decor.addView(mCustomView, new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            mWebview.setVisibility(View.GONE);
            loge(decor.getWidth() + " " + decor.getHeight());
            if (screenOrientationState) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            //全屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //隐藏虚拟按键
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消全屏播放
     *
     * @return
     */
    private void hideCustomView() {
        try {
            loge("onHideCustomView");
            mWebview.setVisibility(View.VISIBLE);
            if (mCustomView == null) {
                return;
            }
            mCustomView.setVisibility(View.GONE);
            // 1. Remove the custom view
            FrameLayout decor = (FrameLayout) getWindow().getDecorView();
            decor.removeView(mCustomView);
            mCustomView = null;
            // 2. Call the custom view callback
            mCustomViewCallback.onCustomViewHidden();
            mCustomViewCallback = null;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            //显示虚拟按键
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decorView.setSystemUiVisibility(uiOptions);
            // 取消全屏
            final WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        try {
            super.onResume();
            if (mWebview != null) {
                // 暂停网页中正在播放的视频
                mWebview.onResume();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        try {
            super.onPause();
            if (mWebview != null) {
                // 暂停网页中正在播放的视频
                mWebview.onPause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击返回上一页面而不是退出浏览器
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        try {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    /** 回退键 事件处理 优先级:视频播放全屏-网页回退-关闭页面 */
                    if (mCustomView != null) {
                        mCustomViewCallback.onCustomViewHidden();
                    } else if (mWebview.canGoBack()) {
                        mWebview.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        try {
            //销毁Webview
            if (mWebview != null) {
                mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
//                mWebview.clearHistory();
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

        @JavascriptInterface
        public void doBack() {
            try {
                onBackPressed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void payment() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        doPayment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @JavascriptInterface
        public boolean bindingPaymentState() {
            return doIsBindingPayment();
        }

        @JavascriptInterface
        public void saveSuccess() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @JavascriptInterface
        public void firstAnswerComment(String evaluationid, String recipientid, String recipienttype, String indexId) {
            evaluationId = evaluationid;
            recipientType = recipienttype;
            recipientId = recipientid;
            index = indexId;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        doWriteComment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @JavascriptInterface
        public void secondAnswerComment(String evaluationid, String recipientid, String recipienttype, String indexId) {
            evaluationId = evaluationid;
            recipientType = recipienttype;
            recipientId = recipientid;
            index = indexId;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        doWriteComment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @JavascriptInterface
        public void skip(String url) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (!TextUtils.isEmpty(url)) {
                            Intent intent = new Intent(mActivity, WebviewActivity.class);
                            intent.putExtra("url", url);
                            intent.putExtra("title", "");
                            context.startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @JavascriptInterface
        public void downLibraryFile() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        doDownLibraryFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    /**
     * 下载文库文件
     */
    public void doDownLibraryFile() {
    }

    /**
     * 支付
     */
    public void doPayment() {
    }

    /**
     * 写评论
     */
    public void doWriteComment() {
    }

    /**
     * 是否绑定支付宝
     *
     * @return
     */
    public boolean doIsBindingPayment() {
        return true;
    }

    @Override
    public void onBackPressed() {
        //h5加载数据成功, 点击返回时触发提交试卷dialog
        if (loadState) {
//            mWebview.loadUrl("javascript:window.$submitTestPaper()");
            //h5加载数据未成功, 直接退出
        } else {
        }
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == REQUEST_SELECT_FILE) {
                if (uploadMessage == null || intent == null) {
                    //若没有选择文件就回退  执行清空  不进行这步操作无法再次选择文件
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                    return;
                }
                //由于用户可能单选或多选   单选getData  多选时getClipData()
                if (intent.getData() != null) {
                    loge(WebChromeClient.FileChooserParams.parseResult(resultCode, intent).toString());
                    uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                } else if (intent.getClipData() != null) {
                    Uri[] uris = new Uri[intent.getClipData().getItemCount()];
                    for (int i = 0; i < intent.getClipData().getItemCount(); i++) {
                        uris[i] = intent.getClipData().getItemAt(i).getUri();
                    }
                    uploadMessage.onReceiveValue(uris);
                }
                uploadMessage = null;
            }
        } else if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage || intent == null) {
                //若没有选择文件就回退  执行清空  不进行这步操作无法再次选择文件
                mUploadMessage.onReceiveValue(null);
                mUploadMessage = null;
                return;
            }
            // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
            // Use RESULT_OK only if you're implementing WebView inside an Activity
            Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        } else {
            Toast.makeText(getBaseContext(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset() {
        try {
            mWebview.loadUrl("javascript:(function(){" +
                    "var objs = document.getElementsByTagName('img'); " +
                    "for(var i=0;i<objs.length;i++)  " +
                    "{"
                    + "var img = objs[i];   " +
                    "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                    "}" +
                    "})()");
            mWebview.loadUrl("javascript:(function(){" +
                    "var objs = document.getElementsByTagName('video'); " +
                    "for(var i=0;i<objs.length;i++)  " +
                    "{"
                    + "var video = objs[i];   " +
                    "    video.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                    "}" +
                    "})()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
