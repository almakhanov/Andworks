package com.qwer.firstintranet

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //var s = Student("ab", 5, 5 ,5.2)

    fun openStudent(view: View){
        val intnt = Intent(this, StudentActivity::class.java)
        startActivity(intnt)
    }

}
