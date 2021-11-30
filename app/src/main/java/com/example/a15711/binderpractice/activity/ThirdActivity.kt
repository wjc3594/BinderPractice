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
import com.example.a15711.binderpractice.aidl.IGradeInterface
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {

    private lateinit var serviceConnection: ServiceConnection

    private var gradeInterface: IGradeInterface? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        init()
        bind_service_3.setOnClickListener {
            val action = "android.intent.action.server.aidl.gradeservice"
            val intent = Intent(action)
            intent.`package` = packageName
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
        get_grade_3.setOnClickListener {
            val grade = gradeInterface?.getGrade("yy")
            Toast.makeText(this, "grade=$grade", Toast.LENGTH_SHORT).show()
        }
    }

    fun init() {
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                gradeInterface = IGradeInterface.Stub.asInterface(service)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                gradeInterface = null
            }
        }
    }
}
