package com.example.a15.content

import android.app.Service
import android.content.Intent
import java.util.Timer
import java.util.TimerTask

class TimerService : Service() {
    override fun onBind(intent: Intent?) = null

    private val timer = Timer()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val time = intent?.getDoubleExtra(TIMER_EXTRA, 0.0)
        timer.schedule(time?.let {
            TimeTask(it)
        }, 0, 1000)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
    inner class TimeTask(
        private var time: Double) : TimerTask() {
        override fun run() {
            val intent = Intent(TIMER_UPDATE)
            time++
            intent.putExtra(TIMER_EXTRA, time)
            sendBroadcast(intent)
        }
    }

    companion object {
        const val TIMER_UPDATE = "TimerUpdate"
        const val TIMER_EXTRA = "TimerExtra"
    }
}