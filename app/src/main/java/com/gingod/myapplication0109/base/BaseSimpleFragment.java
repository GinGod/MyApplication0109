package com.gingod.myapplication0109.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.gingod.myapplication0109.R;
import com.gingod.myapplication0109.config.CommonConfig;
import com.gingold.basislibrary.Base.BasisMuliMethodFragment;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * BaseSimpleFragment
 *
 * @author
 */

public abstract class BaseSimpleFragment extends BasisMuliMethodFragment {
    /**
     * 给Fragment传递数据key和数据
     */
    public static final String FG_BUNDLE_DATA = "FG_BUNDLE_DATA";
    public String receiveData = "";
    /**
     * 空布局
     */
    public View emptyView;
    public TextView tvEmptyView;
    /**
     * 界面是否可见; 显示网络异常
     */
    public boolean isBaseStart = false, isBaseResume = false, hidden = false, isShowNetError = true;
    public Gson mGson = new Gson();
    public String token;
    public String userAccount;
    public String nickName;
    public String toppic;
    public long userId;
    public int userType;
    public Bundle args;

    @Override
    public void beforeCreateView() {
        super.beforeCreateView();
        args = getArguments();
        if (args != null) {
            //默认获取String数据
            receiveData = args.getString(FG_BUNDLE_DATA);
        }
    }

    @Override
    public View setupViewLayout(LayoutInflater inflater) {
        View view = inflater.inflate(getLayoutId(), null, false);
        try {
            mActivity = getActivity();
            ButterKnife.bind(this, view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void initView(View mBaseView) {

    }

    /**
     * 页面布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化空布局
     */
    public void initEmptyView() {
        //空数据布局
        emptyView = LayoutInflater.from(mActivity).inflate(R.layout.common_empty_view, null);
        tvEmptyView = emptyView.findViewById(R.id.tv_empty_view);
        tvEmptyView.setText("加载中...");
    }

    @Override
    public void onStart() {
        super.onStart();
        isBaseStart = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        isBaseResume = true;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.hidden = hidden;
        //隐藏
        if (hidden) {

            //显示
        } else {
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isBaseResume = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        isBaseStart = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
            if (commonBean.code == CommonConfig .REQUEST_SUCCESS) {
                putNetSuccessMethod(json, flag, commonBean);
                doNetSuccess(flag);
            } else if (commonBean.code != CommonConfig.zero) {
                toast(commonBean.message);
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
    public void openActivity(Class<?> cls) {
        openActivity(cls, null, false);
    }

    /**
     * Activity的当前跳转
     */
    public void openActivity(Class<?> cls, boolean isActivityFinish) {
        openActivity(cls, null, isActivityFinish);
    }

    /**
     * Activity之间的跳转
     */
    public void openActivity(Class<?> cls, Bundle bundle, boolean isActivityFinish) {
        try {
            Intent intent = new Intent(mActivity, cls);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
            if (isActivityFinish) {
                mActivity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Activity之间获取结果的跳转
     */
    public void openActivityForResult(Class<?> cls, int requestCode) {
        openActivityForResult(cls, requestCode, null);
    }

    /**
     * Activity之间获取结果的跳转
     */
    public void openActivityForResult(Class<?> cls, int requestCode, Bundle bundle) {
        try {
            Intent intent = new Intent(mActivity, cls);
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
    public void closeKeyBoard() {
        try {
            View view = mActivity.getWindow().peekDecorView();
            if (view != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loge(String message) {
        Logger.e(TAG + message);
    }

    /**
     * 日志
     */
    public void logi(String message) {
        Logger.i(TAG + message);
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

    public void toast(Object message) {
        Toast.makeText(mActivity, MessageUtils.formatToStr(message), Toast.LENGTH_SHORT).show();
    }

}
