package com.home.futuretalentnetworktestdemo2.list.model.repository

import com.home.futuretalentnetworktestdemo2.common.network.Api
import com.home.futuretalentnetworktestdemo2.list.model.pojo.UserBriefPojo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListRepository @Inject
constructor(private val api: Api) {

    suspend fun getList(): List<UserBriefPojo> {
        val userCount = 65
        return api.getUserBriefList(userCount)
    }
}
