package com.example.a15

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter2(
    private val onClick: (model: Model, emptyCont: Model) -> Unit
) : RecyclerView.Adapter<Adapter2.Puzzle4ViewHolder>() {
    private val dataListItem2 = mutableListOf<Model>()

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
        notifyDataSetChanged()
    }



    inner class Puzzle4ViewHolder(
        private val item: View
    ) : RecyclerView.ViewHolder(item) {
        lateinit var tvNumber: TextView

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter2.Puzzle4ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
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