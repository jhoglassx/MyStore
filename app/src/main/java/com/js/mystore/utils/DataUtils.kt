package com.js.mystore.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Date

object DateUtils {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.getTime()
    }

    fun date(): Date? {
        return Date.from(ZonedDateTime.now(ZoneId.of("Etc/UTC")).toInstant())
    }

    fun convertStringToDate(date: String): Date? {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        return dateFormat.parse(date)
    }
}
