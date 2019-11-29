package com.zhuzichu.android.wan.ui.setting.theme.fragment

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentThemeBinding
import com.zhuzichu.android.wan.ui.setting.theme.viewmodel.ViewModelTheme
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.storage.GlobalStorage

import javax.inject.Inject

class FragmentTheme : FragmentAnalyticsBase<FragmentThemeBinding, ViewModelTheme>() {

    @Inject
    lateinit var globalStorage: GlobalStorage

    override fun setLayoutId(): Int = R.layout.fragment_theme

    override fun bindVariableId(): Int = BR.viewModel

    override fun initData() {
        viewModel.loadSectionLable()
    }

    override fun initVariable() {
        viewModel.themeChangeEvent.observe(this, Observer {
            activityCtx.window.setWindowAnimations(R.style.WindowFade)
            globalStorage.uiMode = it
            AppCompatDelegate.setDefaultNightMode(globalStorage.uiMode)
            activityCtx.recreate()
        })
    }
}