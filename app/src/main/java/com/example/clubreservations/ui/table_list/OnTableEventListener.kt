package com.example.clubreservations.ui.table_list

import com.example.clubreservations.model.Table

interface OnTableEventListener {

    fun onTableSelected(id: Long?)
    fun onTableLongPress(table: Table?): Boolean

}