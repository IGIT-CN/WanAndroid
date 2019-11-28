package com.zhuzichu.android.wan.repository

import com.zhuzichu.android.shared.entity.BeanBase
import com.zhuzichu.android.wan.repository.entity.BeanArticle
import com.zhuzichu.android.wan.repository.entity.BeanCoin
import com.zhuzichu.android.wan.repository.entity.BeanLogin
import com.zhuzichu.android.shared.entity.BeanPage
import com.zhuzichu.android.wan.repository.entity.BeanBanner
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.*

interface WanApi {

    @POST("user/login")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Flowable<Response<BeanBase<BeanLogin>>>

    @GET("/lg/coin/list/{page}/json")
    fun getCoins(@Path("page") page: Int): Flowable<BeanBase<BeanPage<BeanCoin>>>

    @GET("/article/list/{page}/json")
    fun getArticles(@Path("page") page: Int): Flowable<BeanBase<BeanPage<BeanArticle>>>

    @GET("/article/top/json")
    fun getTopArticles(): Flowable<BeanBase<List<BeanArticle>>>

    @GET("/banner/json")
    fun getBanner(): Flowable<BeanBase<List<BeanBanner>>>

    @POST("/lg/collect/{id}/json")
    fun collect(@Path("id") id: Int): Flowable<BeanBase<Any>>

    @POST("/lg/uncollect_originId/{id}/json")
    fun unCollect(@Path("id") id: Int): Flowable<BeanBase<Any>>

    @GET("/article/listproject/{page}/json")
    fun getProjects(@Path("page") page: Int): Flowable<BeanBase<BeanPage<BeanArticle>>>
}