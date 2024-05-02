package com.example.hoteltvapptemplate.useCases

import com.example.hoteltvapptemplate.data.repositories.FileUtils
import java.io.File
import java.io.InputStream
import javax.inject.Inject

class SaveFileFromInputStreamUseCase @Inject constructor(
    private val fileUtils: FileUtils
) {
    suspend fun saveFileFromInputStream(inputStream: InputStream, outputFile: File) {
        fileUtils.saveFileFromInputStream(inputStream, outputFile)
    }
}