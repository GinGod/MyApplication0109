package com.gingod.myapplication0109;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gingod.myapplication0109.base.BaseSimpleActivity;
import com.gingod.myapplication0109.utils.CommonUtils;
import com.gingold.basislibrary.utils.BasisDisplayUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SignActivity extends BaseSimpleActivity {

    @BindView(R.id.pdfView)
    PDFView pdfView;
    @BindView(R.id.iv_openfile2)
    ImageView ivOpenfile2;
    @BindView(R.id.iv_openfile2_seal)
    ImageView ivOpenfile2Seal;
    @BindView(R.id.ll_openfile2)
    RelativeLayout llOpenfile2;
    @BindView(R.id.ll_openfile2_sign)
    RelativeLayout llOpenfile2Sign;
    @BindView(R.id.tv_openfile2_sign)
    TextView tvOpenfile2Sign;
    private String localPath = Environment.getExternalStorageDirectory() + "/File/1610672873478效率第一！高效人士的日程规划页面设计.pdf";
    //    private String localPath = "/storage/emulated/0/File/GBT16260.3-2006软件工程产品质量第3部分内部度量.pdf";
//    private String localPath = "/storage/emulated/0/File/Git常用命令.pdf";
    //    private String localPath = "/storage/emulated/0/File/161698274578012页资料.doc";
    private List<Bitmap> bitmaps = new ArrayList<>();
    private BaseQuickAdapter baseQuickAdapter;

    private int initialWidth, initialHeight;
    private Bitmap pdfPageBitmap;
    private int currentX = 252, currentY = 252;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    public void initView() {
        super.initView();
        initTitle("测试签章", "签章");
    }

    @Override
    public void initData() {
        super.initData();
        String path = CommonUtils.putAssetsToSDCard(mActivity, "GBT16260.2-2006软件工程产品质量第2部分：外部度量(GBT).pdf");
        if (areNotEmpty(path)) {
            localPath = path;
        }
        ivOpenfile2Seal.post(new Runnable() {
            @Override
            public void run() {
                initialWidth = ivOpenfile2Seal.getWidth();
                initialHeight = ivOpenfile2Seal.getHeight();
                loge(initialWidth + " _ " + initialHeight);
                setGone(llOpenfile2Sign);
            }
        });
        initOpenPdfFile(mActivity, pdfView, localPath, 0, new OnPageScrollListener() {
            @Override
            public void onPageScrolled(int page, float positionOffset) {
                loge("onPageScrolled: " + page + " " + positionOffset);
            }
        });
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
//        pdfView.fromAsset("GBT16260.2-2006软件工程产品质量第2部分：外部度量(GBT).pdf")
//                .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(defaultPage)
                // allows to draw something on the current page, usually visible in the middle of the screen
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
//                        loge("onDraw: " + canvas.getWidth() + " " + canvas.getHeight());
                    }
                })
                // allows to draw something on all pages, separately for every page. Called only for visible pages
                .onDrawAll(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
//                        loge("onDrawAll: " + pageWidth + " " + pageHeight + " " + displayedPage + " ");

                    }
                })
//                .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        setTVText(page + 1 + "/" + pageCount, tv_base_title);
                    }
                })
                .onPageScroll(onPageScrollListener)
//                .onError(onErrorListener)
//                .onPageError(onPageErrorListener)
//                .onRender(onRenderListener) // called after document is rendered for the first time
                // called on single tap, return true if handled, false to toggle scroll handle visibility
                .onTap(new OnTapListener() {
                    @Override
                    public boolean onTap(MotionEvent e) {
                        loge("OnTapListener: " + e.getX() + " " + e.getY() + " " + e.getRawX() + " " + e.getRawY());
                        return false;
                    }
                })
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


    @Override
    public void doTitleRightListener() {
        super.doTitleRightListener();
        if (llOpenfile2Sign.getVisibility() == View.GONE) {
            setTVText("取消", tv_base_right);
            sign();
        } else {
            setTVText("签章", tv_base_right);
            setGone(llOpenfile2Sign);
        }
    }

    private void sign() {
        BasisExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                pdfPageBitmap = CommonUtils.getPdfPageBitmap(mActivity, new File(localPath), pdfView.getCurrentPage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (pdfPageBitmap != null) {
                            loge(pdfPageBitmap.getWidth() + " " + pdfPageBitmap.getHeight() + " " + BasisDisplayUtils.getScreenWidth(mActivity));
                            setVisible(llOpenfile2Sign);
                            setGone(R.id.ll_sign_scale);
                            Glide.with(mActivity).load(pdfPageBitmap).into(ivOpenfile2);
                            setGone(ivOpenfile2Seal);
                            setTVText("", tvOpenfile2Sign);
                            llOpenfile2.setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    switch (event.getAction()) {
                                        case MotionEvent.ACTION_DOWN:
                                        case MotionEvent.ACTION_MOVE:
                                        case MotionEvent.ACTION_UP:
                                            loge(llOpenfile2.getX() + " " + llOpenfile2.getPivotX() + " " + llOpenfile2.getRotationX() + " " + llOpenfile2.getScaleX()
                                                    + " " + llOpenfile2.getScrollX() + " " + llOpenfile2.getTranslationX());
                                            loge(event.getX() + " " + event.getRawX() + " " + event.getY() + " " + event.getRawY());
//                                            toast(pdfPageBitmap.getWidth() + " " + pdfPageBitmap.getHeight() + " " + event.getX() / pdfPageBitmap.getWidth() + " " + event.getY() / pdfPageBitmap.getHeight());
                                            currentX = (int) event.getX();
                                            currentY = (int) event.getY();
                                            setLocation(currentY, currentX);
                                            break;
                                    }
                                    return true;
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void setLocation(int topMargin, int leftMargin) {
        setVisible(ivOpenfile2Seal);
        setVisible(R.id.ll_sign_scale);
        setTVText(pdfPageBitmap.getWidth() + "_" + pdfPageBitmap.getHeight()
                        + "_" + initialWidth * getScale() / 100 + "_" + initialHeight * getScale() / 100
                        + "_" + CommonUtils.getFormat6(currentX * 1.0 / pdfPageBitmap.getWidth()) + "_" + CommonUtils.getFormat6(currentY * 1.0 / pdfPageBitmap.getHeight())
                , tvOpenfile2Sign)
        ;

        topMargin = topMargin - initialHeight * getScale() / (100 * 2);
        leftMargin = leftMargin - initialWidth * getScale() / (100 * 2);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivOpenfile2Seal.getLayoutParams();
        layoutParams.topMargin = topMargin;
        layoutParams.leftMargin = leftMargin;
        ivOpenfile2Seal.setLayoutParams(layoutParams);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
                loge(llOpenfile2.getX() + " " + llOpenfile2.getPivotX() + " " + llOpenfile2.getRotationX() + " " + llOpenfile2.getScaleX()
                        + " " + llOpenfile2.getScrollX() + " " + llOpenfile2.getTranslationX());
                loge(event.getX() + " " + event.getRawX());
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    @OnClick({R.id.iv_sign_scale_add, R.id.iv_sign_scale_delete, R.id.tv_sign_scale_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_sign_scale_add:
                add();
                break;
            case R.id.iv_sign_scale_delete:
                delete();
                break;
            case R.id.tv_sign_scale_confirm:
                if (getScale() > 0) {
                    changeScale();
                    setLocation(currentY, currentX);
                    hideInputMethod();
                } else {
                    toast("缩放比例过小!");
                }
                break;
        }
    }

    private void delete() {
        int scale = getScale();
        if (scale < 11) {
            toast("缩放比例过小!");
        } else {
            setTVText(scale - 10 + "", R.id.et_sign_scale);
            changeScale();
            setLocation(currentY, currentX);
        }
    }

    private void changeScale() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivOpenfile2Seal.getLayoutParams();
        layoutParams.width = initialWidth * getScale() / 100;
        layoutParams.height = initialHeight * getScale() / 100;
        ivOpenfile2Seal.setLayoutParams(layoutParams);
    }

    private void add() {
        int scale = getScale();
        setTVText(scale + 10 + "", R.id.et_sign_scale);
        changeScale();
        setLocation(currentY, currentX);
    }

    private int getScale() {
        String tvText = getTvText(R.id.et_sign_scale);
        return parseInt(tvText);
    }

}
