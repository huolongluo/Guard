package com.tqxd.guard.pix

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import com.tqxd.guard.ext.finishOnePix
import com.tqxd.guard.ext.isScreenOn
import com.tqxd.guard.ext.log
import com.tqxd.guard.ext.setOnePix

/**
 * 一像素界面
 */
class OnePixActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("one pix is created")
        overridePendingTransition(0, 0)
        //设定一像素的activity
        window.setGravity(Gravity.START or Gravity.TOP)
        window.attributes = window.attributes.apply {
            x = 0
            y = 0
            height = 1
            width = 1
        }
        setOnePix()
    }

    override fun onResume() {
        super.onResume()
        if (isScreenOn) {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        finishOnePix()
        log("one pix is destroyed")
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }
}