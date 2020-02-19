package com.zhuzichu.android.wan.ui.demo.ffmpeg.main.fragment

import com.zhuzichu.android.wan.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentFfmpegBinding
import com.zhuzichu.android.wan.ui.demo.ffmpeg.main.viewmodel.ViewModelAVinfo

class FragmentAVinfo : FragmentAnalyticsBase<FragmentFfmpegBinding, ViewModelAVinfo>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_avinfo

}