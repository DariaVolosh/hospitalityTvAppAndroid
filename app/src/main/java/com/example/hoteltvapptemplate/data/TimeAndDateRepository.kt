package com.example.hoteltvapptemplate.data

import com.instacart.truetime.time.TrueTimeImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import javax.inject.Inject

class TimeAndDateRepository @Inject constructor() {
    private val trueTime = TrueTimeImpl()

    fun getTimeAndDateFlow(): Flow<Pair<String, String>> = flow {
        var oldTime = ""
        while (true) {
            val trueTime = trueTime.now()
            val time = getTime(trueTime)
            val date = getDate(trueTime)
            if (time != oldTime) {
                oldTime = time
                emit(Pair(time, date))
            }
            delay(1000)
        }
    }.flowOn(Dispatchers.IO)

    private fun getTime(date: Date): String {
        val sdf = SimpleDateFormat("HH:mm")
        sdf.timeZone = TimeZone.getTimeZone("GMT+4")
        return sdf.format(date)
    }
    private fun getDate(date: Date): String {
        val sdf = SimpleDateFormat("EEEE, dd-MM-yyyy")
        sdf.timeZone = TimeZone.getTimeZone("GMT+4")
        return sdf.format(date)
    }
}