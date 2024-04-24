package com.example.a15

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var second:Long = 0
    private var running: Boolean = false
    private var wasRunning: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            second = savedInstanceState.getLong(SECOND)
            running = savedInstanceState.getBoolean(RUNNING)
            wasRunning = savedInstanceState.getBoolean(WAS_RUNNING)
        }
        runTimer()

    }

    @Suppress("DEPRECATION")
    private fun runTimer() {
        val timeView: TextView = findViewById(R.id.time_tv)
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                val hours = second / 3600
                val minutes = (second % 60) / 100
                val seconds = second/100

                val time = String.format(
                    Locale.getDefault(),
                    "%02d:%02d:%02d:%02d",
                    hours,
                    minutes,
                    seconds,
                )
                timeView.text = time
                if (running) {
                    second++
                }
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }


    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }
    }

    fun onClickStart(view: View) {
        running = true
    }

    fun onClickPause(view: View) {
        running = false
    }

    fun onClickRefresh(view: View) {
        running = false
        second = 0
    }

    companion object {
        const val SECOND = "milliSecond"
        const val RUNNING = "running"
        const val WAS_RUNNING = "wasRunning"
    }
}
