package com.zhuzichu.android.wan.ui.camera.main.fragment

import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentCameraBinding
import com.zhuzichu.android.wan.ui.camera.main.viewmodel.ViewModelCamera

class FragmentCamera : FragmentAnalyticsBase<FragmentCameraBinding, ViewModelCamera>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_camera

}