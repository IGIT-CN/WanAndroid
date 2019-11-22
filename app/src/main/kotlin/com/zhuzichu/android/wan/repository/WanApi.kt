package com.zhuzichu.android.wan.repository

import com.zhuzichu.android.wan.repository.entity.BeanBase
import com.zhuzichu.android.wan.repository.entity.BeanCoin
import com.zhuzichu.android.wan.repository.entity.BeanLogin
import com.zhuzichu.android.wan.repository.entity.BeanPage
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.*

/**
 * desc:  <br/>
 * time: 2019/11/22 16:17 <br/>
 * author: Coffee <br/>
 * since V 1.2 <br/>
 */
interface WanApi {

    @POST("user/login")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Flowable<Response<BeanBase<BeanLogin>>>

    @GET("/lg/coin/list/{page}/json")
    fun getCoins(
        @Path("page") page: Int
    ): Flowable<BeanBase<BeanPage<BeanCoin>>>
}