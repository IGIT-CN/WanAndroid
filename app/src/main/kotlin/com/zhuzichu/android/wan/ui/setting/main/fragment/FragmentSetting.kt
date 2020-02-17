package com.zhuzichu.android.wan.ui.setting.main.fragment

import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentSettingBinding
import com.zhuzichu.android.wan.ui.setting.main.viewmodel.ViewModelSetting
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase


class FragmentSetting :
    FragmentAnalyticsBase<FragmentSettingBinding, ViewModelSetting>() {

    override fun setLayoutId(): Int = R.layout.fragment_setting

    override fun bindVariableId(): Int = BR.viewModel

}