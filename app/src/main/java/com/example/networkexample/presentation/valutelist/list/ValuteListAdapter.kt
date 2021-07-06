package com.example.networkexample.presentation.valutelist.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.networkexample.R
import com.example.networkexample.domain.model.Valute

class ValuteListAdapter constructor(
    private val itemList: List<Valute>,
    private val onClick: (valute: Valute) -> Unit
) : RecyclerView.Adapter<ValuteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValuteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_valute, parent, false)
        return ValuteViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ValuteViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}