package com.example.clubreservations.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.clubreservations.model.Table


@Dao
interface TableDao {

    @Insert
    fun save(table: Table)

    @Delete
    fun delete(table: Table)

    @Query("SELECT * FROM tables WHERE id =:id")
    fun getTableById(id: Long): Table?

    @Query("SELECT * FROM tables")
    fun getAllTables(): LiveData<List<Table>>



}