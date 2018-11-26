package kz.batana.beautysoft.core.util

import android.util.Log
import kz.batana.beautysoft.BuildConfig

/**
 * Handle Logging in app
 */
object Logger {

    fun msg(tag: String, msg: Any?) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, "$msg")
        }
    }

    fun msg(msg: Any?) {
        msg("MSG", "$msg")
    }

    fun api(msg: String?) {
        msg("API", "$msg")
    }

    fun test(whereRYouTesting: String?, msg: String?){
        msg("accepted", "$whereRYouTesting : $msg")
    }
}