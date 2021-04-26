package com.gingod.myapplication0109;

import com.gingod.myapplication0109.base.BaseWebviewActivity2;

public class TestVideoFullActivity extends BaseWebviewActivity2 {
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

    private String pic = "/uploadnet/library/data/U3sb2OwQ/16178704260041617843788910.jpg|||/uploadnet/library/data/U3sb2OwQ/16178704262261617843784283.jpg|||/uploadnet/library/data/U3sb2OwQ/16178704262261617843784283.jpg";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_testpicwidthwebview;
    }

    @Override
    public void initView() {
        mWebview = findViewById(R.id.wv_picwidthwebview);
        super.initView();
        String[] split = pic.split("\\|{3}");
        loge(split.length + "  1String[] split");
        loge(split[0] + "  1String[] split");
        loge(split[1] + "  1String[] split");
        loge(split[2] + "  1String[] split");
    }

    @Override
    public void loadUrl() {
        loadNoCache();
//        mWebview.loadUrl("https://library.1000dpt.com:444/editor/details?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTk5MjQ5NTc3MzksInBheWxvYWQiOiJ7XCJ1c2VyVHlwZVwiOjEsXCJ1c2VySWRcIjoxNDYxLFwiYWNjb3VudFwiOlwiWDE4RTBuNzhcIn0ifQ.JwEx1Mp-ZXBkWCUhfv4wdga0tq4kOVjDp_d39oOH-4s&type=Android&articleId=1946211641");
//        mWebview.loadUrl("https://privacy.1000dpt.com/文库交易说明.html");
//        mWebview.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
//        mWebview.loadUrl("file:///android_asset/bootstrap-fileinput-master/examples/index.html");
//        mWebview.loadUrl("http://10.66.5.124:8022/");
//        mWebview.loadUrl("https://library.1000dpt.com:444/#/?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MjE5MjA0MjI4MjUsInBheWxvYWQiOiJ7XCJ1c2VyVHlwZVwiOjEsXCJ1c2VySWRcIjoxNDYxLFwiYWNjb3VudFwiOlwiWDE4RTBuNzhcIn0ifQ.b7BLq6sUaFUxaibZ4ULm3OuDFWyNiElO2jEz1n8ZugA&type=Android&articleId=1329169122");
//        mWebview.loadUrl("https://library.1000dpt.com:444/#/?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MjE5MjA0MjI4MjUsInBheWxvYWQiOiJ7XCJ1c2VyVHlwZVwiOjEsXCJ1c2VySWRcIjoxNDYxLFwiYWNjb3VudFwiOlwiWDE4RTBuNzhcIn0ifQ.b7BLq6sUaFUxaibZ4ULm3OuDFWyNiElO2jEz1n8ZugA&type=Android&articleId=2013425129");
        mWebview.loadUrl("https://library.1000dpt.com:444/#/?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MjE5MjA0MjI4MjUsInBheWxvYWQiOiJ7XCJ1c2VyVHlwZVwiOjEsXCJ1c2VySWRcIjoxNDYxLFwiYWNjb3VudFwiOlwiWDE4RTBuNzhcIn0ifQ.b7BLq6sUaFUxaibZ4ULm3OuDFWyNiElO2jEz1n8ZugA&type=Android&articleId=1904111263");
    }
}
