package com.gingod.myapplication0109;

import android.content.Context;
import android.graphics.Bitmap;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gingod.myapplication0109.base.BaseSimpleActivity;
import com.gingold.basislibrary.utils.BasisDisplayUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OpenFileActivity2 extends BaseSimpleActivity {

    @BindView(R.id.pdfView)
    PDFView pdfView;
    //    private String localPath = "/storage/emulated/0/File/GBT16260.2-2006软件工程产品质量第2部分.pdf";
//    private String localPath = "/storage/emulated/0/File/GBT16260.3-2006软件工程产品质量第3部分内部度量.pdf";
    private String localPath = "/storage/emulated/0/File/Git常用命令.pdf";
//    private String localPath = "/storage/emulated/0/File/161698274578012页资料.doc";
    private List<Bitmap> bitmaps = new ArrayList<>();
    private BaseQuickAdapter baseQuickAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_openfile2;
    }

    @Override
    public void initData() {
        super.initData();
        initOpenPdfFile(mActivity,pdfView, localPath, 0, null);
    }

    private void initOpenPdfFile(Context mActivity, PDFView pdfView, String localPath, int defaultPage, OnPageScrollListener onPageScrollListener) {
//        pdfView.fromUri(Uri)
//        or
        pdfView.fromFile(new File(localPath))
//        or
//        pdfView.fromBytes(byte[])
//        or
//        pdfView.fromStream(InputStream) // stream is written to bytearray - native code cannot use Java Streams
//        or
//        pdfView.fromSource(DocumentSource)
//        or
//        pdfView.fromAsset(String)
//                .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(defaultPage)
                // allows to draw something on the current page, usually visible in the middle of the screen
//                .onDraw(onDrawListener)
                // allows to draw something on all pages, separately for every page. Called only for visible pages
//                .onDrawAll(onDrawListener)
//                .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
//                .onPageChange(null)
                .onPageScroll(onPageScrollListener)
//                .onError(onErrorListener)
//                .onPageError(onPageErrorListener)
//                .onRender(onRenderListener) // called after document is rendered for the first time
                // called on single tap, return true if handled, false to toggle scroll handle visibility
//                .onTap(onTapListener)
//                .onLongPress(onLongPressListener)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(BasisDisplayUtils.dp2px(mActivity, 2))
//                .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
//                .linkHandler(DefaultLinkHandler)
//                .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
//                .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
//                .pageSnap(false) // snap pages to screen boundaries
//                .pageFling(false) // make a fling change only a single page like ViewPager
//                .nightMode(false) // toggle night mode
                .load();
    }


}
