package com.qwer.firstintranet

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_student_create.*

class StudentCreateActivity : AppCompatActivity() {

    var list = ArrayList<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this,"opened", Toast.LENGTH_SHORT).show()
        setContentView(R.layout.activity_student_create)
        //list = getUsers()
        //println(list.toString())
    }

    fun saveStudent(view : View){

        println(ageText.text.toString().toInt())
        println("test")

//        var s = Student("ihnk", 76, 658, 2.3)
//        println(s.toString())

        /*val name_ : String = nameView.text.toString()
        val age_ : Int = ageText.text.toString().toInt()
        val salary_ : Int = salaryText.text.toString().toInt()
        val gpa_ : Double = gpaText.text.toString().toDouble()

        var s = Student(name_, age_, salary_, gpa_)

        list.add(s)
        println(s)
        Toast.makeText(this,"saving...", Toast.LENGTH_SHORT).show()

        /*val prefEditor = PreferenceManager.getDefaultSharedPreferences(this).edit()
        val jsonString = Gson().toJson(list)
        prefEditor.putString("students", jsonString).apply()

        Toast.makeText(this,"Saved!!!", Toast.LENGTH_SHORT).show()*/*/
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
