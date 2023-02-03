package com.example.clubreservations.presentation.table

import androidx.lifecycle.ViewModel
import com.example.clubreservations.data.repository.TableRepository
import com.example.clubreservations.model.Table

class TableListViewModel(val tableRepository: TableRepository) : ViewModel() {
    val tables = tableRepository.getAllTables()

    fun delete(table: Table) {
        tableRepository.delete(table)
    }
}