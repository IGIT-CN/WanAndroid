package com.zhuzichu.android.wan.ui.setting.languages.fragment

import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.zhuzichu.android.mvvm.base.BaseFragment
import com.zhuzichu.android.shared.extension.updateApplicationLanguage
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentLanguagesBinding
import com.zhuzichu.android.wan.ui.setting.languages.viewmodel.ViewModelLanguages
import com.zhuzichu.android.shared.storage.GlobalStorage
import java.util.*
import javax.inject.Inject

class FragmentLanguages :
    BaseFragment<FragmentLanguagesBinding, ViewModelLanguages>() {

    @Inject
    lateinit var globalStorage: GlobalStorage

    override fun setLayoutId(): Int = R.layout.fragment_languages

    override fun bindVariableId(): Int = BR.viewModel

    override fun initVariable() {
        viewModel.languagesChangeEvent.observe(this, Observer {
            globalStorage.locale = it
            activityCtx.window.setWindowAnimations(R.style.WindowFade)
            updateApplicationLanguage(Locale(globalStorage.locale ?: Locale.getDefault().country))
            ActivityCompat.recreate(activityCtx)
        })
    }

    override fun initData() {
        viewModel.loadSectionLable()
    }
}