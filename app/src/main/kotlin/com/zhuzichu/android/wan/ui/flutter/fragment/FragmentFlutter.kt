package com.zhuzichu.android.wan.ui.flutter.fragment

import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentFlutterBinding
import com.zhuzichu.android.wan.ui.flutter.viewmodel.ViewModelFlutter

class FragmentFlutter : FragmentAnalyticsBase<FragmentFlutterBinding, ViewModelFlutter>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_flutter

}