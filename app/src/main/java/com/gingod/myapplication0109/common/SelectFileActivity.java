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
import com.gingod.myapplication0109.R;
import com.gingod.myapplication0109.base.BaseSimpleActivity;
import com.gingod.myapplication0109.bean.FileInfo;
import com.gingod.myapplication0109.utils.CommonUtils;
import com.gingod.myapplication0109.utils.FileUtil;

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
public class SelectFileActivity extends BaseSimpleActivity {

    @BindView(R.id.tvUpload)
    TextView tvUpload;
    @BindView(R.id.rv_select_file)
    RecyclerView rv_select_file;

    private BaseQuickAdapter baseQuickAdapter;
    private ArrayList<FileInfo> allData = new ArrayList<>();
    private ArrayList<FileInfo> selectedList = new ArrayList<>();
    private Bundle mBundle;
    /**
     * 最多选择文件个数, 默认只能选择一个
     */
    private int maxSelectNum = 1;

    /**
     * 文件筛选状态:  1 文库发布资料
     */
    private String state = "0";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_file;
    }

    @Override
    public void initView() {
        initEmptyView("文件夹中没有可用文件!");
        tvUpload.setEnabled(false);
        tvUpload.setBackgroundResource(R.drawable.bg_tv_cicle_send_presss_yes);
        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            maxSelectNum = mBundle.getInt("maxSelectNum", 1);
        }
        initTitle("文件");
    }

    @Override
    public void initData() {
        if (getIntent().getExtras() != null) {
            state = getIntent().getExtras().getString("state");
        }
        getFolderData();
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
                return Collator.getInstance(Locale.ENGLISH).compare(e1.getFileName().trim(), e2.getFileName().trim());
            });
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
                                    .setText(R.id.tv_item_select_file_size, showStr(FileUtil.FormetFileSize(fileInfo.getFileSize())))
                                    .setImageResource(R.id.iv_item_select_file_cover, FileUtil.getFileTypeImageId2(mActivity, fileInfo.getFileName()))
                                    .setImageResource(R.id.iv_item_select_file_check, fileInfo.isChecked ? R.mipmap.member_selected : R.mipmap.member_normal)
                            ;

                            if (FileUtil.getFileIsImage(fileInfo.getFileName())) {
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
     * 更新选择的文件
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
            } else {
                tvUpload.setEnabled(false);
                tvUpload.setBackgroundResource(R.drawable.bg_tv_cicle_send_presss_yes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历文件夹中资源
     */
    public void getFolderData() {
        try {
            File allDir = new File(Environment.getExternalStorageDirectory().toString() + "/File/");
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
                if (file.isDirectory()) {
                    recursionFile(file);
                } else {
                    FileInfo document = CommonUtils.getFileInfo(file);
                    //文库发布资料
                    if ("1".equals(state)) {
                        document = CommonUtils.getFileInfoState1(file);
                    }
                    if (document != null) {
                        allData.add(document);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @OnClick({R.id.tvUpload})
    public void onClick(View v) {
        switch (v.getId()) {
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
