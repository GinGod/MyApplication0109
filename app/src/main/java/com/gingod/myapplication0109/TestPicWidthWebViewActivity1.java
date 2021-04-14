package com.gingod.myapplication0109;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gingod.myapplication0109.base.BaseSimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestPicWidthWebViewActivity1 extends BaseSimpleActivity {
    String content = "<div style=\"text-align:center;\">\n" +
            "\t<span style=\"font-size:14px;\">项目位于云南省曲靖市师宗县，是城市公共活动的核心区域，也是一个以文笔塔为核心的生态休闲公园，规划设计面积为130公顷。</span> \n" +
            "</div>\n" +
            "<div style=\"text-align:left;\">\n" +
            "\t<span style=\"font-size:14px;\"><br />\n" +
            "</span> \n" +
            "</div>\n" +
            "<span style=\"font-size:14px;\"> \n" +
            "<div style=\"text-align:center;\">\n" +
            "\t<img src=\"https://www.1000dpt.com/uploadnet/library/article/null/1541987591024599e99e220233.jpg\" alt=\"\" /><img src=\"https://www.1000dpt.com/uploadnet/library/article/null/1541987634712599e99e226839.jpg\" alt=\"\" /> \n" +
            "</div>\n" +
            "</span> \n" +
            "<div style=\"text-align:center;\">\n" +
            "\t<img src=\"https://www.1000dpt.com/uploadnet/library/article/null/1541987604414599e99e22c897.jpg\" alt=\"\" /> \n" +
            "</div>";
    @BindView(R.id.wv_picwidthwebview)
    WebView wvPicwidthwebview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_testpicwidthwebview;
    }

    @Override
    public void initData() {
        super.initData();
        content = content.replaceAll("img", "img width=\"100%\" height=\"auto\" ");
        initWebView(content);
    }

    private void initWebView(String content) {
//        wvPicwidthwebview.getSettings().setJavaScriptEnabled(true);//支持javascript
        //设置可以访问文件
//        wvPicwidthwebview.getSettings().setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
//            wvPicwidthwebview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
//        wvPicwidthwebview.getSettings().setBlockNetworkImage(false);
        //设置支持缩放
        //                wvPicwidthwebview.getSettings().setBuiltInZoomControls(true);
//        wvPicwidthwebview.setWebViewClient(new ArticleWebViewClient());
//        wvPicwidthwebview.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        wvPicwidthwebview.loadUrl("file:///android_asset/text.html");
    }

    private class ArticleWebViewClient extends WebViewClient {
        //
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //重置webview中img标签的图片大小
//            imgReset();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            handler.proceed(); // 兼容https
        }

    }

    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset() {
        try {
            wvPicwidthwebview.loadUrl("javascript:(function(){" +
                    "var objs = document.getElementsByTagName('img'); " +
                    "for(var i=0;i<objs.length;i++)  " +
                    "{"
                    + "var img = objs[i];   " +
                    "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                    "}" +
                    "})()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
