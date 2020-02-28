package com.zhuzichu.android.wan.ui.home.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.entity.BeanBase
import com.zhuzichu.android.shared.entity.BeanPage
import com.zhuzichu.android.shared.ext.bindToException
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.wan.repository.RemoteRepository
import com.zhuzichu.android.wan.repository.entity.BeanArticle
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetProjects @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<Int, Flowable<BeanBase<BeanPage<BeanArticle>>>>() {

    override fun execute(parameters: Int): Flowable<BeanBase<BeanPage<BeanArticle>>> {
        return remoteRepository.getProjects(parameters)
            .bindToSchedulers()
            .bindToException()
    }
}