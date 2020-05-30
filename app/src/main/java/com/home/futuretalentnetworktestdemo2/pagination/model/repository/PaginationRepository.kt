package com.home.futuretalentnetworktestdemo2.pagination.model.repository

import com.home.futuretalentnetworktestdemo2.common.network.Api
import com.home.futuretalentnetworktestdemo2.pagination.model.pojo.RepositoriesPojo
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaginationRepository @Inject
constructor(private val api: Api) {

    suspend fun getList(page: Int): Response<List<RepositoriesPojo>> {
        return api.getRepositoriesList(page)
    }
}
