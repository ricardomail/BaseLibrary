package com.test.nice.baselibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.test.nice.baselibrary.utils.AppActivityManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * BaseActivity class
 *
 * @author CJX
 * @date 2017/10/20
 */
public abstract class BaseActivity extends AppCompatActivity {
    public BaseActivity activity;
    /**
     * 获取布局ID
     *
     * @return 布局ID
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 界面初始化前期准备
     */
    protected void beforeInit(){
        activity = this;
        AppActivityManager.getAppManager().addActivity(activity);
    }

    /**
     * 初始化布局以及view控件
     * @param saveInstanceState
     */
    protected abstract void initView(Bundle saveInstanceState);


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeInit();
        if(getContentViewLayoutID() != 0){
            setContentView(getContentViewLayoutID());
            initView(savedInstanceState);
        }
    }


    private Toast mToast;

    /**
     * Toast工具
     *
     * @param desc
     */
    protected void showToast(String desc){
        if (mToast == null){
            mToast = Toast.makeText(this.getApplicationContext(),desc, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(desc);
        }
        mToast.show();
    }

    /**
     *
     * 设置状态栏全透明
     *
     * @param activity
     */
    protected void setTranspart(Activity activity){
        StatusBarUtil.setTranslucent(activity);
    }


    /**
     * 设置状态栏颜色
     * @param activity
     * @param color
     */
    protected void setColor(Activity activity, int color){
        StatusBarUtil.setColor(activity, color);
    }

    /**
     * 为drawerlayout设置状态栏透明度
     *
     * @param activity
     * @param drawerLayout
     * @param color
     */
    protected void setDrawerLayout(Activity activity, DrawerLayout drawerLayout, int color){
        StatusBarUtil.setColorForDrawerLayout(activity, drawerLayout, color);
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


    /**
     * 检查权限
     */
    private boolean checkPermission(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
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
            ActivityCompat.requestPermissions(this, permissions, requestCode);
        }
    }

    /**
     * 再次申请时
     */
    private boolean shouldShowRequestPermission(String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 弹出的Dialog
     */
    private void showRequestDialog(String msg, final String[] permissions, final int requestCode) {
        new AlertDialog.Builder(this)
                .setTitle("友好提醒")
                .setMessage(msg)
                .setPositiveButton("好，给你", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(activity, permissions, requestCode);
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


}
