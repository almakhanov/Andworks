package com.qwer.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qwer.fragments.MainActivity
import com.qwer.fragments.MyListener
import com.qwer.fragments.R
import kotlinx.android.synthetic.main.fragment_second.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SecondFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var callback: MyListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            callback?.onFragmentButtonClicked()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as MainActivity
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SecondFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
