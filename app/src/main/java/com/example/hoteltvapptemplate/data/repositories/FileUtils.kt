package com.example.hoteltvapptemplate.data.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

class FileUtils @Inject constructor(

) {
    suspend fun saveFileFromInputStream(inputStream: InputStream, outputFile: File) {
        withContext(Dispatchers.IO) {
            val outputStream = FileOutputStream(outputFile)
            var read = 0

            val buffer = ByteArray(4090)
            var bytesRead: Int

            try {
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    read += bytesRead
                    outputStream.write(buffer, 0, bytesRead)
                    Log.i("LOL", (read / 1000000).toString())
                }
            } finally {
                inputStream.close()
                outputStream.close()
            }
        }
    }
}