package com.example.criminalintent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

@Entity
data class Crime (@PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date(),
    var isSolved: Boolean = false,
    var suspect: String = "") {

    val photoFileName
        get() = "IMG_$id.jpg"

    fun getDateCorrectFormat(): String {
        return SimpleDateFormat("EEEE, MMM dd, yyyy", Locale.US).format(date)
    }
    fun getTimeCorrectFormat(): String {
        return SimpleDateFormat("HH:mm", Locale.US).format(date)
    }
}