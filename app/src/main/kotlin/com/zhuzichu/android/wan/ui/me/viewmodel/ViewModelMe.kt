package com.zhuzichu.android.wan.ui.me.viewmodel

import com.uber.autodispose.autoDispose
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.wan.ui.account.login.domain.UseCaseGetCoins
import javax.inject.Inject

class ViewModelMe @Inject constructor(
    private val useCaseGetCoins: UseCaseGetCoins
) : ViewModelAnalyticsBase() {

    fun loadCoins(page: Int) {
        useCaseGetCoins.execute(page)
            .autoLoading(this)
            .autoDispose(this)
            .subscribe(
                {
                    toast(it.data?.pageCount.toString())
                }, {
                    handleThrowable(it)
                }
            )
    }
}