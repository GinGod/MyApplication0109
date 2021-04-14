package com.gingod.myapplication0109;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gingod.myapplication0109.base.BaseSimpleActivity;
import com.gingold.basislibrary.utils.BasisDisplayUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OpenFileActivity extends BaseSimpleActivity {
    @BindView(R.id.rv_openfile)
    RecyclerView rvOpenfile;
    @BindView(R.id.pdfView)
    PDFView pdfView;

    private String localPath = "/storage/emulated/0/File/GBT14394-2008计算机软件可靠性和可维护性管理.pdf";
    private String localPath1 = "/storage/emulated/0/File/GBT16260.2-2006软件工程产品质量第2部分.pdf";
    private List<Bitmap> bitmaps = new ArrayList<>();
    private BaseQuickAdapter baseQuickAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_openfile;
    }

    @Override
    public void initData() {
        super.initData();
        initOpenFile(pdfView);

        initOpenFile();
    }

    private void initOpenFile() {
        BasisExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    bitmaps.clear();
                    ParcelFileDescriptor open = ParcelFileDescriptor.open(new File(localPath), ParcelFileDescriptor.MODE_READ_ONLY);
                    PdfRenderer pdfRenderer = new PdfRenderer(open);
                    for (int i = 0; i < pdfRenderer.getPageCount(); i++) {
                        Bitmap bitmap = renderPage(pdfRenderer.openPage(i));
                        bitmaps.add(bitmap);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initAdapter();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initAdapter() {
        if (baseQuickAdapter == null) {
            baseQuickAdapter = new BaseQuickAdapter<Bitmap, BaseViewHolder>(R.layout.item_bitmap, bitmaps) {

                @Override
                protected void convert(@NotNull BaseViewHolder baseViewHolder, Bitmap bitmap) {
                    ImageView view = baseViewHolder.getView(R.id.tv_item_bitmap);
                    Glide.with(mActivity).load(bitmap).into(view);
                }
            };
            addHeader();
            rvOpenfile.setLayoutManager(new LinearLayoutManager(mActivity));
            rvOpenfile.setAdapter(baseQuickAdapter);
        }
    }

    private void addHeader() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_openfile, null);
        PDFView pdfView = view.findViewById(R.id.pdfView);
        initOpenFile(pdfView);
        pdfView.requestDisallowInterceptTouchEvent(false);
        baseQuickAdapter.addHeaderView(view);
    }

    private Bitmap renderPage(PdfRenderer.Page page) {
        Bitmap bitmap = Bitmap.createBitmap(BasisDisplayUtils.getScreenWidth(mActivity), page.getHeight() * BasisDisplayUtils.getScreenWidth(mActivity) / page.getWidth(), Bitmap.Config.ARGB_8888);
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        page.close();
        return bitmap;
    }

    private void initOpenFile(PDFView pdfView) {
//        pdfView.fromUri(Uri)
//        or
        pdfView.fromFile(new File(localPath1))
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
                .defaultPage(0)
                // allows to draw something on the current page, usually visible in the middle of the screen
//                .onDraw(onDrawListener)
                // allows to draw something on all pages, separately for every page. Called only for visible pages
//                .onDrawAll(onDrawListener)
//                .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        loge("onPageChanged: " + page + " - " + pageCount);
                    }
                })
                .onPageScroll(new OnPageScrollListener() {
                    @Override
                    public void onPageScrolled(int page, float positionOffset) {
                        loge("onPageScrolled: " + page + " - " + positionOffset);
                    }
                })
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
