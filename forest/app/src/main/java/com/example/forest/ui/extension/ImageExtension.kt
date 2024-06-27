package com.example.forest.ui.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

object ImageLoader {
    fun load(url: String, view: CircleImageView) {
        CoroutineScope(Dispatchers.Main).launch {
            val image = loadImage(url)
            view.setImageBitmap(image)
        }
    }

    private suspend fun loadImage(url: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                BitmapFactory.decodeStream(URL(url).openStream())
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
