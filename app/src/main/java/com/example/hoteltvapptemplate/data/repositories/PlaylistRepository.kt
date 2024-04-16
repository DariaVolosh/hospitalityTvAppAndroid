package com.example.hoteltvapptemplate.data.repositories

import com.example.hoteltvapptemplate.data.IptvChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.inject.Inject

interface PlaylistRepository {
    suspend fun readPlaylistContent(url: String): List<IptvChannel> {
        val connection = getPlaylistInputStream(url)
        val reader = BufferedReader(InputStreamReader(connection))

        return withContext(Dispatchers.IO) {
            val list = reader.readLines()
            parsePlaylistContent(list)
        }
    }
    private suspend fun getPlaylistInputStream(url: String) =
        withContext(Dispatchers.IO) {
            getPlaylistConnection(url).getInputStream()
        }

    private suspend fun getPlaylistConnection(url: String) =
        withContext(Dispatchers.IO) {
            URL(url).openConnection()
        }

    suspend fun getPlaylistFormat(url: String): String =
        withContext(Dispatchers.IO) {
            val connection = getPlaylistConnection(url)
            connection.contentType
        }

    fun parsePlaylistContent(lines: List<String>): List<IptvChannel>
}

class M3U8PlaylistRepository @Inject constructor(): PlaylistRepository {
    override fun parsePlaylistContent(lines: List<String>): List<IptvChannel> {
        val iptvChannels = mutableListOf<IptvChannel>()
        var iptvChannel = IptvChannel("", "", "")
        for (line in lines) {
            if (line == "#EXTM3U") continue
            if (iptvChannel.tvLogoUrl == "") {
                var commaIndex = line.indexOf(",")
                iptvChannel.tvLogoUrl = line.substring(line.indexOf("tvg-logo=") + 10..<commaIndex-1)
                iptvChannel.channelName = line.substring(commaIndex + 1..<line.length)
            } else {
                iptvChannel.channelURL = line
                iptvChannels.add(iptvChannel)
                iptvChannel = IptvChannel("", "", "")
            }
        }
        return iptvChannels
    }
}

class M3UPlaylistRepository @Inject constructor(): PlaylistRepository {
    override fun parsePlaylistContent(lines: List<String>): List<IptvChannel> {
        val iptvChannels = mutableListOf<IptvChannel>()
        var iptvChannel = IptvChannel("", "", "")
        for (line in lines) {
            if (line == "#EXTM3U") continue
            if (iptvChannel.channelName == "") {
                if (line.contains(".")) {
                    iptvChannel.channelName = line.substring(line.indexOf("."), line.length).trim()
                } else {
                    iptvChannel.channelName = line.substring(line.indexOf(","), line.length).trim()
                }
            } else {
                iptvChannel.channelURL = line
                iptvChannels.add(iptvChannel)
                iptvChannel = IptvChannel("", "", "")
            }
        }
        return iptvChannels
    }
}