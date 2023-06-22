package com.example.clubreservations.ui.table_list

import com.example.clubreservations.model.Reservation

interface OnTableEventListener {

    fun onTableSelected(reservation: Reservation)
    fun onTableLongPress(reservation: Reservation): Boolean

}