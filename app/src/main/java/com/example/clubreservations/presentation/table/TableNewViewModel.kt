package com.example.clubreservations.presentation.table

import androidx.lifecycle.ViewModel
import com.example.clubreservations.data.repository.TableRepository
import com.example.clubreservations.model.Table
import java.util.Date

class TableNewViewModel(
    val tableRepository: TableRepository
) : ViewModel() {
    fun save(ordNumber: String, title: String, content: String, dateAdded: Date) {
        tableRepository.save(Table(0, ordNumber, title, content, dateAdded))
    }
}