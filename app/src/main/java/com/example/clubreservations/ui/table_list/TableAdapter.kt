package com.example.clubreservations.ui.table_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clubreservations.R
import com.example.clubreservations.model.Table

class TableAdapter: RecyclerView.Adapter<TableViewHolder>() {

    private val tables = mutableListOf<Table>()
    var onTableSelectedListener: OnTableEventListener? = null

    fun setTables(tables: List<Table>) {
        this.tables.clear()
        this.tables.addAll(tables)
        this.notifyItemInserted(tables.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val table = tables[position]
        holder.bind(table)
        onTableSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onTableSelected(table.id) }
            holder.itemView.setOnLongClickListener { listener.onTableLongPress(table) }
        }
    }

    override fun getItemCount(): Int = tables.count()


}