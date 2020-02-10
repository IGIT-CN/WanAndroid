package com.zhuzichu.android.shared.widget.log

import android.util.Log
import org.slf4j.LoggerFactory
import timber.log.Timber

class FileLoggingTree : Timber.Tree() {

    private val logger = LoggerFactory.getLogger(FileLoggingTree::class.java)

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE) {
            return
        }
        val logMessage = "$tag: $message"
        when (priority) {
            Log.DEBUG -> {
                logger.debug(logMessage)
            }
            Log.INFO -> {
                logger.info(logMessage)
            }
            Log.WARN -> {
                logger.warn(logMessage)
            }
            Log.ERROR -> {
                logger.error(logMessage)
            }
        }
    }
}