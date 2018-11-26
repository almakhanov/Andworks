package kz.batana.beautysoft.home.institution_details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_news_details.*
import kz.batana.beautysoft.R
import kz.batana.beautysoft.core.entity.Salon
import kz.batana.beautysoft.core.util.Constants.SALONS

class InstitutionDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val news = intent.getSerializableExtra(SALONS) as Salon
        if (news.imageUrl != "")
            Glide.with(this).load(news.imageUrl).into(main_backdrop)
        tvTitle.text = news.title
        tvDate.text = news.date
        tvContent.text = news.content

    }
}
