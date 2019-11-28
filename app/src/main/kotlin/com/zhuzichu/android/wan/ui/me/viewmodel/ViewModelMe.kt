package com.zhuzichu.android.wan.ui.me.viewmodel

import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.wan.ui.me.domain.UseCaseGetCoins
import com.zhuzichu.android.wan.ui.me.domain.UseCaseGetUserInfo
import javax.inject.Inject

class ViewModelMe @Inject constructor(
    private val useCaseGetCoins: UseCaseGetCoins,
    private val useCaseGetUserInfo: UseCaseGetUserInfo
) : ViewModelAnalyticsBase() {

    val onClickSetting = BindingCommand<Any>({

    })

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

    fun updateUserInfo() {
        useCaseGetUserInfo.execute(Unit)
            .autoLoading(this)
            .autoDispose(this)
            .subscribe(
                {

                },
                {
                    handleThrowable(it)
                }
            )
    }
}