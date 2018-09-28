package com.qwer.fragments

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.qwer.fragments.FirstFragment
import com.qwer.fragments.R
import com.qwer.fragments.SecondFragment

class MainActivity : AppCompatActivity(), MyListener {

    lateinit var fragmentFirst: FirstFragment
    lateinit var fragmentSecond: SecondFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentFirst = FirstFragment.newInstance("", "")
        fragmentSecond = SecondFragment.newInstance("", "")

        supportFragmentManager.beginTransaction()
                .add(R.id.firstContainer, fragmentFirst)
                .commit()

        supportFragmentManager.beginTransaction()
                .add(R.id.secondContainer, fragmentSecond)
                .commit()

    }

    override fun onFragmentButtonClicked() {
        fragmentFirst.plus()
    }
}

interface MyListener {
    fun onFragmentButtonClicked()
}