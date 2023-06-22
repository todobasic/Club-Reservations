package com.example.clubreservations.presentation.event

import androidx.lifecycle.ViewModel
import com.example.clubreservations.data.repository.EventRepository
import com.example.clubreservations.model.Event
import com.example.clubreservations.model.Reservation

class EventDetailsViewModel(
    val eventRepository: EventRepository
) : ViewModel() {
    lateinit var tempEvent: Event
    private var tempEventId: Long = -1

    fun getEventById(id: Long?): Event? {
        var event: Event? = null
        id?.let {
            event = eventRepository.getEventById(id)
            if (event != null) {
                tempEvent = event!!
                tempEventId = id
            }
        }
        return event
    }

    fun updateReservations(reservations: List<Reservation>) {
        eventRepository.updateReservations(tempEventId, reservations)
    }

    fun updateReservedTables(reservedTables: List<String>) {
        eventRepository.updateReservedTables(tempEventId, reservedTables)
    }


}