package com.zhuzichu.android.wan.ui.web.main.fragment

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import com.just.agentweb.AgentWeb
import com.just.agentweb.NestedScrollAgentWebView
import com.just.agentweb.WebChromeClient
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.bindArgument
import com.zhuzichu.android.shared.extension.getAgentWeb
import com.zhuzichu.android.shared.extension.isDark
import com.zhuzichu.android.shared.extension.toColorByResId
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentWebBinding
import com.zhuzichu.android.wan.ui.web.main.viewmodel.ViewModelWeb
import com.zhuzichu.android.wan.webclient.WebClientFactory
import kotlinx.android.synthetic.main.fragment_web.*

class FragmentWeb :
    FragmentAnalyticsBase<FragmentWebBinding, ViewModelWeb>() {

    val url: String? by bindArgument()

    private var agentWeb: AgentWeb? = null

    override fun setLayoutId(): Int = R.layout.fragment_web

    override fun bindVariableId(): Int = BR.viewModel

    override fun initView() {
        super.initView()
        initWebView()
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

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val webView = NestedScrollAgentWebView(requireContext())
        val layoutParams = FrameLayout.LayoutParams(-1, -1)
        agentWeb = url.getAgentWeb(
            requireActivity(),
            content,
            layoutParams,
            webView,
            WebClientFactory.create(url.toString()),
            mWebChromeClient,
            R.color.color_primary.toColorByResId()
        )

        agentWeb?.webCreator?.webView?.apply {
            overScrollMode = WebView.OVER_SCROLL_NEVER
            settings.domStorageEnabled = true
            settings.javaScriptEnabled = true
            settings.loadsImagesAutomatically = true
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                requireActivity().finish()
            }
        }
    }


    private val mWebChromeClient = object : WebChromeClient() {
        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            viewModel.title.value = title
        }
    }
}