package com.zhuzichu.android.wan.ui.web.main.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.bindArgument
import com.zhuzichu.android.shared.extension.isDark
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentWebBinding
import com.zhuzichu.android.wan.ui.web.main.viewmodel.ViewModelWeb
import kotlinx.android.synthetic.main.fragment_web.*

class FragmentWeb :
    FragmentAnalyticsBase<FragmentWebBinding, ViewModelWeb>() {

    val url: String? by bindArgument()

    private lateinit var webSettings: WebSettings

    override fun setLayoutId(): Int = R.layout.fragment_web

    override fun bindVariableId(): Int = BR.viewModel

    override fun initView() {
        super.initView()
        initWebView()
        initBackListener()
        initWebAlpha()
    }

    private fun initWebAlpha() {
        if (requireContext().isDark()) {
            viewModel.alpha.value = 0.5f
        } else {
            viewModel.alpha.value = 0.0f
        }
    }

    override fun initViewObservable() {
        viewModel.onExitEvent.observe(viewLifecycleOwner, Observer {
            requireActivity().finish()
        })
    }

    private fun initBackListener() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (webview.canGoBack()) {
                showLoading()
                webview.goBack()
            } else {
                requireActivity().finish()
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {

        webSettings = webview.settings

        webSettings.loadsImagesAutomatically = true
        webview.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        webSettings.javaScriptEnabled = true
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        webview.isSaveEnabled = true
        webview.keepScreenOn = true

        webview.setDownloadListener { paramAnonymousString1, _, _, _, _ ->
            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            intent.data = Uri.parse(paramAnonymousString1)
            activity?.startActivity(intent)
        }

        webview.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                if (!webSettings.loadsImagesAutomatically) {
                    webSettings.loadsImagesAutomatically = true
                }
                hideLoading()
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                showLoading()
                return false
            }

        }

        webview.webChromeClient = object : WebChromeClient() {

            override fun onReceivedTitle(view: WebView, title: String) {
                viewModel.title.value = title
                hideLoading()
            }

        }

        showLoading()
        webview.loadUrl(url)

    }
}