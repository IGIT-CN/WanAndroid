package com.zhuzichu.android.wan.ui.main.viewmodel

import com.uber.autodispose.autoDispose
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.wan.ui.account.login.domain.UseCaseGetCoins
import javax.inject.Inject

class ViewModelMain @Inject constructor(
    private val useCaseGetCoins: UseCaseGetCoins
) : ViewModelAnalyticsBase() {

    fun loadCoinsData() {
        useCaseGetCoins.execute(1)
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({
                toast(it.data?.size.toString())
            }, {
                handleThrowable(it)
            })
    }
}