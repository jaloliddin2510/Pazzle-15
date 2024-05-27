package com.example.a15

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController


class MainActivity : AppCompatActivity() {

    private var second: Long = 0
    private var running: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn=findViewById<CardView>(R.id.puzzle_4x4)
        btn.setOnClickListener {
            Fragment3x3().next()
        }
        val timeTv = findViewById<TextView>(R.id.time_tv)

        val timer = object : CountDownTimer(second * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeTv.text = formatTime(millisUntilFinished)
            }
            override fun onFinish() {
                running = false
            }
        }
        val startStopButton = findViewById<ImageView>(R.id.pause_iv)
        startStopButton.setOnClickListener {
            if (running) {
                timer.cancel()
                running = false
            } else {
                timer.start()
                running = true
            }
        }
        val startPuzzle = findViewById<View>(R.id.fragmentContainerView)
        startPuzzle.setOnClickListener {
            if (running) {
                timer.start()
                running = true
            }
        }
        val resetButton = findViewById<View>(R.id.refresh_cv)
        resetButton.setOnClickListener {
            timer.cancel()
            running = false
            second = 0
            timeTv.text = formatTime(second * 1000)
        }

    }

    @SuppressLint("DefaultLocale")
    fun formatTime(millis: Long): String {
        val minutes = (millis / 1000) / 60
        val seconds = (millis / 1000) % 60
        val milliseconds = (millis % 1000) / 10
        return String.format("%02d : %02d : %02d", minutes, seconds, milliseconds)
    }
}
