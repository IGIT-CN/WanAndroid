package com.zhuzichu.android.shared.global

import com.zhuzichu.android.libs.tool.isExternalStorageWriteable
import com.zhuzichu.android.shared.global.AppGlobal.context
import java.io.File

object CacheGlobal {

    private const val CACHE_GLIDE_DIR = "/cache_glide"

    fun initDir() {
        getGlideCacheDir()
    }

    fun getGlideCacheDir(): String {
        return getDiskCacheDir(CACHE_GLIDE_DIR).absolutePath
    }

    private fun getDiskCacheDir(last: String): File {
        val path: String = if (isExternalStorageWriteable()) {
            context.externalCacheDir.toString() + last
        } else {
            context.cacheDir.toString() + last
        }
        val file = File(path)
        if (!file.exists()) {
            file.mkdirs()
        }
        return file.absoluteFile
    }

}