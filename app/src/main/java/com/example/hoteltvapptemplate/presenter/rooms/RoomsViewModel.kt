package com.example.hoteltvapptemplate.presenter.rooms

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.hoteltvapptemplate.R
import javax.inject.Inject

class RoomsViewModel @Inject constructor(
    private val appContext: Context
): ViewModel() {
    private val roomsList: List<RoomInfo> = loadRoomsData()

    private fun loadRoomsData(): List<RoomInfo> {
        return listOf(
            RoomInfo(
                appContext.getString(R.string.room_1),
                R.drawable.hotel_room
            ),

            RoomInfo(
                appContext.getString(R.string.room_2),
                R.drawable.hotel_room
            ),

            RoomInfo(
                appContext.getString(R.string.room_3),
                R.drawable.hotel_room
            ),

            RoomInfo(
                appContext.getString(R.string.room_4),
                R.drawable.hotel_room
            ),

            RoomInfo(
                appContext.getString(R.string.room_5),
                R.drawable.hotel_room
            ),

            RoomInfo(
                appContext.getString(R.string.room_6),
                R.drawable.hotel_room
            )
        )
    }

    fun getRoomsData() = roomsList
}
