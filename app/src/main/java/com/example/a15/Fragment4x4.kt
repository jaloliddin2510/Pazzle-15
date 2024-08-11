package com.example.a15

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a15.databinding.Fragment4x4Binding

class Fragment4x4(context: Context) : Fragment(R.layout.fragment_4x4),ItemChangeListener {
    private lateinit var binding: Fragment4x4Binding
    private val puzzleAdapter by lazy {
        Adapter2(
            { model: Model, emptyModel: Model -> onItemClick(model,emptyModel)},this
        )

    }
    private fun onItemClick(it: Model, emptyModel: Model) {
        puzzleAdapter.changeItem(it, emptyModel)
        puzzleAdapter.changeItem(it, emptyModel)
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
        Model(15),
    )




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = Fragment4x4Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = binding.rvMain4
        rv.adapter = puzzleAdapter
        rv.setHasFixedSize(false)
        puzzleAdapter.loadData(puzzleDataList)
    }

    override fun onItemChange(currentItem: Model, emptyItem: Model) {
        val activity=activity as MainActivity
        activity.finishFragment()
    }
}