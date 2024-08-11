package com.example.a15

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val onClick: (model: Model, emptyCont: Model) -> Unit,
    private val listener: ItemChangeListener
) : RecyclerView.Adapter<Adapter.PuzzleViewHolder>() {
    val dataListItem = mutableListOf<Model>()
    private lateinit var emptyCont: Model
    fun loadData(data: List<Model>) {
        dataListItem.clear()
        dataListItem.addAll(data)
        notifyDataSetChanged()
    }


    fun changeItem(currentItem: Model, emptyItem: Model) {
        val currentPosition = currentItem.position
        val emptyPosition = emptyItem.position
        dataListItem[currentPosition] = emptyItem
        dataListItem[emptyPosition] = currentItem
        currentItem.position = emptyPosition
        emptyItem.position = currentPosition
        emptyCont=emptyItem
        if (isFinished()){
            listener.onItemChange(currentItem,emptyItem)
        }
        notifyItemChanged(currentPosition)
        notifyItemChanged(emptyPosition)
    }

    fun isFinished(): Boolean {
        var counter=0
        for (i in 0 until dataListItem.size) {
            when(dataListItem[i].number){
                -1 -> if (dataListItem[i].position == 8) counter += 1
                1-> if (dataListItem[i].position == 0) counter += 1
                2-> if (dataListItem[i].position == 1) counter += 1
                3-> if (dataListItem[i].position == 2) counter += 1
                4-> if (dataListItem[i].position == 3) counter += 1
                5-> if (dataListItem[i].position == 4) counter += 1
                6-> if (dataListItem[i].position == 5) counter += 1
                7-> if (dataListItem[i].position == 6) counter += 1
                8-> if (dataListItem[i].position == 7) counter += 1

            }
        }
        if (counter==9){
            return true
        }
        else{
            return false
        }
    }


    inner class PuzzleViewHolder(
        private val item: View
    ) : RecyclerView.ViewHolder(item) {
        var tvNumber: TextView

        init {
            tvNumber = item.findViewById(R.id.item_tv)
        }

        fun bind(model: Model) {
            tvNumber.text = model.number.toString()

            // Ma'lumotlar ro'yxatida bo'sh konteynerni topamiz.
            val emptyCont: Model? = dataListItem.find { it.number == -1 }

            // Element ko'rinishiga OnClickListener o'rnatamiz.
            item.setOnClickListener {

                if (model != emptyCont) {
                    when (model.position) {
                        0 -> {
                            if (emptyCont.position == 1 || emptyCont.position == 3) {
                                changeItem(model, emptyCont)
                                onClick(model,emptyCont)
                            }
                        }

                        1 -> {
                            if (emptyCont.position == 0 || emptyCont.position == 2 || emptyCont.position == 4) {
                                changeItem(model, emptyCont)
                                onClick(model,emptyCont)
                            }
                        }

                        2 -> {
                            if (emptyCont.position == 1 || emptyCont.position == 5) {
                                changeItem(model, emptyCont)
                                onClick(model,emptyCont)
                            }
                        }

                        3 -> {
                            if (emptyCont.position == 0 || emptyCont.position == 4 || emptyCont.position == 6) {
                                changeItem(model, emptyCont)
                                onClick(model,emptyCont)
                            }
                        }

                        4 -> {
                            if (emptyCont.position == 1 || emptyCont.position == 3 || emptyCont.position == 5 || emptyCont.position == 7) {
                                changeItem(model, emptyCont)
                                onClick(model,emptyCont)
                            }
                        }

                        5 -> {
                            if (emptyCont.position == 2 || emptyCont.position == 4 || emptyCont.position == 8) {
                                changeItem(model, emptyCont)
                                onClick(model,emptyCont)
                            }
                        }

                        6 -> {
                            if (emptyCont.position == 3 || emptyCont.position == 7) {
                                changeItem(model, emptyCont)
                                onClick(model,emptyCont)
                            }
                        }

                        7 -> {
                            if (emptyCont.position == 4 || emptyCont.position == 6 || emptyCont.position == 8) {
                                changeItem(model, emptyCont)
                                onClick(model,emptyCont)
                            }
                        }

                        8 -> {
                            if (emptyCont.position == 5 || emptyCont.position == 7) {
                                changeItem(model, emptyCont)
                                onClick(model,emptyCont)
                            }
                        }
                    }
                }
            }
            item.visibility = if (model.number == -1) {
                View.INVISIBLE
            } else {
                View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuzzleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return PuzzleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataListItem.size
    }

    override fun onBindViewHolder(holder: PuzzleViewHolder, position: Int) {
        dataListItem[position].position = position
        holder.bind(dataListItem[position])
    }
}