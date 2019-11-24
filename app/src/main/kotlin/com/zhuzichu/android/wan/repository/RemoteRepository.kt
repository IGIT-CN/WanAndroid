package com.zhuzichu.android.wan.repository

import com.zhuzichu.android.shared.entity.BeanBase
import com.zhuzichu.android.wan.repository.entity.BeanCoin
import com.zhuzichu.android.wan.repository.entity.BeanLogin
import com.zhuzichu.android.wan.repository.entity.BeanPage
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Named

interface RemoteRepository {
    fun login(username: String, password: String): Flowable<Response<BeanBase<BeanLogin>>>

    fun getCoins(page: Int): Flowable<BeanBase<BeanPage<BeanCoin>>>
}

class RemoteRepositoryImpl(
    @Named("gsonRetrofit")
    private val gsonRetrofit: Retrofit,
    @Named("htmlRetrofit")
    private val htmlRetrofit: Retrofit
) : RemoteRepository {

    override fun login(
        username: String,
        password: String
    ): Flowable<Response<BeanBase<BeanLogin>>> {
        return app.login(username, password)
    }

    override fun getCoins(page: Int): Flowable<BeanBase<BeanPage<BeanCoin>>> {
        return app.getCoins(page)
    }

    private val app by lazy { gsonRetrofit.create(WanApi::class.java) }

    private val html by lazy { htmlRetrofit.create(HtmlApi::class.java) }
}