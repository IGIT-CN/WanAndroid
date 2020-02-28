package com.zhuzichu.android.wan.ui.search.list.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.ext.bindToException
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.wan.repository.RemoteRepository
import com.zhuzichu.android.shared.entity.BeanBase
import com.zhuzichu.android.shared.entity.BeanPage
import com.zhuzichu.android.wan.repository.entity.BeanArticle
import com.zhuzichu.android.wan.ui.search.main.entity.ParamterSearch
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseSearchArticle @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<ParamterSearch, Flowable<BeanBase<BeanPage<BeanArticle>>>>() {

    override fun execute(parameters: ParamterSearch): Flowable<BeanBase<BeanPage<BeanArticle>>> {
        return remoteRepository.search(parameters.page, parameters.keyWord)
            .bindToSchedulers()
            .bindToException()
    }
}