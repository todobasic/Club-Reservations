package com.example.clubreservations.ui.event_list

import com.example.clubreservations.model.Event

interface OnEventEventListener {

    fun onEventSelected(id: Long?)
    fun onEventLongPress(event: Event): Boolean

}