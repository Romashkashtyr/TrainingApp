package com.example.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

fun getTodayDate(): String {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return format.format(Date())
}

@RequiresApi(Build.VERSION_CODES.O)
fun getLocalDate(): String {
    return LocalDate.now().toString()
}
