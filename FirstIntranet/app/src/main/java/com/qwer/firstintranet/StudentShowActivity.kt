package com.qwer.firstintranet

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_student_show.*

class StudentShowActivity : AppCompatActivity() {

    var list = arrayListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_show)
        list = getUsers()
        println(list.toString())

        val recyclerView = RecView as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val adapter = StudentAdapter(list)
        recyclerView.adapter = adapter
    }

    private fun getUsers(): ArrayList<Student>{
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val jsonString = prefs.getString("students", null)

        return if(jsonString != null)
            Gson().fromJson(jsonString, arrayListOf<Student>().javaClass)
        else
            arrayListOf()
    }
}
