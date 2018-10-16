package kz.batana.beautysoft.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kz.batana.beautysoft.R
import kz.batana.beautysoft.home.discounts.DiscountsFragment
import kz.batana.beautysoft.home.popular.PopularFragment


class HomeFragment : Fragment() {

    private var actionbar: ActionBar? = null
    private lateinit var popularFragment: PopularFragment
    private lateinit var discountsFragment: DiscountsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(toolbar_main)
            actionbar = (activity as AppCompatActivity).supportActionBar
            actionbar?.apply {
                this.title = resources.getString(R.string.app_name)
            }
        }

        //View pager fragments
        val adapter = HomeFragmentPagerAdapter(childFragmentManager)
        popularFragment = PopularFragment()
        discountsFragment = DiscountsFragment()

        adapter.addFragment(popularFragment, "В тренде")
        adapter.addFragment(discountsFragment, "Скидки")
        view_pager_home.adapter = adapter
        tab_layout_home.setupWithViewPager(view_pager_home)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }








}
