package com.zhuzichu.android.wan.ui.search.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.extension.bindToException
import com.zhuzichu.android.shared.extension.bindToSchedulers
import com.zhuzichu.android.wan.db.entity.DOKeyword
import com.zhuzichu.android.wan.repository.LocalRepository
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetHistoryKey @Inject constructor(
    private val localRepository: LocalRepository
) : UseCase<Unit, Flowable<List<DOKeyword>>>() {

    override fun execute(parameters: Unit): Flowable<List<DOKeyword>> {
        return localRepository.getKeywords()
            .bindToSchedulers()
            .bindToException()
    }
}