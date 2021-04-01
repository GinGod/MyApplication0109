package com.gingod.myapplication0109.base;

import android.app.Service;
import android.widget.Toast;

public abstract class BaseService extends Service {
    private Toast mToast = null;

    /**
     * 只显示最后一个toast
     */
    public void showSingleToast(CharSequence text) {
        try {
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(this, text + "", Toast.LENGTH_SHORT);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
