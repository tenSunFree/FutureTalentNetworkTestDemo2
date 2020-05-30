package com.home.futuretalentnetworktestdemo2.pagination.model.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.home.futuretalentnetworktestdemo2.pagination.model.pojo.RepositoriesPojo
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class PaginationDataSourceFactory @Inject constructor(
    private val scope: CoroutineScope, private val repository: PaginationRepository
) : DataSource.Factory<Int, RepositoriesPojo>() {

    private val popularPostLiveDataSource: MutableLiveData<
            PageKeyedDataSource<Int, RepositoriesPojo>> = MutableLiveData()

    override fun create(): DataSource<Int, RepositoriesPojo> {
        val dataSource = PaginationPageKeyedDataSource(scope, repository)
        popularPostLiveDataSource.postValue(dataSource)
        return dataSource
    }
}