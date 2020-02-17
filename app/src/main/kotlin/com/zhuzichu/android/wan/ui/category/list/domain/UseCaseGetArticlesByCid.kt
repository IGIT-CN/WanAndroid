package com.zhuzichu.android.wan.ui.category.list.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import com.zhuzichu.android.wan.repository.RemoteRepository
import com.zhuzichu.android.shared.entity.BeanBase
import com.zhuzichu.android.shared.entity.BeanPage
import com.zhuzichu.android.wan.repository.entity.BeanArticle
import com.zhuzichu.android.wan.ui.category.list.entity.ParamterCategory
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetArticlesByCid @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<ParamterCategory, Flowable<BeanBase<BeanPage<BeanArticle>>>>() {

    override fun execute(parameters: ParamterCategory): Flowable<BeanBase<BeanPage<BeanArticle>>> {
        return remoteRepository.getArticles(parameters.page, parameters.cid)
            .bindToSchedulers()
            .bindToException()
    }
}