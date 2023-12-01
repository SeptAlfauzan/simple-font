package com.septalfauzan.simplefont.utils

import android.content.Context
import android.graphics.Typeface
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.text.font.FontFamily
import java.io.File
import java.io.FileOutputStream

object FontUtils{
    fun pickLocalTtf(context: Context, uri: Uri?, callback: (FontFamily) -> Unit){
            try {
                uri?.let {
                    context.contentResolver.openInputStream(it)?.use { inputStream ->
                        // Create a temporary file in your app's internal storage
                        val tempFile = File.createTempFile("font", null, context.cacheDir)
                        FileOutputStream(tempFile).use { outputStream ->
                            inputStream.copyTo(outputStream)
                        }
                        // Load Typeface from the temporary file
                        val typeface = Typeface.createFromFile(tempFile)
                        val fontFamilyLocal = FontFamily(typeface)
                        callback(fontFamilyLocal)
                    }
                }
            } catch (e: Exception) {
                Log.d(this::class.java.name, "error: $e")
                Toast.makeText(context, "error: ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }
}