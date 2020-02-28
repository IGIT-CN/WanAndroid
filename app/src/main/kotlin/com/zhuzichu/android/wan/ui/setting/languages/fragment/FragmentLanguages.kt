package com.zhuzichu.android.wan.ui.setting.languages.fragment

import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.zhuzichu.android.mvvm.base.BaseFragment
import com.zhuzichu.android.shared.ext.updateApplicationLanguage
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentLanguagesBinding
import com.zhuzichu.android.wan.ui.setting.languages.viewmodel.ViewModelLanguages
import com.zhuzichu.android.shared.storage.GlobalStorage
import java.util.*

class FragmentLanguages :
    BaseFragment<FragmentLanguagesBinding, ViewModelLanguages>() {

    override fun setLayoutId(): Int = R.layout.fragment_languages

    override fun bindVariableId(): Int = BR.viewModel

    override fun initVariable() {
        viewModel.languagesChangeEvent.observe(this, Observer {
            GlobalStorage.locale = it
            updateApplicationLanguage(Locale(GlobalStorage.locale ?: Locale.getDefault().country))
            activityCtx.window.setWindowAnimations(R.style.WindowFade)
            ActivityCompat.recreate(activityCtx)
        })
    }

    override fun initData() {
        viewModel.loadSectionLable()
    }
}