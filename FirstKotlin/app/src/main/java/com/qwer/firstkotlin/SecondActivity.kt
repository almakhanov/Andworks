package com.qwer.firstkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*

class SecondActivity : AppCompatActivity() {

    companion object {
        const val TOTAL_COUNT = "total_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        showRandNum()
    }

    fun showRandNum(){
        val cnt = intent.getIntExtra(TOTAL_COUNT, 0)
        val random = Random()
        var randInt = 0

        if(cnt > 0){
            randInt = random.nextInt(cnt+1)
        }

        textViewRand.text = Integer.toString(randInt)
        textViewLabel.text = getString(R.string.randHeading, cnt)
    }
}
