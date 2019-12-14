package com.zhuzichu.android.wan.ui.search.main.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.wan.db.entity.DOKeyword
import com.zhuzichu.android.wan.repository.LocalRepository
import javax.inject.Inject

class UseCaseDropHistoryKey @Inject constructor(
    private val localRepository: LocalRepository
) : UseCase<List<DOKeyword>, Unit>() {

    override fun execute(parameters: List<DOKeyword>) {
        return localRepository.deleteKeyword(parameters)
    }
}