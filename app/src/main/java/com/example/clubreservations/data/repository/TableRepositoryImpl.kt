package com.example.clubreservations.data.repository

import androidx.lifecycle.LiveData
import com.example.clubreservations.data.TableDao
import com.example.clubreservations.model.Table

class TableRepositoryImpl(val tableDao: TableDao) : TableRepository {
    override fun save(table: Table) = tableDao.save(table)
    override fun delete(table: Table) = tableDao.delete(table)
    override fun getTableById(id: Long): Table? = tableDao.getTableById(id)
    override fun getAllTables(): LiveData<List<Table>> = tableDao.getAllTables()
}