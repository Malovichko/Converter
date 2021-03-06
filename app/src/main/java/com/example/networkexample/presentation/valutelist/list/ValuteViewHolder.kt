package com.example.networkexample.presentation.valutelist.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.networkexample.R
import com.example.networkexample.domain.model.Valute


class ValuteViewHolder(rootView: View, private val onClick: (valute: Valute) -> Unit) :
    RecyclerView.ViewHolder(
        rootView
    ) {

    private lateinit var textViewValuteName: TextView
    private lateinit var textViewValuteCharCode: TextView

    fun bind(valute: Valute) {
        textViewValuteName = itemView.findViewById(R.id.text_view_valute_name)
        textViewValuteCharCode = itemView.findViewById(R.id.text_view_valute_charcode)

        textViewValuteName.text = valute.name
        textViewValuteCharCode.text = valute.charCode

        itemView.setOnClickListener {
            onClick(
                valute
            )
        }
    }
}