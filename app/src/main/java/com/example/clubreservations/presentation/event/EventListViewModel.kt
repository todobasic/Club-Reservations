package com.example.clubreservations.presentation.event

import androidx.lifecycle.ViewModel
import com.example.clubreservations.data.repository.EventRepository
import com.example.clubreservations.model.Event
import com.example.clubreservations.ui.event_list.EventListFragment
import kotlin.properties.ReadOnlyProperty

class EventListViewModel(val eventRepository: EventRepository) : ViewModel() {
    val events = eventRepository.getAllEvents()

    fun delete(event: Event) {
        eventRepository.delete(event)
    }

}