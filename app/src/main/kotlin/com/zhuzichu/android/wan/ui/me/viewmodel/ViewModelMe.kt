package com.zhuzichu.android.wan.ui.me.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.me.domain.UseCaseGetUserInfo
import javax.inject.Inject

class ViewModelMe @Inject constructor(
    private val globalStorage: GlobalStorage,
    private val useCaseGetUserInfo: UseCaseGetUserInfo
) : ViewModelAnalyticsBase() {

    val username = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val coins = MutableLiveData<String>()
    val ranking = MutableLiveData<String>()

    val onClickSetting = BindingCommand<Any>({
        startFragment(R.id.action_fragmentMain_to_fragmentSetting)
    })

    val onClickCoins = BindingCommand<Any>({

    })

    val onClickRanking = BindingCommand<Any>({

    })

    fun updateUserInfo() {
        username.value = globalStorage.username
        nickname.value = globalStorage.nickname
        useCaseGetUserInfo.execute(Unit)
            .autoLoading(this)
            .autoDispose(this)
            .subscribe(
                {
                    coins.value = it.data?.coinCount.toString()
                    ranking.value = it.data?.rank.toString()
                },
                {
                    handleThrowable(it)
                }
            )
    }
}