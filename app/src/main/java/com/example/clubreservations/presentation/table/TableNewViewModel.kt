package com.example.clubreservations.presentation.table

import androidx.lifecycle.ViewModel
import com.example.clubreservations.data.repository.EventRepository
import com.example.clubreservations.model.Reservation

class TableNewViewModel(
    val eventRepository: EventRepository
) : ViewModel() {
    fun updateReservations(eventId: Long, reservations: List<Reservation>) {
        eventRepository.updateReservations(eventId, reservations)
    }

    fun getEventReservations(eventId: Long): List<Reservation> {
        return eventRepository.getEventReservations(eventId)
    }
}