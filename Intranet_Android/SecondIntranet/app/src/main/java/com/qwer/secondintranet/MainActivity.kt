package com.qwer.secondintranet


import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.qwer.firstintranet.Student
import com.qwer.secondintranet.Database.StudentDB
import com.qwer.secondintranet.Database.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentable, CreateFragmentListener,
        CreateStudentFragment.CreateStudenFragmentListener, StudentRecyclerFragment.StudentRecyclerFragmentListener,
        StudentProfileFragment.StudentProfileFragmentListener, SampleFragment.OnFragmentInteractionListener {


    private lateinit var firstFragment: MainFragment



    companion object {
        var list = ArrayList<Student>()
        var TAG = "accepted"
        var staticStudent : Student? = null
        var studentDB: StudentDB? = null
        var listDB = ArrayList<User.Student>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()

        openMainFragment()

    }

    private fun initialize(){
        studentDB =  Room.databaseBuilder(this, StudentDB::class.java, "student").build()

        var ind : Long = 1
        for(i in list){
            var stud = User.Student(ind, i.getterName(), i.getterAge(), i.getterGpa())
            Single.fromCallable {
                studentDB?.StudentDao()?.insert(stud)
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()

            ind++
        }


        var stf = StudentPreference(this)

        StudentPreference.getStudSharedPref()
        Log.d(TAG, "List Size = ${list.size}")


        studentDB?.StudentDao()?.getAllStduents()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { listOfStudents ->
                    listDB = listOfStudents
                }

        Log.d(TAG, "listDB size = ${listDB.size}")
    }

    private fun openMainFragment(){
        firstFragment = MainFragment.newInstance("","")

        supportFragmentManager.beginTransaction()
                .add(R.id.firstFragmentContainer, firstFragment)
                .addToBackStack(null)
                .commit()
    }


    override fun createFragment(fragment: Fragment, ind: Int) {
        Log.d(TAG, "$fragment is creating...")
        supportFragmentManager.beginTransaction()
                .replace(ind, fragment)
                .addToBackStack(null)
                .commit()
        Log.d(TAG, "$fragment Created!")
    }

    override fun createFragment(s: Student) {

        Log.d(TAG, "profile is creating...")
        Log.d(TAG, s.toString())

        staticStudent = s


        var profileFragment = StudentProfileFragment.newInstance("","")


        supportFragmentManager.beginTransaction()
                .replace(R.id.firstFragmentContainer, profileFragment)
                .addToBackStack(null)
                .commit()



        Log.d(TAG, "profile Created!")
    }


//    fun openProfile(fragment: Fragment, ind: Int, s: Student) {
//        supportFragmentManager.beginTransaction()
//                .replace(ind, fragment)
//                .addToBackStack(null)
//                .commit()
//        Log.d(TAG, "OnClicked -> $s")
//        fragment.stProfName.text = s.getterName()
//        fragment.stProfAge.text = s.getterAge().toString()
//        fragment.stProfGpa.text = s.getterGpa().toString()
//    }






}

interface CreateFragmentListener{
    fun createFragment(fragment : Fragment, ind : Int)
    fun createFragment(s: Student)
}