package com.example.a15711.binderpractice.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import com.example.a15711.binderpractice.R
import com.example.a15711.binderpractice.binder_optimize.BinderProxy
import com.example.a15711.binderpractice.binder_optimize.IGradeInterface
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private var gradeInterface: IGradeInterface? = null
    private lateinit var serviceConnection: ServiceConnection
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        init()
        bind_service_2.setOnClickListener {
            val action = "android.intent.action.server.gradeRemoteOptimizeService"
            val intent = Intent(action)
            intent.`package` = packageName
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
        get_grade_2.setOnClickListener {
            val grade = gradeInterface?.getGrade("wjc")
            Toast.makeText(this, "grade=$grade", Toast.LENGTH_SHORT).show()
        }
        jump_third.setOnClickListener { startActivity(Intent(this,ThirdActivity::class.java)) }
    }

    fun init() {
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                gradeInterface = BinderProxy.asInterface(service)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                gradeInterface = null
            }
        }
    }
}
