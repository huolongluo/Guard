package com.tqxd.guard.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.tqxd.guard.entity.Constant
import com.tqxd.guard.entity.NotificationConfig
import com.tqxd.guard.ext.sMainHandler
import com.tqxd.guard.ext.setNotification

/**
 * 隐藏前台服务
 */
class HideForegroundService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getParcelableExtra<NotificationConfig>(Constant.GUARD_NOTIFICATION_CONFIG)
            ?.let {
                setNotification(it, true)
            }
        sMainHandler.postDelayed({
            stopForeground(true)
            stopSelf()
        }, 2000)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}