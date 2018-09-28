package com.qwer.firstintranet

class Student(name_ : String, age_ : Int, salary_ : Int, gpa_ : Double) : Person(name_, age_, salary_){
    private var gpa : Double

    init {
        this.gpa = gpa_
    }

    fun getterGpa() : Double {
        return gpa
    }




}
