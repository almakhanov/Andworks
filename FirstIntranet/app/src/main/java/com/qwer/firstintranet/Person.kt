package com.qwer.firstintranet

open class Person(name_ : String, age_ : Int, salary_ : Int){
    private var name : String
    private var age : Int
    private var salary : Int


    fun getterName() : String = name
    fun getterAge() : Int = age
    fun getterSalary() : Int = salary



    init {
        name = name_
        age = age_
        salary = salary_
    }




}