/*
 * Copyright (c) DAR Ecosystem 2018.
 *
 * Created by sough on 15/08/2018.
 */

package kz.batana.khanproject

import android.arch.persistence.room.Room
import android.content.Context
import kz.batana.khanproject.audio_list.audioListModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

/**
 * Gather all app modules
 */
val appModules: List<Module>
    get() = listOf(
            roomModule,
            mainModule,
            audioListModule
    )

val mainModule = module{
    factory { MainPresenter(get()) as MainContract.Presenter }
    factory { MainRepository(get()) as MainContract.Repository }
}

val roomModule = module {
    single { getAppDB(androidContext()).audioDao() }
}

fun getAppDB(context: Context) : AudioDB{
    return Room.databaseBuilder(context, AudioDB::class.java, "khan_project2")
            .allowMainThreadQueries()
            .build()
}