package com.zhuzichu.android.wan.ui.search.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.wan.db.entity.DOKeyword
import com.zhuzichu.android.wan.repository.LocalRepository
import javax.inject.Inject

class UseCasePlusHistoryKey @Inject constructor(
    private val localRepository: LocalRepository
) : UseCase<DOKeyword, Unit>() {

    override fun execute(parameters: DOKeyword) {
        return localRepository.saveKeyword(parameters)
    }
}