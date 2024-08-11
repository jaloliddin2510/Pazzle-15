package com.example.a15

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter2(
    private val onClick: (model: Model, emptyCont: Model) -> Unit,
    private val listener: ItemChangeListener,
) : RecyclerView.Adapter<Adapter2.Puzzle4ViewHolder>() {
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
            listener.onItemChange(currentItem, emptyItem)
        }
        notifyItemChanged(currentPosition)
        notifyItemChanged(emptyPosition)
    }

    fun isFinished(): Boolean {
        var counter = 0
        for (i in 0 until dataListItem2.size) {
            when (dataListItem2[i].number) {
                -1 -> if (dataListItem2[i].position == 15) counter += 1
                1 -> if (dataListItem2[i].position == 0) counter += 1
                2 -> if (dataListItem2[i].position == 1) counter += 1
                3 -> if (dataListItem2[i].position == 2) counter += 1
                4 -> if (dataListItem2[i].position == 3) counter += 1
                5 -> if (dataListItem2[i].position == 4) counter += 1
                6 -> if (dataListItem2[i].position == 5) counter += 1
                7 -> if (dataListItem2[i].position == 6) counter += 1
                8 -> if (dataListItem2[i].position == 7) counter += 1
                9 -> if (dataListItem2[i].position == 8) counter += 1
                10 -> if (dataListItem2[i].position == 9) counter += 1
                11 -> if (dataListItem2[i].position == 10) counter += 1
                12 -> if (dataListItem2[i].position == 11) counter += 1
                13 -> if (dataListItem2[i].position == 12) counter += 1
                14 -> if (dataListItem2[i].position == 13) counter += 1
                15 -> if (dataListItem2[i].position == 14) counter += 1
            }
        }
        if (counter == 16) {
            return true
        } else {
            counter = 0
            return false
        }
    }


    inner class Puzzle4ViewHolder(
        private val item: View,
    ) : RecyclerView.ViewHolder(item) {
        var tvNumber: TextView

        init {
            tvNumber = item.findViewById(R.id.item_tv)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter2.Puzzle4ViewHolder {
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