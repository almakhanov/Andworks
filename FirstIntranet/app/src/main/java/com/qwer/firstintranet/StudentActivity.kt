package com.qwer.firstintranet

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class StudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

    }

    fun createStudent(view: View){
        val intent = Intent(this, StudentCreateActivity::class.java)
        startActivity(intent)
    }

    fun showStudent(view: View){
        val intent = Intent(this, StudentShowActivity::class.java)
        startActivity(intent)
    }
}
