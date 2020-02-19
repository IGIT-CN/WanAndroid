package com.zhuzichu.android.wan.ui.setting.animation.fragment

import androidx.databinding.library.baseAdapters.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentAnimationBinding
import com.zhuzichu.android.wan.ui.setting.animation.viewmodel.ViewModelAnimation
import com.zhuzichu.android.wan.base.FragmentAnalyticsBase

class FragmentAnimation : FragmentAnalyticsBase<FragmentAnimationBinding, ViewModelAnimation>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_animation

    override fun initFirstData() {
        super.initFirstData()
        viewModel.updateData()
    }
}