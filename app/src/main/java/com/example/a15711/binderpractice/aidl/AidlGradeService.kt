package com.example.a15711.binderpractice.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.a15711.binderpractice.util.GradeHelper

class AidlGradeService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return CustomBinder()
    }

    class CustomBinder : IGradeInterface.Stub() {
        override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?) {
        }

        override fun getGrade(name: String?): Int {
            return GradeHelper.getGradeByName(name)
        }

    }
}
