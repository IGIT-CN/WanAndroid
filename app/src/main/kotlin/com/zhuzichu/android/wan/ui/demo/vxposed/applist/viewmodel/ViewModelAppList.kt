package com.zhuzichu.android.wan.ui.demo.vxposed.applist.viewmodel

import com.uber.autodispose.autoDispose
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.className
import com.zhuzichu.android.shared.extension.logi
import com.zhuzichu.android.shared.extension.toast
import com.zhuzichu.android.wan.ui.demo.vxposed.applist.domain.UseCaseGetApps
import javax.inject.Inject

class ViewModelAppList @Inject constructor(
    private val useCaseGetApps: UseCaseGetApps
) : ViewModelAnalyticsBase() {

    fun loadData() {
        useCaseGetApps.execute(this)
            .autoDispose(this)
            .subscribe(
                {
                    it.size.toString().toast()
                    it.logi(className())
                },
                {
                    handleThrowable(it)
                }
            )
    }
}