package com.zhuzichu.android.wan.repository

import com.zhuzichu.android.shared.entity.BeanBase
import com.zhuzichu.android.shared.entity.BeanPage
import com.zhuzichu.android.wan.repository.entity.*
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Named

interface RemoteRepository {
    fun login(username: String, password: String): Flowable<Response<BeanBase<BeanLogin>>>

    fun getCoins(page: Int): Flowable<BeanBase<BeanPage<BeanCoin>>>

    fun getArticles(page: Int): Flowable<BeanBase<BeanPage<BeanArticle>>>

    fun getBanner(): Flowable<BeanBase<List<BeanBanner>>>

    fun getTopArticles(): Flowable<BeanBase<List<BeanArticle>>>

    fun collect(id: Int): Flowable<BeanBase<Any>>

    fun unCollect(id: Int): Flowable<BeanBase<Any>>

    fun getProjects(page: Int): Flowable<BeanBase<BeanPage<BeanArticle>>>

    fun getUserInfo(): Flowable<BeanBase<BeanUserInfo>>

    fun getCollections(page: Int): Flowable<BeanBase<BeanPage<BeanArticle>>>
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

    override fun getArticles(page: Int): Flowable<BeanBase<BeanPage<BeanArticle>>> {
        return app.getArticles(page)
    }

    override fun getBanner(): Flowable<BeanBase<List<BeanBanner>>> {
        return app.getBanner()
    }

    override fun getTopArticles(): Flowable<BeanBase<List<BeanArticle>>> {
        return app.getTopArticles()
    }

    override fun collect(id: Int): Flowable<BeanBase<Any>> {
        return app.collect(id)
    }

    override fun unCollect(id: Int): Flowable<BeanBase<Any>> {
        return app.unCollect(id)
    }

    override fun getProjects(page: Int): Flowable<BeanBase<BeanPage<BeanArticle>>> {
        return app.getProjects(page)
    }

    override fun getUserInfo(): Flowable<BeanBase<BeanUserInfo>> {
        return app.getUserInfo()
    }

    override fun getCollections(page: Int): Flowable<BeanBase<BeanPage<BeanArticle>>> {
        return app.getCollections(page)
    }

    private val app by lazy { gsonRetrofit.create(WanApi::class.java) }

    private val html by lazy { htmlRetrofit.create(HtmlApi::class.java) }
}