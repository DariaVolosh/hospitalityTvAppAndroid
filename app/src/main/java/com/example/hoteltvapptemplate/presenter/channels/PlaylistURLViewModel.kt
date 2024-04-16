package com.example.hoteltvapptemplate.presenter.channels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteltvapptemplate.data.IptvChannel
import com.example.hoteltvapptemplate.useCases.ReadPlaylistContentByURLUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaylistURLViewModel @Inject constructor(
    private val readPlaylistUseCase: ReadPlaylistContentByURLUseCase
) : ViewModel() {
    val channels = MutableLiveData<List<IptvChannel>>()

    init {
        viewModelScope.launch {
            val playlistUrl = "http://tinyurl.com/4daf6e37"
            val playlistType = getPlaylistType(playlistUrl)
            readPlaylistContent(playlistUrl, playlistType == "application/x-mpegurl")
        }
    }

    private fun readPlaylistContent(url: String, m3u8: Boolean) {
        viewModelScope.launch {
            if (m3u8) channels.postValue(readPlaylistUseCase.readM3U8PlaylistContentByURL(url))
            else channels.postValue(readPlaylistUseCase.readM3UPlaylistContentByURL(url))
        }
    }

    private suspend fun getPlaylistType(url: String) = readPlaylistUseCase.getPlaylistType(url)
}