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
    }
    private val prefs = Shared.getSharedPrefs(context)
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
        Model(),
    )


    fun isFinished(): Boolean {
        var counter=0
        for (i in 0 until puzzleAdapter.dataListItem2.size) {
            when(puzzleAdapter.dataListItem2[i].number){
                -1 -> if (puzzleAdapter.dataListItem2[i].position == 15) counter += 1
                1-> if (puzzleAdapter.dataListItem2[i].position == 0) counter += 1
                2-> if (puzzleAdapter.dataListItem2[i].position == 1) counter += 1
                3-> if (puzzleAdapter.dataListItem2[i].position == 2) counter += 1
                4-> if (puzzleAdapter.dataListItem2[i].position == 3) counter += 1
                5-> if (puzzleAdapter.dataListItem2[i].position == 4) counter += 1
                6-> if (puzzleAdapter.dataListItem2[i].position == 5) counter += 1
                7-> if (puzzleAdapter.dataListItem2[i].position == 6) counter += 1
                8-> if (puzzleAdapter.dataListItem2[i].position == 7) counter += 1
                9-> if (puzzleAdapter.dataListItem2[i].position == 8) counter += 1
                10-> if (puzzleAdapter.dataListItem2[i].position == 9) counter += 1
                11-> if (puzzleAdapter.dataListItem2[i].position == 10) counter += 1
                12-> if (puzzleAdapter.dataListItem2[i].position == 11) counter += 1
                13-> if (puzzleAdapter.dataListItem2[i].position == 12) counter += 1
                14-> if (puzzleAdapter.dataListItem2[i].position == 13) counter += 1
                15-> if (puzzleAdapter.dataListItem2[i].position == 14) counter += 1
            }
        }
        if (counter==16){
            return true
        }
        else{
            counter=0
            return false
        }
    }

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
        puzzleAdapter.loadData(puzzleDataList.shuffled())
    }

    override fun onItemChange(currentItem: Model, emptyItem: Model) {
        if (isFinished()){
            println("::::::::::::::::::::::::::::Finish")
        }
    }
}