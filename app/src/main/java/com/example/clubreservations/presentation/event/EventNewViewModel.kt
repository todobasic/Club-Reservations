package com.example.clubreservations.presentation.event

import androidx.lifecycle.ViewModel
import com.example.clubreservations.data.repository.EventRepository
import com.example.clubreservations.model.Event

class EventNewViewModel(
    val eventRepository: EventRepository
) : ViewModel() {
    fun save(title: String) {
        eventRepository.save(Event(title = title))
    }
}