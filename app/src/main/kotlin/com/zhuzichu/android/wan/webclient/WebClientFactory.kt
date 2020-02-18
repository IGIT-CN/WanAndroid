package com.zhuzichu.android.wan.webclient

import com.just.agentweb.WebViewClient


object WebClientFactory {

    val JIAN_SHU = "https://www.jianshu.com"

    fun create(url: String): WebViewClient {
        return when {
            url.startsWith(JIAN_SHU) -> BaseWebClient()
            else -> BaseWebClient()
        }
    }

}