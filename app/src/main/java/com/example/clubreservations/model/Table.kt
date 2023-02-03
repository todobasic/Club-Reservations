package com.example.clubreservations.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "tables")
data class Table(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "ordNumber")
    val ordNumber: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "dateAdded")
    val dateAdded: Date,
    //@ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    //val imageUploaded: ByteArray
    ) {
}

