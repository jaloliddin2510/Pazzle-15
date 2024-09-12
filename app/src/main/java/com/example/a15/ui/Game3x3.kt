package com.example.a15.ui

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a15.R
import com.example.a15.adapters.Adapter3x3
import com.example.a15.content.CustomDialog
import com.example.a15.content.CustomIsUpDialog
import com.example.a15.content.ItemChangeListener
import com.example.a15.content.Model
import com.example.a15.content.TimerService
import com.example.a15.databinding.ActivityGame3x3Binding
import kotlin.math.roundToInt

@Suppress("DEPRECATION")
class Game3x3 : AppCompatActivity(), ItemChangeListener {
    private var starting = false
    private lateinit var binding: ActivityGame3x3Binding
    private var second = 0.0
    private lateinit var serviceIntent: Intent
    private var nextActions = false
    private var downSeconds = 0
    private var downtime = 0
    private val puzzleAdapter3x3 by lazy {
        Adapter3x3({ model: Model, emptyModel: Model -> onItemClick(model, emptyModel) }, this)
    }
    private var currentDialog: CustomDialog? = null
    private var customDialog: CustomIsUpDialog? = null
    private val handler = Handler()
    private lateinit var runnable: Runnable

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGame3x3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.home.setOnClickListener {
            finish()
        }
        val rv = binding.rvMain
        rv.layoutManager = GridLayoutManager(this, 3)
        rv.adapter = puzzleAdapter3x3
        puzzleAdapter3x3.loadData(puzzleDataList.shuffled())
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        val time = intent.getIntExtra("time3x3", 0)
        val nextAction = intent.getBooleanExtra("nextAction3x3", false)
        downSeconds = time * 60
        nextActions = nextAction
        downtime = time * 60
        if (nextActions) {
            binding.timeTv.text = getTimeStringFormatDouble(downSeconds.toDouble())
        }
        serviceIntent = Intent(applicationContext, TimerService::class.java)
        val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                second = intent.getDoubleExtra(TimerService.TIMER_EXTRA, 0.0)
                binding.timeTv.text = getTimeStringFormatDouble(second)
            }

        }
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATE), RECEIVER_NOT_EXPORTED)
        binding.pauseIv.setOnClickListener { startStopTimer() }
        binding.refreshCv.setOnClickListener { refreshTimer() }

    }

    fun downTime() {
        runnable = object : Runnable {
            override fun run() {
                if (downSeconds > 0) {
                    downSeconds--
                    binding.timeTv.text = getTimeStringFormatDouble(downSeconds.toDouble())
                    handler.postDelayed(this, 1000)
                } else {
                    stopTime()
                    customDialog?.dismiss()
                    customDialog = CustomIsUpDialog(
                        this@Game3x3,
                        onRecreateGameClick = {
                            refreshTimer()
                            recreate()
                        },
                        onHomeClick = {
                            finish()
                        }
                    )
                    customDialog?.show()
                }

            }
        }
        handler.post(runnable)
    }

    @SuppressLint("DefaultLocale")
    private fun getTimeStringFormatDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hour = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 / 60
        val seconds = resultInt % 86400 % 3600 % 60
        return String.format("%02d:%02d:%02d", hour, minutes, seconds)
    }

    private fun refreshTimer() {
        stopTime()
        if (nextActions) {
            binding.timeTv.text = getTimeStringFormatDouble(downtime.toDouble())
        } else {
            second = 0.0
            binding.timeTv.text = getTimeStringFormatDouble(second)
        }
        recreate()
        puzzleAdapter3x3.loadData(puzzleDataList.shuffled())
    }

    private fun startStopTimer() {
        if (starting) {
            stopTime()
        } else {
            startTime()
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun startTime() {
        if (nextActions) {
            if (!::runnable.isInitialized) {
                initializeRunnable()
                downTime()
            }
        } else {
            serviceIntent.putExtra(TimerService.TIMER_EXTRA, second)
            startService(serviceIntent)
        }
        binding.pauseIv.background = getDrawable(R.drawable.baseline_pause_circle_24)
        starting = true
    }


    private fun initializeRunnable() {
        runnable = object : Runnable {
            override fun run() {
                if (downSeconds > 0) {
                    downSeconds--
                    binding.timeTv.text = getTimeStringFormatDouble(downSeconds.toDouble())
                    handler.postDelayed(this, 1000)
                } else {
                    stopTime()
                    customDialog?.dismiss()
                    customDialog = CustomIsUpDialog(
                        this@Game3x3,
                        onRecreateGameClick = {
                            refreshTimer()
                            recreate()
                        },
                        onHomeClick = {
                            finish()
                        }
                    )
                    customDialog?.show()
                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun stopTime() {
        if (nextActions) {
            handler.removeCallbacks(runnable)
        } else {
            stopService(serviceIntent)
        }
        binding.pauseIv.background = getDrawable(R.drawable.baseline_play_circle_24)
        starting = false
    }

    private val puzzleDataList = listOf(
        Model(1),
        Model(2),
        Model(3),
        Model(4),
        Model(5),
        Model(6),
        Model(7),
        Model(),
        Model(8),
    )

    override fun onItemChange() {
        stopTime()
        currentDialog?.dismiss()
        currentDialog = CustomDialog(
            this,
            onRecreateGameClick = {
                refreshTimer()
                recreate()
            },
            onHomeClick = {
                finish()
            }
        )
        currentDialog?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        currentDialog?.dismiss()
        if (::runnable.isInitialized) {
            handler.removeCallbacks(runnable)
        }
    }


    private fun onItemClick(it: Model, emptyModel: Model) {
        puzzleAdapter3x3.changeItem(it, emptyModel)
        puzzleAdapter3x3.changeItem(it, emptyModel)
        if (!starting) {
            startTime()
        }
    }
}