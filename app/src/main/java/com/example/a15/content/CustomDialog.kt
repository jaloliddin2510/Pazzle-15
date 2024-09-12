package com.example.a15.content

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.ImageView
import com.example.a15.R

class CustomDialog(
    context: Context,
    private val onRecreateGameClick: () -> Unit,
    private val onHomeClick: () -> Unit
) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_finish)

        val recreateButton: ImageView = findViewById(R.id.recreate_game3x3)
        val homeButton: ImageView = findViewById(R.id.home_activity)

        recreateButton.setOnClickListener {
            onRecreateGameClick()
            dismiss()
        }

        homeButton.setOnClickListener {
            onHomeClick()
            dismiss()
        }
    }
}

