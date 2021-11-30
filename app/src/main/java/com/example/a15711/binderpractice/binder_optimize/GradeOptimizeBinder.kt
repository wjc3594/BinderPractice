package com.example.a15711.binderpractice.binder_optimize

import android.os.Binder
import android.os.Parcel
import com.example.a15711.binderpractice.util.GradeHelper

class GradeOptimizeBinder : Binder(), IGradeInterface {
    override fun onTransact(code: Int, data: Parcel?, reply: Parcel?, flags: Int): Boolean {
        if (code == GradeHelper.REQUEST_CODE) {
            val name1 = data?.readString()
            val name2 = data?.readString()
            val grade1 = getGrade(name1)
            val grade2 = getGrade(name2)
            reply?.writeInt(grade1)
            reply?.writeInt(grade2)
            return true
        }
        return super.onTransact(code, data, reply, flags)
    }

    override fun getGrade(name: String?): Int {
        return GradeHelper.getGradeByName(name)
    }
}