package com.example.a15

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.a15.databinding.Fragment3x3Binding
import com.example.a15.databinding.Fragment4x4Binding

class Fragment4x4: Fragment(R.layout.fragment_4x4) {
    private lateinit var binding: Fragment4x4Binding

    private val puzzleAdapter by lazy {
        Adapter { model: Model, emptyModel: Model ->
            onItemClick(model, emptyModel)
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
        Model(),
    )

    fun isFinished(): Boolean {
        for (i in 0 until puzzleDataList.size) {
            if (puzzleDataList[i].number != i + 1) {
                return false
            }
        }

        Toast.makeText(requireContext(), "Puzzle finished!", Toast.LENGTH_SHORT).show()
        return true
    }



    private fun onItemClick(it: Model, emptyModel: Model) {
        println("onItemClick:")
        if (isFinished()){
            Toast.makeText(requireContext(), "zvXcvxzcvCXvXZCv", Toast.LENGTH_SHORT).show()
        }
        puzzleAdapter.changeItem(it, emptyModel)
        Toast.makeText(requireContext(), "$it | $emptyModel  dfjsdfdkfjnsdf", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Fragment4x4Binding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = view.findViewById<RecyclerView>(R.id.rv_main)
        rv.adapter = puzzleAdapter
        rv.setHasFixedSize(false)
        puzzleAdapter.loadData(puzzleDataList.shuffled())
        isFinished()

    }
}