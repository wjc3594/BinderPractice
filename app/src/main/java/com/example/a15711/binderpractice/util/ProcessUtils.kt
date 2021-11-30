package com.example.a15711.binderpractice.util

import android.app.ActivityManager
import android.content.Context
import android.os.Process

object ProcessUtils {

    /**
     * 通过ActivityManager获取到进程名字
     */
    fun getCurrentProcessName(context: Context):String?{
        var myPid = Process.myPid()
        var activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        var runningAppProcesses = activityManager.runningAppProcesses
        if (!runningAppProcesses.isEmpty()){
            runningAppProcesses.forEach {
                if (it.pid == myPid){
                    return it.processName
                }
            }
        }
        return null
    }
}