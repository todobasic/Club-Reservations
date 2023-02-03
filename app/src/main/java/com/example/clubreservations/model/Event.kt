package com.example.clubreservations.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true)
    public var id: Long = 0,
    @ColumnInfo(name= "title")
    val title: String
) {
}