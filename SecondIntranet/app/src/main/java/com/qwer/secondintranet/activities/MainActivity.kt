package com.qwer.secondintranet.activities


import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.qwer.firstintranet.Student
import com.qwer.secondintranet.R
import com.qwer.secondintranet.database.StudentPreference
import com.qwer.secondintranet.database.UserDB
import com.qwer.secondintranet.fragments.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//import com.qwer.secondintranet.Database.StudentDB

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentable, CreateFragmentListener,
        CreateStudentFragment.CreateStudenFragmentListener, StudentRecyclerFragment.StudentRecyclerFragmentListener,
        StudentProfileFragment.StudentProfileFragmentListener, SampleFragment.OnFragmentInteractionListener {


    private lateinit var firstFragment: MainFragment
    var toolbar : android.support.v7.widget.Toolbar? = null


    companion object {
        var list = ArrayList<Student>()
        var TAG = "accepted"
        var staticStudent : Student? = null
        var facultyList = arrayOf("Select your Faculty","Faculty of Information Technology", "International School of Management",
                "Business School", "Faculty of Oil and Gas", "Center of Math and Cybernetics", "Kazakhstan Maritime Academy")
        var yearOfStudyList = arrayOf("Select your year of Study","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
        var specList = arrayOf("Select your Specialization","Information Systems", "Computer Systems and Software", "Automation and Control", "Management",
                "Finance","Project Management", "Economics", "Petroleum Engineering", "Geology" )
        var studentDB: UserDB? = null
        //var listDB : Flowable<ArrayList<User.Student>>? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()

        openMainFragment()


    }

    private fun initialize(){
        studentDB =  Room.databaseBuilder(this, UserDB::class.java, "student").build()



        var stf = StudentPreference(this)

        StudentPreference.getStudSharedPref()
        Log.d(TAG, "List Size = ${list.size}")


//        var ind : Int = 1
//        for(i in list){
//            var stud = User(ind, i.getterName(), i.getterAge(), i.getterGpa())
//            Single.fromCallable {
//                studentDB?.userDao()?.insertUser(stud)
//            }.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread()).subscribe()
//
//            ind++
//            Log.d(TAG, "inserted: $i")
//        }




        studentDB?.userDao()?.getAllUsers()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { listOfStudents ->
                    Log.d(TAG, listOfStudents.toString())
                }

        //Log.d(TAG, "listDB size = ${listDB}")


    }



    private fun openMainFragment(){
        firstFragment = MainFragment.newInstance("", "")

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


        var profileFragment = StudentProfileFragment.newInstance("", "")


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