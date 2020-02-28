package com.zhuzichu.android.wan.ui.me.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.autoLoading
import com.zhuzichu.android.shared.ext.createCommand
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.me.domain.UseCaseGetUserInfo
import javax.inject.Inject

class ViewModelMe @Inject constructor(
    private val useCaseGetUserInfo: UseCaseGetUserInfo
) : ViewModelAnalyticsBase() {

    val username = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val coins = MutableLiveData<String>()
    val ranking = MutableLiveData<String>()

    val onClickSetting = createCommand{
        startFragment(R.id.action_fragmentMain_to_fragmentSetting)
    }

    val onClickCoins = createCommand{

    }

    val onClickRanking = createCommand{

    }

    val onClickDemo = createCommand{
        startFragment(R.id.action_fragmentMain_to_fragmentDemo)
    }

    fun updateUserInfo() {
        username.value = GlobalStorage.username
        nickname.value = GlobalStorage.nickname
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