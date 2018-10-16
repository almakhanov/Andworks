package kz.batana.beautysoft

import android.content.Context
import android.content.SharedPreferences
import kz.batana.lab3.core.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModules: List<Module>
    get() = listOf(
            localStorageModules
    )

val localStorageModules = module {
    single { createSharedPrefs(androidContext()) }
}


internal fun createSharedPrefs(context: Context) : SharedPreferences {
    return context.applicationContext.getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
}


