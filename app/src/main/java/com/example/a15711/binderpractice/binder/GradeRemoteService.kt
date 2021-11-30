package com.example.a15711.binderpractice.binder

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Parcel
import android.util.Log
import com.example.a15711.binderpractice.util.GradeHelper
import com.example.a15711.binderpractice.util.GradeHelper.REQUEST_CODE
import com.example.a15711.binderpractice.util.ProcessUtils

class GradeRemoteService : Service() {

    private val mBinder = object : Binder() {
        override fun onTransact(code: Int, data: Parcel?, reply: Parcel?, flags: Int): Boolean {
            if (code == REQUEST_CODE) {
                val name1 = data?.readString()
                val name2 = data?.readString()
                val grade1 = GradeHelper.getGradeByName(name1)
                val grade2 = GradeHelper.getGradeByName(name2)
                reply?.writeInt(grade1)
                reply?.writeInt(grade2)
                Log.e("wjc", ProcessUtils.getCurrentProcessName(context = this@GradeRemoteService))
                return true
            }
            return super.onTransact(code, data, reply, flags)
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }
}
