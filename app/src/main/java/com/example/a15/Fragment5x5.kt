package com.example.a15

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a15.databinding.Fragment5x5Binding


class Fragment5x5 : Fragment() {
    private lateinit var binding: Fragment5x5Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = Fragment5x5Binding.inflate(inflater, container, false)
        return binding.root
    }


}