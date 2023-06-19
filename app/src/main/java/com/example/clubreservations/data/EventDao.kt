package com.example.clubreservations.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.clubreservations.model.Event
import com.example.clubreservations.model.Reservation

@Dao
interface EventDao {

    @Insert
    fun save(event: Event)

    @Delete
    fun delete(event: Event)

    @Query("SELECT * FROM events WHERE id =:id")
    fun getEventById(id: Long): Event?

    @Query("SELECT * FROM events")
    fun getAllEvents(): LiveData<List<Event>>

    @Query("UPDATE events SET reservations = :reservations WHERE id =:eventId")
    fun updateReservations(eventId: Long, reservations: List<Reservation>)

}