package com.qwer.secondintranet

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.qwer.firstintranet.Student
import com.qwer.secondintranet.Database.User
import com.qwer.secondintranet.MainActivity.Companion.studentDB
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_create_student.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CreateStudentFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: CreateStudenFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveBtn.setOnClickListener{
            var name : String = nameTxt.text.toString()
            var age : Int = ageTxt.text.toString().toInt()
            var gpa : Double = gpaTxt.text.toString().toDouble()

            var s = Student(name, age, gpa)
            saveStudent(s)

            nameTxt.setText("")
            ageTxt.setText("")
            gpaTxt.setText("")

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CreateStudenFragmentListener) {
            listener = context as MainActivity
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface CreateStudenFragmentListener {

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                CreateStudentFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


    private fun saveStudent(s : Student) {
        Log.d("accepted", "method saveStudent")
        MainActivity.list.add(s)
        StudentPreference.putStudSharedPref()
        Toast.makeText(activity,"saved(${MainActivity.list.size})", Toast.LENGTH_SHORT).show()

        //DB
        var stud = User.Student(0, s.getterName(), s.getterAge(), s.getterGpa())
        Single.fromCallable {
            studentDB?.StudentDao()?.insert(stud)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }




}
