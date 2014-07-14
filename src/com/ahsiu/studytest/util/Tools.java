package com.ahsiu.studytest.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;

/**
 * Created by hsiunien on 2014/7/12.
 */
public class Tools {
    /**
     * 检查用户是否打开GPS,返回GPS打开状态（true 表示 GPS 已打开）
     *
     * @return boolean
     * @author hsiunien
     */
    public static boolean check_GPS_is_open(Context context) {
        LocationManager alm = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            // GPS已经打开
            return true;
        } else {
            // GPS没有打开
            return false;
        }
    }

    /**
     * 获得当前软件的版本号
     *
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 1;
        try {
            String pkNam = context.getPackageName();
            versionCode = context.getPackageManager()
                    .getPackageInfo(pkNam, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获得当前软件的版本名字 （这个是给人看的）
     *
     * @return
     */
    public static String getVersionName(Context context) {
        String verName = "";
        try {
            String pkNam = context.getPackageName();
            verName = context.getPackageManager()
                    .getPackageInfo(pkNam, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    public static void installApk(Context context, String fileSavePath) {

        File file = new File(fileSavePath);
        if (!file.exists()) {
            Log.i("", "找不到下载的软件");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    //设置act全屏
    public static void setActivityFullScreen(Activity act) {
        act.requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        act.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
    }

    //设置act横屏 在onresume中调用 竖屏 SCREEN_ORIENTATION_PORTRAIT
    //另一种方法：在xml中设置 android:screenOrientation="portrait"
    public static void setScreenLandscape(Activity act) {
        /**
         * 设置为横屏
         */
        if (act.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            act.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    public static DisplayMetrics getScreeenDisplay(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return displayMetrics;
    }

}
