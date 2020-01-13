package com.zhuzichu.android.wan.ui.camera.main.fragment

import androidx.databinding.library.baseAdapters.BR
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentCameraBasicBinding
import com.zhuzichu.android.wan.ui.camera.main.viewmodel.ViewModelCameraBasic

class FragmentCameraBasic :
    FragmentAnalyticsBase<FragmentCameraBasicBinding, ViewModelCameraBasic>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_camera_basic

}