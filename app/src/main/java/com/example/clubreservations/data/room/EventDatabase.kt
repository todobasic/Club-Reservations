package com.example.clubreservations.data.room

import android.content.Context
import android.media.metrics.Event
import androidx.room.*
import com.example.clubreservations.data.EventDao


@Database(
    entities = [com.example.clubreservations.model.Event::class],
    version = 1,
    exportSchema = false
)

abstract class EventDatabase : RoomDatabase() {

    abstract fun getEventDao(): EventDao

    companion object {
        private const val databaseName = "biljeskeDb"

        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getDatabase(context: Context): EventDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): EventDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                EventDatabase::class.java,
                databaseName
            )
                .allowMainThreadQueries()
                .build()
        }
    }


}