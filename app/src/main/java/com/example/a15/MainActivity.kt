package com.example.a15

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.a15.databinding.ActivityMainBinding
import com.google.android.material.R.style.MaterialAlertDialog_Material3_Animation


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var second: Long = 0
    private var running: Boolean = false
    private lateinit var fragmentTransaction: androidx.fragment.app.FragmentTransaction
    private var activeFragment: androidx.fragment.app.Fragment? = null
    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.puzzle3x3.setOnClickListener {
            actionFragment3x3()
        }
        binding.puzzle4x4.setOnClickListener {
            actionFragment4x4()
        }
        binding.puzzle5x5.setOnClickListener {
            actionFragment5x5()
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


    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        if (fragment != activeFragment) {
            fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            activeFragment = fragment
        }
    }

    @SuppressLint("DefaultLocale")
    fun formatTime(millis: Long): String {
        val minutes = (millis / 1000) / 60
        val seconds = (millis / 1000) % 60
        val milliseconds = (millis % 1000) / 10
        return String.format("%02d : %02d : %02d", minutes, seconds, milliseconds)
    }
    fun finishFragment() {
        val finishFragment = FinishFragment()
        replaceFragment(finishFragment)
        invisible()
    }
    fun visible(){
        binding.llGameMode.visibility=View.VISIBLE
    }
    fun invisible(){
        binding.llGameMode.visibility=View.GONE
    }
    fun actionFragment3x3(){
        val fragment3x3=Fragment3x3()
        replaceFragment(fragment3x3)
    }
    fun actionFragment4x4(){
        val fragment4x4=Fragment4x4(this)
        replaceFragment(fragment4x4)
    }
    fun actionFragment5x5(){
        val fragment5x5=Fragment5x5()
        replaceFragment(fragment5x5)
    }


//    @SuppressLint("PrivateResource", "MissingInflatedId")
//    fun simpleDialog(
//        action1: (() -> Unit)? = null,
//    ) {
//        val dialog = Dialog(this)
//        val view = LayoutInflater.from(this).inflate(R.layout.finish_dialog, null)
//        dialog.setContentView(view)
//
//        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        dialog.window?.attributes?.windowAnimations = MaterialAlertDialog_Material3_Animation
//
//        val iconImageView = view.findViewById<ImageView>(R.id.refresh_cv)
//
//        iconImageView.setOnClickListener {
//            action1?.invoke()
//            dialog.dismiss()
//        }
//        dialog.show()
//    }
}

