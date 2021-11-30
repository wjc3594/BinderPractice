package com.example.a15711.binderpractice.binder_optimize

import android.app.Service
import android.content.Intent
import android.os.IBinder

class GradeRemoteOptimizeService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return GradeOptimizeBinder()
    }
}
