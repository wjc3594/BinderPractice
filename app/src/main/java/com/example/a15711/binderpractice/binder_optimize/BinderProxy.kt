package com.example.a15711.binderpractice.binder_optimize

import android.os.IBinder
import android.os.Parcel
import com.example.a15711.binderpractice.util.GradeHelper

class BinderProxy private constructor(iBinder: IBinder?) : IGradeInterface {
    private var iBinder: IBinder? = iBinder

    override fun getGrade(name: String?): Int {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        data.writeString(name)
        iBinder?.transact(GradeHelper.REQUEST_CODE, data, reply, 0)
        return reply.readInt()
    }

    companion object {
        fun asInterface(iBinder: IBinder?): IGradeInterface {
            if (iBinder is IGradeInterface) {
                //当前进程
                return iBinder
            } else {
                return BinderProxy(iBinder)
            }
        }
    }
}