package com.example.clubreservations.data.repository

import androidx.lifecycle.LiveData
import com.example.clubreservations.model.Event

interface EventRepository {

    fun save(event: Event)
    fun delete(event: Event)
    fun getEventById(id: Long): Event?
    fun getAllEvents(): LiveData<List<Event>>

}