package com.zhuzichu.android.wan.ui.account.login.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import com.zhuzichu.android.wan.repository.RemoteRepository
import com.zhuzichu.android.wan.repository.entity.BeanBase
import com.zhuzichu.android.wan.repository.entity.BeanCoin
import com.zhuzichu.android.wan.repository.entity.BeanPage
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * desc:  <br/>
 * time: 2019/11/22 17:58 <br/>
 * author: Coffee <br/>
 * since V 1.2 <br/>
 */
class UseCaseGetCoins @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<Int, Flowable<BeanBase<BeanPage<BeanCoin>>>>() {

    override fun execute(parameters: Int): Flowable<BeanBase<BeanPage<BeanCoin>>> {
        return remoteRepository.getCoins(parameters)
            .bindToSchedulers()
            .bindToException()
    }
}