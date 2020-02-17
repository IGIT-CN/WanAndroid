package com.zhuzichu.android.shared.extension

import timber.log.Timber

private const val TAG = "Nice"

fun Any.logi(tag: String = TAG, throwable: Throwable? = null) {
    throwable?.let {
        Timber.tag(tag).i(throwable, this.toString())
    } ?: let {
        Timber.tag(tag).i(this.toString())
    }
}

fun Any.logd(tag: String = TAG, throwable: Throwable? = null) {
    throwable?.let {
        Timber.tag(tag).d(throwable, this.toString())
    } ?: let {
        Timber.tag(tag).d(this.toString())
    }
}

fun Any.loge(tag: String = TAG, throwable: Throwable? = null) {
    throwable?.let {
        Timber.tag(tag).e(throwable, this.toString())
    } ?: let {
        Timber.tag(tag).e(this.toString())
    }
}

fun Any.logw(tag: String = TAG, throwable: Throwable? = null) {
    throwable?.let {
        Timber.tag(tag).w(throwable, this.toString())
    } ?: let {
        Timber.tag(tag).w(this.toString())
    }
}