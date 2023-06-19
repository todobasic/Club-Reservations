package com.example.clubreservations.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class Reservation(
    val ordNumber: String? = "",
    val title: String? = "",
    val content: String? = "",
    val dateAdded: Date? = Date(),
    val imageUploaded: ByteArray
) : Parcelable {
}

