package kz.batana.beautysoft

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_root.*
import kz.batana.beautysoft.home.HomeFragment
import kz.batana.beautysoft.map.MapFragment
import kz.batana.beautysoft.profile.ProfileFragment
import kz.batana.beautysoft.search.SearchFragment

class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container_fragment, HomeFragment())
                .commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment = HomeFragment()
        when (item.itemId) {
            R.id.navigation_home -> {
                selectedFragment = HomeFragment()
            }
            R.id.navigation_search -> {
                selectedFragment = SearchFragment()
            }
            R.id.navigation_camera -> {
                selectedFragment = MapFragment()
            }
            R.id.navigation_map -> {
                selectedFragment = MapFragment()
            }
            R.id.navigation_profile -> {
                selectedFragment = ProfileFragment()
            }
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.container_fragment, selectedFragment)
                .commit()

        true
    }
}
