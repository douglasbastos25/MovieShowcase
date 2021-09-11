package com.github.douglasbastos25.movieshowcase.core

import android.os.Build
import com.github.douglasbastos25.movieshowcase.core.Constants.TMDB_DATE_PATTERN
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.util.*

object Constants {
    const val TMDB_DATE_PATTERN = "yyyy-MM-dd"
}

fun String.parseAndGetYear(default: String): String {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        try {
            LocalDate.parse(this).year.toString()
        } catch (e: DateTimeParseException) {
            default
        }
    } else {
        try {
            val simpleDateFormat = SimpleDateFormat(TMDB_DATE_PATTERN, Locale.ENGLISH)
            simpleDateFormat.parse(this)?.year.toString()
        } catch (e: ParseException) {
            default
        }
    }
}
