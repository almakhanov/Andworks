package com.qwer.secondintranet.database

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Entity(tableName = "user")
data class User(
        @PrimaryKey(autoGenerate = true)
        var id : Int,

        var name : String,

        var age : Int,

        var gpa : Double
)

@Dao
interface UserDao{
    @Query("SELECT * FROM user")
    fun getAllUsers() : Flowable<List<User>>

    @Insert
    fun insertUser(user : User)

}

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDB : RoomDatabase(){
    abstract fun userDao() : UserDao
}