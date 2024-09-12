package com.example.a15.management

import CustomTimeDialog
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.a15.R
import com.example.a15.ui.Game3x3
import com.example.a15.ui.Game4x4
import com.example.a15.ui.Game5x5
import com.example.a15.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentDialog: CustomTimeDialog? = null
    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.puzzle3x3.setOnClickListener {
            startActivity(Intent(this, Game3x3::class.java))
        }
        binding.puzzle4x4.setOnClickListener {
            startActivity(Intent(this, Game4x4::class.java))
        }
        binding.puzzle5x5.setOnClickListener {
            startActivity(Intent(this, Game5x5::class.java))
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        binding.puzzle3x3CountDown.setOnClickListener {
            currentDialog?.dismiss()
            currentDialog = CustomTimeDialog(
                this,
                onOkClickListener = {
                    if (it > 0) {
                        val intent = Intent(this, Game3x3::class.java)
                        intent.putExtra("time3x3", it)
                        intent.putExtra("nextAction3x3", true)
                        startActivity(intent)
                        currentDialog?.dismiss()
                    } else {
                        Toast.makeText(this, "Invalid value!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            )
            currentDialog?.show()
        }
        binding.puzzle4x4CountDown.setOnClickListener {
            currentDialog?.dismiss()
            currentDialog = CustomTimeDialog(
                this,
                onOkClickListener = {
                    if (it > 0) {
                        val intent = Intent(this, Game4x4::class.java)
                        intent.putExtra("time4x4", it)
                        intent.putExtra("nextAction4x4", true)
                        startActivity(intent)
                        currentDialog?.dismiss()
                    } else {
                        Toast.makeText(this, "Invalid value!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            )
            currentDialog?.show()
        }
        binding.puzzle5x5CountDown.setOnClickListener {
            currentDialog?.dismiss()
            currentDialog = CustomTimeDialog(
                this,
                onOkClickListener = {
                    if (it > 0) {
                        val intent = Intent(this, Game5x5::class.java)
                        intent.putExtra("time5x5", it)
                        intent.putExtra("nextAction5x5", true)
                        startActivity(intent)
                        currentDialog?.dismiss()
                    } else {
                        Toast.makeText(this, "Invalid value!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            )
            currentDialog?.show()
        }
        binding.profile.setOnClickListener {
        }

    }

}

