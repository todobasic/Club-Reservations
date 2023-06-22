package com.example.clubreservations.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "reservations")
    val reservations: List<Reservation>? = listOf<Reservation>(),
    @ColumnInfo(name = "taken_tables")
    val takenTables: List<String>? = listOf<String>()
)