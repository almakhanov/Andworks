/*
 * Copyright (c) DAR Ecosystem 2018.
 *
 * Created by sough on 14/08/2018.
 */

package kz.batana.beautysoft

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import kz.batana.beautysoft.core.util.Logger
import org.koin.android.ext.android.startKoin

/**
 * Custom application class
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.msg("accepted", "App")

        startKoin(this, appModules)
        Fresco.initialize(this)
    }

    companion object {
        @JvmStatic var instance: App? = null
            private set



    }
}