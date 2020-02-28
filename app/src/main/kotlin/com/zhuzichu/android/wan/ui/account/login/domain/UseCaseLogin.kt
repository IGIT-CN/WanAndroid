package com.zhuzichu.android.wan.ui.account.login.domain

import com.zhuzichu.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.ext.bindToException
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.wan.repository.RemoteRepository
import com.zhuzichu.android.shared.entity.BeanBase
import com.zhuzichu.android.wan.repository.entity.BeanLogin
import com.zhuzichu.android.wan.ui.account.login.entity.ParamterLogin
import io.reactivex.Flowable
import retrofit2.Response
import javax.inject.Inject

class UseCaseLogin @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<ParamterLogin, Flowable<Response<BeanBase<BeanLogin>>>>() {

    override fun execute(parameters: ParamterLogin): Flowable<Response<BeanBase<BeanLogin>>> {
        return remoteRepository.login(parameters.username, parameters.password)
            .bindToSchedulers()
            .bindToException()
    }
}