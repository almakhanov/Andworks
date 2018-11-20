package com.qwer.firstkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastMe(view: View){
        val myToast = Toast.makeText(this, "Hello Boss", Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun countMe(view: View) {
        var cntString: TextView= findViewById(R.id.res)
        var number:String=cntString.text.toString()
        var cnt: Int = Integer.parseInt(number)
        cnt++
        cntString.text=cnt.toString()
    }

    fun randMe(view: View){
        val randomIntent = Intent(this, SecondActivity::class.java)

        val cntString = res.text.toString()
        val cnt = Integer.parseInt(cntString)
        randomIntent.putExtra(SecondActivity.TOTAL_COUNT, cnt)

        startActivity(randomIntent);
    }
}