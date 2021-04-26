package com.gingod.myapplication0109.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gingod.myapplication0109.BasisExecutors;
import com.gingod.myapplication0109.R;
import com.gingod.myapplication0109.base.BaseSimpleActivity;
import com.gingod.myapplication0109.bean.FileInfo;
import com.gingod.myapplication0109.utils.CommonUtils;
import com.gingod.myapplication0109.utils.FileUtil;
import com.lihang.ShadowLayout;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选择本地文件
 *
 * @author
 */
public class SelectMultiFileActivity extends BaseSimpleActivity {

    @BindView(R.id.tvUpload)
    TextView tvUpload;
    @BindView(R.id.rv_select_file)
    RecyclerView rv_select_file;
    @BindView(R.id.tv_select_file_phone)
    TextView tvSelectFilePhone;
    @BindView(R.id.rv_select_file_document)
    RecyclerView rvSelectFileDocument;
    @BindView(R.id.sl_select_file)
    ShadowLayout slSelectFile;

    /**
     * 文件筛选状态: 0 所有文件,  1 文库发布资料
     */
    public final static int SELECT_ALL = 0, SELECT_LIBRARY = 1;
    public final static String DIRECTORY = "DIRECTORY";
    private BaseQuickAdapter dirQuickAdapter, baseQuickAdapter;
    private ArrayList<String> dirList = new ArrayList<>();
    private ArrayList<FileInfo> allData = new ArrayList<>();
    private ArrayList<FileInfo> selectedList = new ArrayList<>();
    private Bundle mBundle;
    /**
     * 最多选择文件个数, 默认只能选择一个
     */
    private int maxSelectNum = 5;

    private int state = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_multi_select_file;
    }

    @Override
    public void initView() {
        initEmptyView("文件夹中没有文件!");
        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            maxSelectNum = mBundle.getInt("maxSelectNum", maxSelectNum);
            state = mBundle.getInt("state");
        }
        initTitle("文件");
    }

    @Override
    public void initData() {
        dirList.add("File");
        resetData("/File");
    }

    /**
     * 获取文件夹中文件
     *
     * @param dir
     */
    private void resetData(String dir) {
        selectedList.clear();
        allData.clear();
        initDirAdapter();
        getFolderData(dir);
        sortList();
        initAdapter();
        updateSelectList();
    }

    /**
     * 按名字排序
     */
    private void sortList() {
        if (allData != null && allData.size() > 0) {
            // 排序实现
            Collections.sort(allData, (e1, e2) -> {
                if (DIRECTORY.equals(e1.getSuffix()) && DIRECTORY.equals(e2.getSuffix())) {
                    return Collator.getInstance(Locale.ENGLISH).compare(e1.getFileName().trim(), e2.getFileName().trim());
                } else if (DIRECTORY.equals(e1.getSuffix())) {
                    return -1;
                } else if (DIRECTORY.equals(e2.getSuffix())) {
                    return 1;
                } else {
                    return Collator.getInstance(Locale.ENGLISH).compare(e1.getFileName().trim(), e2.getFileName().trim());
                }
            });
        }
    }

    /**
     * 适配器
     */
    private void initDirAdapter() {
        try {
            if (dirQuickAdapter != null) {
                dirQuickAdapter.notifyDataSetChanged();
                rvSelectFileDocument.scrollToPosition(dirList.size() - 1);
            } else {
                dirQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_multi_select_file, dirList) {

                    @Override
                    protected void convert(@NotNull BaseViewHolder baseViewHolder, String dir) {
                        try {
                            baseViewHolder
                                    .setText(R.id.tv_item_multi_select_file_directoryname, showStr(dir))
                                    .setTextColorRes(R.id.tv_item_multi_select_file_directoryname,
                                            (baseViewHolder.getAdapterPosition() == dirList.size() - 1) ? R.color.wallet : R.color.light_gre)
                                    .setGone(R.id.iv_item_multi_select_file, baseViewHolder.getAdapterPosition() == dirList.size() - 1)
                            ;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                dirQuickAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                        try {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 0; i < position + 1; i++) {
                                stringBuilder.append("/").append(dirList.get(i));
                            }
                            for (int i = 0; dirList.size() > position + 1; i++) {
                                dirList.remove(position + 1);
                            }
                            resetData(stringBuilder.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                rvSelectFileDocument.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                rvSelectFileDocument.setAdapter(dirQuickAdapter);
                rvSelectFileDocument.scrollToPosition(dirList.size() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 适配器
     */
    private void initAdapter() {
        try {
            if (baseQuickAdapter != null) {
                baseQuickAdapter.notifyDataSetChanged();
            } else {
                rv_select_file.setLayoutManager(new LinearLayoutManager(this));
                baseQuickAdapter = new BaseQuickAdapter<FileInfo, BaseViewHolder>(R.layout.item_select_file, allData) {

                    @Override
                    protected void convert(@NotNull BaseViewHolder baseViewHolder, FileInfo fileInfo) {
                        try {
                            baseViewHolder
                                    .setText(R.id.tv_item_select_file_name, showStr(fileInfo.getFileName()))
                                    .setText(R.id.tv_item_select_file_size,
                                            DIRECTORY.equals(fileInfo.getSuffix()) ? (fileInfo.getFileSize() + "项") + " | " + fileInfo.getTime() : showStr(FileUtil.FormetFileSize(fileInfo.getFileSize())) + " | " + fileInfo.getTime())
                                    .setImageResource(R.id.iv_item_select_file_cover,
                                            DIRECTORY.equals(fileInfo.getSuffix()) ? R.mipmap.file_folder_new : FileUtil.getFileTypeImageId2(mActivity, fileInfo.getFileName()))
                                    .setGone(R.id.iv_item_select_file_check,
                                            DIRECTORY.equals(fileInfo.getSuffix()))
                                    .setImageResource(R.id.iv_item_select_file_check, fileInfo.isChecked ? R.mipmap.member_selected : R.mipmap.member_normal)
                            ;

                            if (FileUtil.getFileIsImage(fileInfo.getFileName()) || FileUtil.getFileIsVideo(fileInfo.getFileName())) {
                                ImageView iv_item_slect_file = baseViewHolder.getView(R.id.iv_item_select_file_cover);
                                Glide.with(mActivity).load(fileInfo.getFilePath()).into(iv_item_slect_file);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                baseQuickAdapter.setEmptyView(emptyView);

                baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                        try {
                            //文件夹
                            if (DIRECTORY.equals(allData.get(position).getSuffix())) {
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i = 0; i < dirList.size(); i++) {
                                    stringBuilder.append("/").append(dirList.get(i));
                                }
                                stringBuilder.append("/").append(allData.get(position).getFileName());
                                dirList.add(allData.get(position).getFileName());
                                resetData(stringBuilder.toString());
                                //文件
                            } else {
                                boolean isChecked = allData.get(position).isChecked;
                                if (!isChecked) {
                                    if (selectedList.size() == maxSelectNum) {
                                        toast("最多可选取" + maxSelectNum + "个文件!");
                                        return;
                                    }
                                }
                                //改变选择状态
                                allData.get(position).isChecked = !isChecked;
                                initAdapter();
                                updateSelectList();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                rv_select_file.setAdapter(baseQuickAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新选择的文件和上传按钮状态
     */
    private void updateSelectList() {
        try {
            selectedList.clear();
            for (int i = 0; i < allData.size(); i++) {
                FileInfo fileInfo = allData.get(i);
                if (fileInfo.isChecked) {
                    selectedList.add(fileInfo);
                }
            }
            if (selectedList.size() > 0 && selectedList.size() <= maxSelectNum) {
                tvUpload.setEnabled(true);
                tvUpload.setBackgroundResource(R.drawable.bg_send_selector_color);
                setVisible(slSelectFile);
            } else {
                tvUpload.setEnabled(false);
                tvUpload.setBackgroundResource(R.drawable.bg_tv_cicle_send_presss_yes);
                setGone(slSelectFile);
            }
            BasisExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < allData.size(); i++) {
                        FileInfo fileInfo = allData.get(i);
                        if (DIRECTORY.equals(fileInfo.getSuffix())) {
                            fileInfo.setFileSize(new File(fileInfo.getFilePath()).listFiles().length);
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initAdapter();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历文件夹中资源
     */
    public void getFolderData(String dir) {
        try {
            File allDir = new File(Environment.getExternalStorageDirectory().toString() + dir);
            //新建File文件夹
            if (!allDir.exists() || !allDir.isDirectory()) {
                allDir.mkdir();
            }
            recursionFile(allDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历手机所有文件 并将路径名存入集合中 参数需要 路径和集合 - 递归
     */
    public void recursionFile(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                FileInfo document = null;
                if (file.isDirectory()) {
                    if (areNotEmpty(file.getName())) {
                        document = new FileInfo();
                        document.setSuffix(DIRECTORY);
                        document.setFileName(file.getName());
                        document.setFilePath(file.getAbsolutePath());
                        document.setTime(FileUtil.getFileLastModifiedTime(file));
//                        document.setFileSize(file.listFiles().length);
                    }
                } else {
                    document = FileUtil.getFileInfoFromFile(file);
                    switch (state) {
                        //文库发布资料
                        case SELECT_LIBRARY:
                            document = CommonUtils.getFileInfoState1(file);
                            break;
                    }
                }
                if (document != null) {
                    allData.add(document);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @OnClick({R.id.tv_select_file_phone, R.id.tvUpload})
    public void onClick(View v) {
        switch (v.getId()) {
            //手机储存
            case R.id.tv_select_file_phone:
                dirList.clear();
                resetData("");
                break;
            //返回文件
            case R.id.tvUpload:
                if (selectedList.size() > 0) {
                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra("fileList", selectedList);
                    setResult(252, intent);
                    finish();
                } else {
                    toast("请至少选择一个文件!");
                }
                break;
            default:
                break;
        }
    }

}
