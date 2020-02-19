package com.zhuzichu.android.wan.ui.home.fragment

import androidx.databinding.ViewDataBinding
import com.zhuzichu.android.wan.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.bindArgument

abstract class FragmentHomeChild<TBinding : ViewDataBinding, TViewModel : ViewModelAnalyticsBase> :
    FragmentAnalyticsBase<TBinding, TViewModel>() {
    open val title: String? by bindArgument()
}