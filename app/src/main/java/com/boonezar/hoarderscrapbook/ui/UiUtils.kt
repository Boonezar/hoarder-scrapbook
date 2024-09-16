package com.boonezar.hoarderscrapbook.ui

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
object UiUtils {
    private const val DATE_FORMAT = "MM/dd/yyyy"

    fun convertMillisToDate(millis: Long?): String {
        if (millis == null) return ""
        return getFormatter().format(Date(millis))
    }

    fun convertDateToMillis(dateString: String): Long? {
        val date = getFormatter().parse(dateString)
        return date?.time
    }

    fun getDateStringOfToday(): String = getFormatter().format(Date())

    private fun getFormatter() =  SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
}