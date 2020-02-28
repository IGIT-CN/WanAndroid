package com.zhuzichu.android.wan.ui.home.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.entity.BeanBase
import com.zhuzichu.android.shared.ext.bindToException
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.wan.repository.RemoteRepository
import com.zhuzichu.android.wan.repository.entity.BeanArticle
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetTopArticles @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<Unit, Flowable<BeanBase<List<BeanArticle>>>>() {

    override fun execute(parameters: Unit): Flowable<BeanBase<List<BeanArticle>>> {
        return remoteRepository.getTopArticles()
            .bindToSchedulers()
            .bindToException()
    }
}