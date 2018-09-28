package kz.batana.timer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kz.batana.timer.util.NotificationUtil
import kz.batana.timer.util.PrefUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(MainActivity.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)

    }
}
