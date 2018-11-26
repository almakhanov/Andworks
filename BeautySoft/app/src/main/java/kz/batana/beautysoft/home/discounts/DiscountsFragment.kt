package kz.batana.beautysoft.home.discounts


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_discounts.*
import kz.batana.beautysoft.R
import kz.batana.beautysoft.core.entity.Salon
import kz.batana.beautysoft.core.util.Constants
import kz.batana.beautysoft.core.util.Logger
import kz.batana.beautysoft.home.institution_details.InstitutionDetailsActivity
import kz.batana.beautysoft.home.popular.SalonsAdapter
import java.io.Serializable

class DiscountsFragment : Fragment(), SalonsAdapter.NewsItemClicked {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discounts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPopularList()
    }

    private fun getPopularList() {
        val dataSet = ArrayList<Salon>()
        dataSet.apply {
            add(Salon(0, "Очень большая скидка", "30%",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "http://tvoi-noski.ru/wp-content/uploads/2018/04/HDR-Lr-resize.jpg"))
            add(Salon(0, "Очень большая скидка", "30%",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "http://laudator.ru/wp-content/uploads/2013/05/salon-krasotyi-svoy-biznes.jpg"))
            add(Salon(0, "Очень большая скидка", "30%",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "https://ratatum.com/wp-content/uploads/2017/07/salon-krasoty.jpg"))
            add(Salon(0, "Очень большая скидка", "30%",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "https://www.retail-loyalty.org/upload/medialibrary/bb4/bb44e587986b4fa40aa91e996d2dd4b8.jpg"))
            add(Salon(0, "Очень большая скидка", "30%",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXN_YRpJxzA3uHOrx8cHx0B_aZQC_j9PLJJ_gmWGiRQwtj4i7r"))
            add(Salon(0, "Очень большая скидка", "30%",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "http://strg1.mebel.kz/neofiles/serve-image/57c9434cf0f84513cf002398/1170x750"))
            add(Salon(0, "Очень большая скидка", "30%",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwFoyfHEyDiFOhABDChulr7CUzyBy_g7yJ2S80XIjQIb7NmsXB"))
            add(Salon(0, "Очень большая скидка", "30%",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "http://tvoi-noski.ru/wp-content/uploads/2018/04/HDR-Lr-resize.jpg"))
            add(Salon(0, "Очень большая скидка", "30%",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "http://tvoi-noski.ru/wp-content/uploads/2018/04/HDR-Lr-resize.jpg"))
            add(Salon(0, "Очень большая скидка", "30%",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "http://tvoi-noski.ru/wp-content/uploads/2018/04/HDR-Lr-resize.jpg"))
            add(Salon(0, "Очень большая скидка", "30%",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "http://tvoi-noski.ru/wp-content/uploads/2018/04/HDR-Lr-resize.jpg"))
            add(Salon(0, "Очень большая скидка", "30%",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "http://tvoi-noski.ru/wp-content/uploads/2018/04/HDR-Lr-resize.jpg"))
        }

        setNewsList(dataSet)
    }

    fun setNewsList(newsList: ArrayList<Salon>) {
        if (context?.resources?.configuration?.orientation== Configuration.ORIENTATION_LANDSCAPE) {
            recycler_discounts.layoutManager = GridLayoutManager(context,2)
        } else{
            recycler_discounts.layoutManager = LinearLayoutManager(context)
        }
        recycler_discounts.adapter = SalonsAdapter(activity!!, newsList, this)
    }

    override fun onItemClicked(salon: Salon) {
        val intent = Intent(activity, InstitutionDetailsActivity::class.java)
        intent.putExtra(Constants.SALONS, salon as Serializable)
        startActivity(intent)
        Logger.msg("accepted", "clicked -> $salon")
    }


}
