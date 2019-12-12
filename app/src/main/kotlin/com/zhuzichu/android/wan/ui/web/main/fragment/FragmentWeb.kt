package com.zhuzichu.android.wan.ui.web.main.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.bindArgument
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentWebBinding
import com.zhuzichu.android.wan.ui.web.main.viewmodel.ViewModelWeb
import kotlinx.android.synthetic.main.fragment_web.*

class FragmentWeb :
    FragmentAnalyticsBase<FragmentWebBinding, ViewModelWeb>() {

    val url: String? by bindArgument()

    private lateinit var webSettings: WebSettings;

    override fun setLayoutId(): Int = R.layout.fragment_web

    override fun bindVariableId(): Int = BR.viewModel

    override fun initView() {
        super.initView()
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {

        webSettings = webview.settings
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK//加载缓存否则网络
        webSettings.loadsImagesAutomatically = true
        webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null)//软件解码
        webview.setLayerType(View.LAYER_TYPE_HARDWARE, null)//硬件解码
        webSettings.javaScriptEnabled = true // 设置支持javascript脚本
        webSettings.setSupportZoom(true)// 设置可以支持缩放
        webSettings.builtInZoomControls =
            true// 设置出现缩放工具 是否使用WebView内置的缩放组件，由浮动在窗口上的缩放控制和手势缩放控制组成，默认false
        webSettings.displayZoomControls = false//隐藏缩放工具
        webSettings.useWideViewPort = true// 扩大比例的缩放
        webSettings.loadWithOverviewMode = true
        webSettings.databaseEnabled = true//
        webSettings.domStorageEnabled =
            true//是否开启本地DOM存储  鉴于它的安全特性（任何人都能读取到它，尽管有相应的限制，将敏感数据存储在这里依然不是明智之举），Android 默认是关闭该功能的。
        webview.isSaveEnabled = true
        webview.keepScreenOn = true


        webview.setDownloadListener { paramAnonymousString1, _, _, _, _ ->
            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            intent.data = Uri.parse(paramAnonymousString1)
            requireContext().startActivity(intent)
        }

        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                if (!webSettings.loadsImagesAutomatically) {
                    webSettings.loadsImagesAutomatically = true
                }
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                val uri = url.split("?")[0]
                if (uri == "https://uland.taobao.com/coupon/edetail") {

                    return true
                }
                return false
            }
        }
        webview.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView, title: String) {
                viewModel.title.value = title
            }
        }
        webview.loadUrl(url)
    }
}