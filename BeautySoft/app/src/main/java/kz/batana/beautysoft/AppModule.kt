package kz.batana.beautysoft

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import kz.batana.beautysoft.core.coreModule
import kz.batana.beautysoft.core.util.Constants
import kz.batana.beautysoft.home.homeModule
import kz.batana.beautysoft.local_storage.AppLocalDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModules: List<Module>
    get() = listOf(
            localStorageModules,
            coreModule,
            homeModule
    )

val localStorageModules = module {
    single { createSharedPrefs(androidContext()) }
    single { createLocalStorage(androidContext()) }
}


internal fun createSharedPrefs(context: Context) : SharedPreferences {
    return context.applicationContext.getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
}

fun createLocalStorage(context:Context) : AppLocalDatabase {
    return Room.databaseBuilder(context, AppLocalDatabase::class.java,"beauty_soft_db").build()
}


