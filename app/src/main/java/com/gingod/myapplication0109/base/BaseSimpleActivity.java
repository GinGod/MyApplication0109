package com.gingod.myapplication0109.base;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.gingod.myapplication0109.MyApplication;
import com.gingod.myapplication0109.R;
import com.gingod.myapplication0109.config.CommonConfig;
import com.gingod.myapplication0109.utils.ScreenInfoUtils;
import com.gingold.basislibrary.Base.BasisMultiMethodActivity;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * BaseSimpleActivity
 *
 * @author
 */

public abstract class BaseSimpleActivity extends BasisMultiMethodActivity {
    /**
     * 界面是否可见; 显示网络异常
     */
    public boolean isBaseStart = false, isBaseResume = false, isShowNetError = true;
    /**
     * 空布局
     */
    public View emptyView;
    public TextView tvEmptyView, tvTitleBack;
    public ImageView iv_empty_view;


    public Gson mGson = new Gson();
    public String token;
    public String userAccount;
    public String nickName;
    public String toppic;
    public long userId;
    public int userType;

    public boolean bindState;

    /**
     * 请求网络按钮点击事件, 防止多点
     */
    public boolean clickState = true;

    @Override
    public void beforeSetContentView() {
        super.beforeSetContentView();
        ScreenInfoUtils.fullScreen(this);
    }

    @Override
    public void setupViewLayout() {
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    /**
     * 使用activity_common_header3布局时才可调用
     */
    public void initTitle(String title) {
        initTitle(title, "");
    }

    /**
     * 使用activity_common_header3布局时才可调用
     */
    public void initTitle(String title, String right) {
        initTitle(title, right, R.id.rlback, R.id.tvTitle, R.id.tvFewCents, false);
    }

    public void initTitle(String title, String right, int iv_base_back, int tv_base_title, int tv_base_right, boolean addShow) {
        super.initTitle(title, right, iv_base_back, tv_base_title, tv_base_right);
        setVisible(tv_base_right);
        if (!addShow) {
            setGone(R.id.rlAddFile);
        }
        tvTitleBack = findViewById(R.id.tvTitleBack);
        tvTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        initValues();
    }

    public void initValues() {

    }

    /**
     * 使用文字返回
     */
    public void setTextBack() {
        try {
            setGone(iv_base_back);
            setVisible(tvTitleBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化空布局
     */
    public void initEmptyView() {
        initEmptyView("");
    }

    /**
     * 初始化空布局
     */
    public void initEmptyView(String str) {
        //空数据布局
        emptyView = LayoutInflater.from(mActivity).inflate(R.layout.common_empty_view, null);
        iv_empty_view = emptyView.findViewById(R.id.iv_empty_view);
        tvEmptyView = emptyView.findViewById(R.id.tv_empty_view);
        tvEmptyView.setText(TextUtils.isEmpty(str) ? "加载中..." : str);
    }

    /**
     * 页面布局
     *
     * @return
     */
    protected abstract int getLayoutId();

    @Override
    protected void onStart() {
        super.onStart();
        isBaseStart = true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        isBaseStart = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isBaseResume = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isBaseResume = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isBaseStart = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 网络请求
     */
    public void getRequest(final int flag) {
        getRequest(flag, Api.URL_BASE);
    }

    /**
     * 网络请求
     */
    public void getRequest8105(final int flag) {
        getRequest(flag, Api.URL_BASE_8105);
    }

    /**
     * 网络请求
     */
    public void getRequestAliPay(final int flag) {
        getRequest(flag, Api.AliPay);
    }

    /**
     * 网络请求
     */
    public void getRequest(final int flag, final String url) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            MyService myService = retrofit.create(MyService.class);
            Call call = null;
            try {
                call = getApiServiceMethod(myService, flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (call != null) {
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        try {
                            putNetSuccessMethod(call, response, flag);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        try {
                            putNetFailureMethod(call, t, flag);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                toast("网络异常!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            toast("请输入正确的URL");
        }
    }

    /**
     * 获取具体的类传回想要调用的接口 在上面赋值给call
     */
    public Call<Object> getApiServiceMethod(MyService apiService, int flag) {
        return null;
    }

    /**
     * 子类提供网络请求成功后具体的实现方法
     */
    public void putNetSuccessMethod(Call<Object> call, Response<Object> response, int flag) {
        try {
            clickState = true;
            String json = JSON.toJSONString(response.body());
            logi(TAG + flag + " - " + json);
            if (MessageUtils.isNotEmpty(json)) {
                putNetSuccessMethod(json, flag);
            } else {
                toast("未知异常!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 网络请求结果
     */
    public void putNetSuccessMethod(String json, int flag) {
        try {
            //微服务结果处理
            CommonBean commonBean = JSON.parseObject(json, CommonBean.class);
            if (commonBean.code == CommonConfig.REQUEST_SUCCESS || commonBean.code == CommonConfig.REQUEST_EMPTY_DATA) {
                putNetSuccessMethod(json, flag, commonBean);
                doNetSuccess(flag);
            } else if (commonBean.code != CommonConfig.zero) {
                if (flag != 239) {
                    toast(commonBean.message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 网络请求结果(微服务)
     */
    public void doNetSuccess(int flag) {

    }

    /**
     * 网络请求结果(微服务)
     */
    public void putNetSuccessMethod(String json, int flag, CommonBean commonBean) {

    }

    /**
     * 子类提供网络请求失败后具体的实现方法
     */
    public void putNetFailureMethod(Call<Object> call, Throwable t, int flag) {
        try {
            clickState = true;
            if (isShowNetError) {
                toast("网络异常, 请检查网络连接!");
            }
            loge(TAG + flag + " - " + t.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Activity的当前跳转
     */
    protected void openActivity(Class<?> cls) {
        openActivity(cls, null, false);
    }

    /**
     * Activity的当前跳转
     */
    protected void openActivity(Class<?> cls, boolean isActivityFinish) {
        openActivity(cls, null, isActivityFinish);
    }

    /**
     * Activity之间的跳转
     */
    protected void openActivity(Class<?> cls, Bundle bundle, boolean isActivityFinish) {
        try {
            Intent intent = new Intent(this, cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
            if (isActivityFinish) {
                this.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Activity之间获取结果的跳转
     */
    protected void openActivityForResult(Class<?> cls, int requestCode) {
        openActivityForResult(cls, requestCode, null);
    }

    /**
     * Activity之间获取结果的跳转
     */
    protected void openActivityForResult(Class<?> cls, int requestCode, Bundle bundle) {
        try {
            Intent intent = new Intent(this, cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭软键盘
     */
    protected void closeKeyBoard() {
        try {
            View view = getWindow().peekDecorView();
            if (view != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收消息广播
     */
    private ChatMessageReceiver chatMessageReceiver;

    /**
     * 动态注册广播
     */
    protected void doRegisterReceiver() {
        try {
            chatMessageReceiver = new ChatMessageReceiver();
            IntentFilter filter = new IntentFilter("com.qn.millennium.content");
            registerReceiver(chatMessageReceiver, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消注册广播
     */
    protected void unregisterReceiver() {
        try {
            if (chatMessageReceiver != null) {
                unregisterReceiver(chatMessageReceiver);
                chatMessageReceiver = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收消息广播
     */
    private class ChatMessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                String message = intent.getStringExtra("message");
                handleMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理接收到的消息
     */
    protected void handleMessage(String message) {
    }

    @Override
    public void loge(String message) {
        Logger.e(TAG + message);
    }

    public void loge(Object obj) {
        Logger.e(TAG + JSON.toJSONString(obj == null ? "" : obj));
    }

    /**
     * 日志
     */
    public void logi(String message) {
        Logger.i(TAG + message);
    }

    public void toast(Object message) {
        Toast.makeText(mActivity, MessageUtils.formatToStr(message), Toast.LENGTH_SHORT).show();
    }

    /**
     * 判断网络是否连接
     */
    public void onClickNetAvailable(View view) {
        if (NetworkHelper.isNetworkAvailable(mActivity)) {
            onClick(view.getId());
        } else {
            toast("网络连接异常");
        }
    }

    /**
     * 显示键盘
     */
    public void showInput(final EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 设置不可见状态
     */
    public void setGoneState(boolean state, int... ids) {
        for (int i = 0; i < ids.length; i++) {
            setGoneState(state, getViewNoClickable(ids[i]));
        }
    }

    /**
     * 设置不可见状态
     */
    public void setGoneState(boolean state, View... views) {
        for (int i = 0; i < views.length; i++) {
            if (state) {
                setGone(views);
            } else {
                setVisible(views);
            }
        }
    }

    public Dialog progressDialog;
    public TextView tvProgressTip;

    public void startDialogProgress(String tip) {
        try {
            if (progressDialog == null) {
                progressDialog = new Dialog(this, R.style.Son_dialog);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_loading, null);
                tvProgressTip = (TextView) view.findViewById(R.id.tvTip);
                tvProgressTip.setText(tip);
                progressDialog.setContentView(view);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopDialogProgress() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog.cancel();
                progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDialogMessage(String message) {
        try {
            if (tvProgressTip != null) {
                tvProgressTip.setText(message + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
