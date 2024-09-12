package com.example.a15.content

import android.content.Context
import androidx.fragment.app.FragmentManager

data class Model(
    val number: Int = -1,
    var position: Int = -1,
)

class Shared() {
    companion object {
        fun getSharedPrefs(context: Context) = context.getSharedPreferences(
            SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }
}
const val SHARED_PREFERENCES = "shared_prefernces"
