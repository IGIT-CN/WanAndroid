package com.zhuzichu.android.wan.ui.me.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import com.zhuzichu.android.wan.repository.RemoteRepository
import com.zhuzichu.android.shared.entity.BeanBase
import com.zhuzichu.android.wan.repository.entity.BeanUserInfo
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetUserInfo @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<Unit, Flowable<BeanBase<BeanUserInfo>>>() {

    override fun execute(parameters: Unit): Flowable<BeanBase<BeanUserInfo>> {
        return remoteRepository.getUserInfo()
            .bindToSchedulers()
            .bindToException()
    }
}