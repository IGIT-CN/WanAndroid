package com.zhuzichu.android.wan.ui.home.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.entity.BeanBase
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import com.zhuzichu.android.wan.repository.RemoteRepository
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseUnCollect @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<Int, Flowable<BeanBase<Any>>>() {

    override fun execute(parameters: Int): Flowable<BeanBase<Any>> {
        return remoteRepository.unCollect(parameters)
            .bindToSchedulers()
            .bindToException()
    }
}