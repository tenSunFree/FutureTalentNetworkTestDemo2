package com.home.futuretalentnetworktestdemo2.common.network

import com.home.futuretalentnetworktestdemo2.common.network.module.NetworkModule
import com.home.futuretalentnetworktestdemo2.detail.model.pojo.UserDetailPojo
import com.home.futuretalentnetworktestdemo2.list.model.pojo.UserBriefPojo
import com.home.futuretalentnetworktestdemo2.pagination.model.pojo.RepositoriesPojo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("users")
    suspend fun getUserBriefList(@Query("per_page") per_page: Int): List<UserBriefPojo>

    @GET("users/{userName}")
    suspend fun getUserDetail(@Path("userName") userId: String): UserDetailPojo

    @Headers("authorization: Bearer ${NetworkModule.TOKEN}")
    @GET("user/repos")
    suspend fun getRepositoriesList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20
    ): Response<List<RepositoriesPojo>>
}
