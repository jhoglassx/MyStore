package com.js.mystore.utils

import androidx.room.TypeConverter
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

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
}
