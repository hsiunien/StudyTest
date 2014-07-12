package com.ahsiu.studytest.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by hsiunien on 2014/7/12.
 *
 *
 *
  <receiver
        android:name="com.ahsiu.studytest.NetworkChangeReceiver"
        android:label="NetworkChangeReceiver" >
             <intent-filter>
                    <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
                    <action android:name="android.net.wifi.WIFI_STATE_CHANGED" />
            </intent-filter>
 </receiver>
 *
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
    }
}
