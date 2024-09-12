package com.example.a15.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a15.content.ItemChangeListener
import com.example.a15.content.Model
import com.example.a15.R

class Adapter4x4(
    private val onClick: (model: Model, emptyCont: Model) -> Unit,
    private val listener: ItemChangeListener,
) : RecyclerView.Adapter<Adapter4x4.Puzzle4ViewHolder>() {
    val dataListItem2 = mutableListOf<Model>()
    private lateinit var emptyCont: Model

    fun loadData(data: List<Model>) {
        dataListItem2.clear()
        dataListItem2.addAll(data)
        notifyDataSetChanged()
    }


    fun changeItem(currentItem: Model, emptyItem: Model) {
        val currentPosition = currentItem.position
        val emptyPosition = emptyItem.position
        dataListItem2[currentPosition] = emptyItem
        dataListItem2[emptyPosition] = currentItem
        currentItem.position = emptyPosition
        emptyItem.position = currentPosition
        emptyCont = emptyItem
        if (isFinished()) {
            listener.onItemChange()
        }
        notifyItemChanged(currentPosition)
        notifyItemChanged(emptyPosition)
    }

    fun isFinished(): Boolean {
        var counter = 0
        for (i in 0 until dataListItem2.size) {
            if (dataListItem2[i].number == -1 && dataListItem2[i].position == 15) {
                counter += 1
            } else if (dataListItem2[i].number == i + 1) {
                counter += 1
            }
        }
        return counter == 16
    }


    inner class Puzzle4ViewHolder(
        private val item: View,
    ) : RecyclerView.ViewHolder(item) {
        var tvNumber: TextView

        init {
            tvNumber = item.findViewById(R.id.item_tv_4x4)
        }

        fun bind(model: Model) {

            tvNumber.text = model.number.toString()


            val emptyCont: Model? = dataListItem2.find { it.number == -1 }
            item.setOnClickListener {

                if (emptyCont != null && model != emptyCont) {
                    when (model.position) {
                        0 -> {
                            if (emptyCont.position == 1 || emptyCont.position == 4) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        1 -> {
                            if (emptyCont.position == 0 || emptyCont.position == 2 || emptyCont.position == 5) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        2 -> {
                            if (emptyCont.position == 1 || emptyCont.position == 3 || emptyCont.position == 6) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        3 -> {
                            if (emptyCont.position == 2 || emptyCont.position == 7) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        4 -> {
                            if (emptyCont.position == 0 || emptyCont.position == 5 || emptyCont.position == 8) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        5 -> {
                            if (emptyCont.position == 1 || emptyCont.position == 4 || emptyCont.position == 6 || emptyCont.position == 9) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        6 -> {
                            if (emptyCont.position == 2 || emptyCont.position == 5 || emptyCont.position == 7 || emptyCont.position == 10) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        7 -> {
                            if (emptyCont.position == 3 || emptyCont.position == 6 || emptyCont.position == 11) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        8 -> {
                            if (emptyCont.position == 4 || emptyCont.position == 9 || emptyCont.position == 12) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        9 -> {
                            if (emptyCont.position == 5 || emptyCont.position == 8 || emptyCont.position == 10 || emptyCont.position == 13) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        10 -> {
                            if (emptyCont.position == 6 || emptyCont.position == 9 || emptyCont.position == 11 || emptyCont.position == 14) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        11 -> {
                            if (emptyCont.position == 7 || emptyCont.position == 10 || emptyCont.position == 15) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        12 -> {
                            if (emptyCont.position == 8 || emptyCont.position == 13) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        13 -> {
                            if (emptyCont.position == 9 || emptyCont.position == 12 || emptyCont.position == 14) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        14 -> {
                            if (emptyCont.position == 10 || emptyCont.position == 13 || emptyCont.position == 15) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
                            }
                        }

                        15 -> {
                            if (emptyCont.position == 11 || emptyCont.position == 14) {
                                changeItem(model, emptyCont)
                                onClick(model, emptyCont)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Puzzle4ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_4x4, parent, false)
        return Puzzle4ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Puzzle4ViewHolder, position: Int) {
        dataListItem2[position].position = position
        holder.bind(dataListItem2[position])
    }

    override fun getItemCount(): Int {
        return dataListItem2.size
    }

}