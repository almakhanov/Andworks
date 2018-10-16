package kz.batana.beautysoft.profile


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kz.batana.beautysoft.R

class ProfileFragment : Fragment() {

    private var actionbar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(toolbar_profile)
            actionbar = (activity as AppCompatActivity).supportActionBar
            actionbar?.apply {
                this.title = "Ваш профиль"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile_settinds, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}
