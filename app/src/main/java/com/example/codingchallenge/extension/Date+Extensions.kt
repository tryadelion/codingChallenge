package com.example.codingchallenge.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Returns a Date as a [String], using a given format and Timezone.
 * @param format the format to transform the Date to. ex: `yyyyMMdd`.
 * @param timeZone a timezone for the transformation, or the default.
 * @return the formatted Date.
 */
fun Date.formatted(format: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    dateFormat.timeZone = timeZone
    return dateFormat.format(this)
}