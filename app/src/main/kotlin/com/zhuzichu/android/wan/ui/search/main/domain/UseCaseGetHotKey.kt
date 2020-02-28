package com.zhuzichu.android.wan.ui.search.main.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.ext.bindToException
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.wan.repository.RemoteRepository
import com.zhuzichu.android.shared.entity.BeanBase
import com.zhuzichu.android.wan.repository.entity.BeanKeyword
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetHotKey @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<Unit, Flowable<BeanBase<List<BeanKeyword>>>>() {

    override fun execute(parameters: Unit): Flowable<BeanBase<List<BeanKeyword>>> {
        return remoteRepository.getHotKey()
            .bindToSchedulers()
            .bindToException()
    }
}