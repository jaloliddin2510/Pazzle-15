package com.example.a15

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val onClick: (model: Model, emptyCont: Model) -> Unit
) : RecyclerView.Adapter<Adapter.PuzzleViewHolder>() {
    private val dataListItem = mutableListOf<Model>()

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
        notifyDataSetChanged()
    }



    inner class PuzzleViewHolder(
        private val item: View
    ) : RecyclerView.ViewHolder(item) {
        lateinit var tvNumber: TextView

        init {
            tvNumber = item.findViewById(R.id.item_tv)
        }

        fun bind(model: Model) {
            // TextView matnini modelning raqamiga o'rnatamiz.
            tvNumber.text = model.number.toString()

            // Ma'lumotlar ro'yxatida bo'sh konteynerni topamiz.
            val emptyCont: Model? = dataListItem.find { it.number == -1 }

            // Element ko'rinishiga OnClickListener o'rnatamiz.
            item.setOnClickListener {
                // Agar bo'sh konteyner mavjud bo'lmasa va joriy element bo'sh konteyner bo'lmasa,
                // joriy element va bo'sh konteynerni almashtiramiz.
                if (emptyCont != null && model != emptyCont) {
                    when (model.position) {
                        0 -> {
                            if (emptyCont.position == 1 || emptyCont.position == 3) {
                                changeItem(model, emptyCont)
                            }
                        }

                        1 -> {
                            if (emptyCont.position == 0 || emptyCont.position == 2 || emptyCont.position == 4) {
                                changeItem(model, emptyCont)
                            }
                        }

                        2 -> {
                            if (emptyCont.position == 1 || emptyCont.position == 5) {
                                changeItem(model, emptyCont)
                            }
                        }

                        3 -> {
                            if (emptyCont.position == 0 || emptyCont.position == 4 || emptyCont.position == 6) {
                                changeItem(model, emptyCont)
                            }
                        }

                        4 -> {
                            if (emptyCont.position == 1 || emptyCont.position == 3 || emptyCont.position == 5 || emptyCont.position == 7) {
                                changeItem(model, emptyCont)
                            }
                        }

                        5 -> {
                            if (emptyCont.position == 2 || emptyCont.position == 4 || emptyCont.position == 8) {
                                changeItem(model, emptyCont)
                            }
                        }

                        6 -> {
                            if (emptyCont.position == 3 || emptyCont.position == 7) {
                                changeItem(model, emptyCont)
                            }
                        }

                        7 -> {
                            if (emptyCont.position == 4 || emptyCont.position == 6 || emptyCont.position == 8) {
                                changeItem(model, emptyCont)
                            }
                        }

                        8 -> {
                            if (emptyCont.position == 5 || emptyCont.position == 7) {
                                changeItem(model, emptyCont)
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