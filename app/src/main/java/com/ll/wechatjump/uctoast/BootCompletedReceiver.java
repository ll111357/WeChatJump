package com.ll.wechatjump.uctoast;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by lenovo on 2018/1/5.
 */

public class BootCompletedReceiver  extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ListenClipboardService.startForWeakLock(context, intent);
    }
}
