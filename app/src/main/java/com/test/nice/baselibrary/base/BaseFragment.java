package com.test.nice.baselibrary.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * BaseFragment class
 *
 * @author CJX
 * @date 2017/10/20
 */
public abstract class BaseFragment extends Fragment{
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(setFragmentLayoutID(), container, false);
        initView(rootView);
        initData();
        return rootView;
    }

    /**
     * 获取fragment布局
     * @return
     */
    public abstract int setFragmentLayoutID();


    /**
     * 初始化画面元素
     * @param rootView
     */
    protected abstract void initView(View rootView);


    /**
     * 初始化数据
     */
    protected abstract void initData();


    /**
     * 检查权限
     */
    private boolean checkPermission(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 申请权限
     */
    private void requestPermission(String msg, String[] permissions, int requestCode) {
        //先判断是否已经拒绝过了
        if (shouldShowRequestPermission(permissions)) {
            //弹出Dialog 给用户说明
            showRequestDialog(msg, permissions, requestCode);
        } else {
            ActivityCompat.requestPermissions(getActivity(), permissions, requestCode);
        }
    }

    /**
     * 再次申请时
     */
    private boolean shouldShowRequestPermission(String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 弹出的Dialog
     */
    private void showRequestDialog(String msg, final String[] permissions, final int requestCode) {
        new AlertDialog.Builder(getActivity())
                .setTitle("友好提醒")
                .setMessage(msg)
                .setPositiveButton("好，给你", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(getActivity(), permissions, requestCode);
                    }
                })
                .setNegativeButton("我拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }



    /**
     * 检查回调结果
     */
    private boolean checkRequestResult(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    private Toast mToast;
    /**
     * 显示Toast,页面中重复Toast不会重复实例化Toast对象
     *
     * @param str      String
     * @param duration 显示时间
     */
    public void showToast(String str, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), str, duration);
        } else {
            mToast.setText(str);
            mToast.setDuration(duration);
        }
        mToast.show();
    }


    /**
     * 注册eventbus
     * @param context
     */
    protected void eventRegister(Context context){
        EventBus.getDefault().register(context);
    }

    /**
     * eventbus解除注册
     * @param context
     */
    protected void eventUnRegister(Context context){
        EventBus.getDefault().unregister(context);
    }
}
