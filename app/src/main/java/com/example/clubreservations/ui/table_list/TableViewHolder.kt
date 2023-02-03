package com.example.clubreservations.ui.table_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.clubreservations.databinding.ItemTableBinding
import com.example.clubreservations.model.Table

class TableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(table: Table) {
        val binding = ItemTableBinding.bind(itemView)
        binding.itemEventTitle.text = "Table " + table.ordNumber

    }

}