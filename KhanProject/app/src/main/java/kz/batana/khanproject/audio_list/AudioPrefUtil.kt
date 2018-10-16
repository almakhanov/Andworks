package kz.batana.khanproject.audio_list

import android.content.Context
import android.preference.PreferenceManager





class AudioPrefUtil(private val context: Context){
    private val POSITION = "POSITION"

    private var pref = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveInstance(positionInList: Int){
        pref.edit().apply{
            putInt(POSITION, positionInList)
            apply()
        }
    }

    fun getPosition(): Int? = pref.getInt(POSITION, -1)
}