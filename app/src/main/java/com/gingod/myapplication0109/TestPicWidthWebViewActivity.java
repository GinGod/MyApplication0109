package com.gingod.myapplication0109;

import android.os.Build;
import android.webkit.WebSettings;

import com.gingod.myapplication0109.base.BaseWebviewActivity;

public class TestPicWidthWebViewActivity extends BaseWebviewActivity {
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_testpicwidthwebview;
    }

    @Override
    public void initView() {
        mWebview = findViewById(R.id.wv_picwidthwebview);
        super.initView();
    }

    @Override
    public void loadUrl() {
        mWebview.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
    }
}
