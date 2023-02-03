package com.example.clubreservations.presentation.table

import androidx.lifecycle.ViewModel
import com.example.clubreservations.data.repository.TableRepository
import com.example.clubreservations.model.Table

class TableDetailsViewModel(
    val tableRepository: TableRepository
) : ViewModel() {

    fun getTableById(id: Long?): Table? {
        var table: Table? = null
        id?.let { table = tableRepository.getTableById(id) }
        return table
    }

}