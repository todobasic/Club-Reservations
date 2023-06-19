package com.example.clubreservations.ui.table_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clubreservations.R
import com.example.clubreservations.model.Reservation

class TableAdapter : RecyclerView.Adapter<TableViewHolder>() {

    private val reservations = mutableListOf<Reservation>()
    var onTableSelectedListener: OnTableEventListener? = null

    fun setTables(reservations: List<Reservation>) {
        this.reservations.clear()
        this.reservations.addAll(reservations)
        this.notifyItemInserted(reservations.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_table, parent, false)
        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val reservation = reservations[position]
        holder.bind(reservation)
        onTableSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onTableSelected(reservation) }
            holder.itemView.setOnLongClickListener { listener.onTableLongPress(reservation) }
        }
    }

    override fun getItemCount(): Int = reservations.count()


}