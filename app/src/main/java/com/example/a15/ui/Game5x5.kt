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
import com.example.a15.R
import com.example.a15.adapters.Adapter5x5
import com.example.a15.content.CustomDialog
import com.example.a15.content.CustomIsUpDialog
import com.example.a15.content.ItemChangeListener
import com.example.a15.content.Model
import com.example.a15.content.TimerService
import com.example.a15.databinding.ActivityGame5x5Binding
import kotlin.math.roundToInt

class Game5x5 : AppCompatActivity(),ItemChangeListener {
    private lateinit var binding: ActivityGame5x5Binding
    private var starting = false
    private var second = 0.0
    private lateinit var serviceIntent:Intent
    private var nextActions = false
    private var downSeconds = 0
    private var downtime = 0
    private val puzzleAdapter by lazy {
        Adapter5x5(
            { model: Model, emptyModel: Model -> onItemClick(model, emptyModel) }, this)
    }
    private var currentDialog: CustomDialog? = null
    private var customDialog: CustomIsUpDialog? = null
    private val handler = Handler()
    private lateinit var runnable: Runnable

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGame5x5Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.home.setOnClickListener {
            onBackPressed()
        }
        val rv = binding.rvMain4
        rv.adapter = puzzleAdapter
        rv.setHasFixedSize(false)
        puzzleAdapter.loadData(puzzleDataList.shuffled())
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)


        val time = intent.getIntExtra("time5x5", 0)
        val nextAction = intent.getBooleanExtra("nextAction5x5", false)
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
        puzzleAdapter.loadData(puzzleDataList.shuffled())
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
                        this@Game5x5,
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
                        this@Game5x5,
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


    private fun onItemClick(it: Model, emptyModel: Model) {
        puzzleAdapter.changeItem(it, emptyModel)
        puzzleAdapter.changeItem(it, emptyModel)
        if (!starting) {
            startTime()
        }
    }

    private val puzzleDataList = listOf(
        Model(1),
        Model(2),
        Model(3),
        Model(4),
        Model(5),
        Model(6),
        Model(7),
        Model(8),
        Model(9),
        Model(10),
        Model(11),
        Model(12),
        Model(13),
        Model(14),
        Model(15),
        Model(16),
        Model(17),
        Model(18),
        Model(19),
        Model(20),
        Model(21),
        Model(22),
        Model(23),
        Model(),
        Model(24),
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

}