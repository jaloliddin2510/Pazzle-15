package com.example.a15

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a15.databinding.FragmentFinishBinding

class FinishFragment : Fragment() {
    private lateinit var binding: FragmentFinishBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentFinishBinding.inflate(layoutInflater)
        return binding.root
    }


    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.refreshGame.setOnClickListener {
            val fragment3x3=Fragment3x3()
            fragment3x3.shuffledList()
        }
    }

}