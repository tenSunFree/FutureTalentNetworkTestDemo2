package com.home.futuretalentnetworktestdemo2.common.network

import com.home.futuretalentnetworktestdemo2.list.model.pojo.UserBriefPojo
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("users")
    suspend fun getUserBriefList(@Query("per_page") per_page: Int): List<UserBriefPojo>
}
