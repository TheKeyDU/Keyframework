package com.maosong.component.view.impl;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import com.maosong.component.view.BaseView;
import com.maosong.tools.ToastUtils;
import com.itkey.component.R;

/**
 * create by colin on 2019-05-08
 */
public class BaseViewImpl implements BaseView {
    private Context mContext;
    private ProgressDialog mLoadDialog;

    public BaseViewImpl(Context context) {
        mContext = context;
    }

    @Override
    public void showLoading() {
        showLoading(null);
    }

    @Override
    public void showLoading(String message) {
        if (mLoadDialog == null) {
            mLoadDialog = new ProgressDialog(mContext);
            mLoadDialog.setCancelable(false);
        }
        if (TextUtils.isEmpty(message)) {
            mLoadDialog.setMessage(mContext.getString(R.string.wait));
        } else {
            mLoadDialog.setMessage(message);
        }
//        if (!mLoadDialog.isShowing())
        mLoadDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mLoadDialog != null && mLoadDialog.isShowing())
        {mLoadDialog.dismiss();}
    }

    @Override
    public void showTipMessage(String msg) {
        ToastUtils.showLongToast(msg);
    }


    @Override
    public String getNetKey() {
        return "";
    }





    public void showAlertDialog(String message) {
        showAlertDialog(message, (dialog, which) -> dialog.dismiss());
    }

    public void showAlertDialog(String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(mContext)
                .setMessage(message)
                .setPositiveButton("Ok", listener)
                .setCancelable(false)
                .show();
    }
}
