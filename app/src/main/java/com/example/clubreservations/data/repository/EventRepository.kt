package com.example.clubreservations.data.repository

import androidx.lifecycle.LiveData
import com.example.clubreservations.model.Event
import com.example.clubreservations.model.Reservation

interface EventRepository {

    fun save(event: Event)
    fun delete(event: Event)
    fun getEventById(id: Long): Event?
    fun getAllEvents(): LiveData<List<Event>>
    fun updateReservations(eventId: Long, reservations: List<Reservation>)
    fun getEventReservations(eventId: Long): List<Reservation>
    fun updateReservedTables(eventId: Long, reservedTables: List<String>)
    fun getEventReservedTables(eventId: Long): List<String>

}