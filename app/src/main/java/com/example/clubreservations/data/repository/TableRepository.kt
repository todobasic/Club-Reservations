package com.example.clubreservations.data.repository

import androidx.lifecycle.LiveData
import com.example.clubreservations.model.Table

interface TableRepository {

    fun save(table: Table)
    fun delete(table: Table)
    fun getTableById(id: Long): Table?
    fun getAllTables(): LiveData<List<Table>>

}