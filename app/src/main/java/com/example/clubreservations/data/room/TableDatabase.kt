package com.example.clubreservations.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.clubreservations.data.TableDao

@Database(
    entities = [com.example.clubreservations.model.Table::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(TableConverters::class)
abstract class TableDatabase : RoomDatabase() {

    abstract fun getTableDao(): TableDao

    companion object {
        private const val databaseName = "tablesDb"

        @Volatile
        private var INSTANCE: TableDatabase? = null

        fun getDatabase(context: Context): TableDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): TableDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                TableDatabase::class.java,
                databaseName
            )
                .allowMainThreadQueries()
                .build()
        }
    }

}