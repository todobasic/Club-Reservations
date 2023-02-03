package com.example.clubreservations.presentation.event

import androidx.lifecycle.ViewModel
import com.example.clubreservations.data.repository.EventRepository
import com.example.clubreservations.model.Event

class EventDetailsViewModel(
    val eventRepository: EventRepository
) : ViewModel() {

    fun getEventById(id: Long?) : Event? {
        var event: Event? = null
        id?.let { event = eventRepository.getEventById(id) }
        return event
    }

}