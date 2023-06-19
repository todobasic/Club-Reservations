package com.example.clubreservations.data.room

import androidx.room.TypeConverter
import com.example.clubreservations.model.Reservation
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.GsonBuilder
import java.util.Date

class RoomTypeConverters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toReservationList(data: String): List<Reservation> {
        val listType = object : TypeToken<List<Reservation>>() {}.type
        return GsonBuilder().create().fromJson(data, listType)
    }

    @TypeConverter
    fun toReservationString(reservations: List<Reservation>): String {
        return GsonBuilder().create().toJson(reservations)
    }

    @TypeConverter
    fun toIntList(data: String): List<Int> {
        val listType = object : TypeToken<List<Int>>() {}.type
        return GsonBuilder().create().fromJson(data, listType)
    }

    @TypeConverter
    fun toIntString(intList: List<Int>): String {
        return GsonBuilder().create().toJson(intList)
    }
}