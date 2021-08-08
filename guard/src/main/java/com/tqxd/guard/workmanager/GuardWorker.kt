package com.tqxd.guard.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.tqxd.guard.ext.*

/**
 * WorkManager定时器
 */
class GuardWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    /**
     * 停止标识符
     */
    private var mIsStop = false

    init {
        context.registerStopReceiver {
            mIsStop = true
        }
    }

    override fun doWork(): Result {
        context.apply {
            val guardConfig = getConfig()
            log("${this@GuardWorker}-doWork")
            if (!isGuardRunning && !mIsStop && !isStopped) {
                register(guardConfig)
            }
        }
        return Result.success()
    }
}