package com.example.clubreservations.data.repository

import androidx.lifecycle.LiveData
import com.example.clubreservations.data.EventDao
import com.example.clubreservations.model.Event

class EventRepositoryImpl(val eventDao: EventDao) : EventRepository {
    override fun save(event: Event) = eventDao.save(event)
    override fun delete(event: Event) = eventDao.delete(event)
    override fun getEventById(id: Long): Event? = eventDao.getEventById(id)
    override fun getAllEvents(): LiveData<List<Event>> = eventDao.getAllEvents()
}