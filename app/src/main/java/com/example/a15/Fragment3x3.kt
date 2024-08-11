package com.example.a15

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a15.databinding.Fragment3x3Binding

class Fragment3x3 : Fragment(R.layout.fragment_3x3), ItemChangeListener {
    private lateinit var binding: Fragment3x3Binding
    private val puzzleAdapter by lazy {
        Adapter({ model: Model, emptyModel: Model -> onItemClick(model, emptyModel) }, this)
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
        Model(),
    )
    override fun onItemChange(currentItem: Model, emptyItem: Model) {
        val activity=activity as MainActivity
        activity.finishFragment()
    }
    private fun onItemClick(it: Model, emptyModel: Model) {
        puzzleAdapter.changeItem(it, emptyModel)
        puzzleAdapter.changeItem(it,emptyModel)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = Fragment3x3Binding.inflate(layoutInflater)
        return binding.root
    }
    fun shuffledList(){
        puzzleAdapter.loadData(puzzleDataList.shuffled())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv = binding.rvMain
        rv.layoutManager = GridLayoutManager(requireContext(), 3)
        rv.adapter = puzzleAdapter
        puzzleAdapter.loadData(puzzleDataList.shuffled())
    }
}