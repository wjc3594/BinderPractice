package com.example.a15711.binderpractice.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Parcel
import android.util.Log
import android.widget.Toast
import com.example.a15711.binderpractice.R
import com.example.a15711.binderpractice.util.ProcessUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var serviceConnection: ServiceConnection
    var localBinder: IBinder? = null
    val REQUEST_CODE = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        //1、绑定远程服务
        bind_service.setOnClickListener {
            val action = "android.intent.action.server.gradeRemoteService"
            val intent = Intent(action)
            intent.`package` = packageName
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
        //2、获取学生分数
        get_grade.setOnClickListener {
            Log.e("wjc", ProcessUtils.getCurrentProcessName(context = this))
            val grade = getGradeByName(name1 = "wjc", name2 = "yy")
            Toast.makeText(this, grade.toString(), Toast.LENGTH_SHORT).show()
        }
        //跳转下一个activity
        jump_second.setOnClickListener { startActivity(Intent(this, SecondActivity::class.java)) }
    }

    fun init() {
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                localBinder = service
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                localBinder = null
            }
        }
    }

    fun getGradeByName(name1: String, name2: String) {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        data.writeString(name1)
        data.writeString(name2)
        localBinder?.transact(REQUEST_CODE, data, reply, 0)
        Toast.makeText(this, "$name1 grade=${reply.readInt()},$name2 grade=${reply.readInt()}", Toast.LENGTH_SHORT).show()
    }
}
