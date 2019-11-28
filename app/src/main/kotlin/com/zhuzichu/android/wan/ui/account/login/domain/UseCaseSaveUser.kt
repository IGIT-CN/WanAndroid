package com.zhuzichu.android.wan.ui.account.login.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.wan.db.entity.DOUser
import com.zhuzichu.android.wan.repository.LocalRepository
import javax.inject.Inject

class UseCaseSaveUser @Inject constructor(
    private val localRepository: LocalRepository
) : UseCase<DOUser, Unit>() {

    override fun execute(parameters: DOUser) {
        return localRepository.saveUser(parameters)
    }
}