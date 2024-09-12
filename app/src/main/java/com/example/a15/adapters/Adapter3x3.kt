package com.example.a15.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a15.content.ItemChangeListener
import com.example.a15.content.Model
import com.example.a15.R

class Adapter3x3(
    private val onClick: (model: Model, emptyCont: Model) -> Unit,
    private val listener: ItemChangeListener
) : RecyclerView.Adapter<Adapter3x3.PuzzleViewHolder>() {
    val dataListItem = mutableListOf<Model>()
    private lateinit var emptyCont: Model
    @SuppressLint("NotifyDataSetChanged")
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
            listener.onItemChange()
        }
        notifyItemChanged(currentPosition)
        notifyItemChanged(emptyPosition)
    }

    private fun isFinished(): Boolean {
        var counter = 0
        for (i in 0 until dataListItem.size) {
            if (dataListItem[i].number == -1 && dataListItem[i].position == 8) {
                counter += 1
            } else if (dataListItem[i].number == i + 1) {
                counter += 1
            }
        }
        return counter == 9
    }


    inner class PuzzleViewHolder(
        private val item: View
    ) : RecyclerView.ViewHolder(item) {
        private var tvNumber: TextView = item.findViewById(R.id.item_tv)

        fun bind(model: Model) {
            tvNumber.text = model.number.toString()

            val emptyCont: Model? = dataListItem.find { it.number == -1 }

            item.setOnClickListener {

                if (emptyCont!=null && model != emptyCont) {
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
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_3x3, parent, false)
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