package com.zhuzichu.android.wan.ui.home.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.entity.BeanBase
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import com.zhuzichu.android.wan.repository.RemoteRepository
import com.zhuzichu.android.wan.repository.entity.BeanArticle
import com.zhuzichu.android.shared.entity.BeanPage
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetArticles @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<Int, Flowable<BeanBase<BeanPage<BeanArticle>>>>() {

    override fun execute(parameters: Int): Flowable<BeanBase<BeanPage<BeanArticle>>> {
        return remoteRepository.getArticles(parameters, null)
            .bindToSchedulers()
            .bindToException()
    }
}