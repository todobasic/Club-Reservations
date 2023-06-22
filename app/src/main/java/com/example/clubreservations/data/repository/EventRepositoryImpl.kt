package com.example.clubreservations.data.repository

import androidx.lifecycle.LiveData
import com.example.clubreservations.data.EventDao
import com.example.clubreservations.model.Event
import com.example.clubreservations.model.Reservation

class EventRepositoryImpl(val eventDao: EventDao) : EventRepository {
    override fun save(event: Event) = eventDao.save(event)
    override fun delete(event: Event) = eventDao.delete(event)
    override fun getEventById(id: Long): Event? = eventDao.getEventById(id)
    override fun getAllEvents(): LiveData<List<Event>> = eventDao.getAllEvents()
    override fun updateReservations(eventId: Long, reservations: List<Reservation>) {
        eventDao.updateReservations(eventId, reservations)
    }
    override fun getEventReservations(eventId: Long): List<Reservation> {
        return eventDao.getEventById(eventId)?.reservations ?: listOf()
    }
    override fun updateReservedTables(eventId: Long, reservedTables: List<String>) {
        eventDao.updateReservedTables(eventId, reservedTables)
    }
    override fun getEventReservedTables(eventId: Long): List<String> {
        return eventDao.getEventById(eventId)?.takenTables ?: listOf()
    }

}