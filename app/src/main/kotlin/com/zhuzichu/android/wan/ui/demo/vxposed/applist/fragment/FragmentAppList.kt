package com.zhuzichu.android.wan.ui.demo.vxposed.applist.fragment

import android.Manifest
import com.tbruyelle.rxpermissions2.RxPermissions
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.wan.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentAppListBinding
import com.zhuzichu.android.wan.ui.demo.vxposed.applist.viewmodel.ViewModelAppList

class FragmentAppList : FragmentAnalyticsBase<FragmentAppListBinding, ViewModelAppList>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_app_list

    override fun initFirstData() {
        super.initFirstData()
        RxPermissions(this).request(Manifest.permission.READ_EXTERNAL_STORAGE)
            .autoDispose(viewModel)
            .subscribe {
                if (it) {
                    viewModel.loadData()
                } else {
                    back()
                }
            }
    }

}