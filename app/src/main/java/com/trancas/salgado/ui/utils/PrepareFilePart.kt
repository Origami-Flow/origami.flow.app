package com.trancas.salgado.ui.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

fun prepareFilePart(context: Context, uri: Uri): MultipartBody.Part? {
    return try {
        val fileBytes = compressImage(context, uri)
        fileBytes?.takeIf { it.isNotEmpty() }?.let { bytes ->
            val fileName = getFileNameFromUri(context, uri)
            val requestBody = bytes.toRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData(
                "imagem",
                fileName,
                requestBody
            )
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun String?.normalizeImageUrl(): String {
    if (this.isNullOrBlank()) {
        return ""
    }
    var updatedUrl = this
    if (updatedUrl.startsWith("http://")) {
        updatedUrl = updatedUrl.replaceFirst("http://", "https://")
    }

    return updatedUrl
}

private fun getFileNameFromUri(context: Context, uri: Uri): String {
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.moveToFirst()
    val fileName = cursor?.getString(cursor.getColumnIndexOrThrow(android.provider.OpenableColumns.DISPLAY_NAME))
    cursor?.close()
    return fileName ?: "image.jpg"
}

fun criarRequestBody(valor: String): RequestBody {
    return valor.toRequestBody("text/plain".toMediaTypeOrNull())
}

private fun compressImage(context: Context, uri: Uri, quality: Int = 70): ByteArray? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()

        val outputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        outputStream.toByteArray()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}