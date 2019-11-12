package com.app.jcs.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateExtractUtils {

    fun extractDateFromMonth(date: String): String {
        val subString = date.substring(date.indexOf("-") + 1, date.length)
        return subString.substring(0, subString.indexOf("-"))
    }

    fun getTodayDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val date = Date()
        return dateFormat.format(date)
    }
}
