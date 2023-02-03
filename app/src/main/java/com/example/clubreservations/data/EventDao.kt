package com.example.clubreservations.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.clubreservations.model.Event

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
}