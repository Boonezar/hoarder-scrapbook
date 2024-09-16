package com.boonezar.hoarderscrapbook.providers

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.boonezar.hoarderscrapbook.R
import java.io.File

class ComposeFileProvider : FileProvider(R.xml.paths) {
    companion object {
        fun getCacheImageUri(context: Context): Uri {
            val directory = File(context.cacheDir, "cache_images")
            directory.mkdirs()
            val file = File.createTempFile(
                "selected_image_",
                ".jpg",
                directory
            )
            val authority = context.packageName + ".providers.ComposeFileProvider"
            return getUriForFile(context, authority, file)
        }
    }
}