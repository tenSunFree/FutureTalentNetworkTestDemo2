package com.home.futuretalentnetworktestdemo2.pagination.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.home.futuretalentnetworktestdemo2.pagination.model.pojo.RepositoriesPojo
import com.home.futuretalentnetworktestdemo2.pagination.model.repository.PaginationDataSourceFactory
import com.home.futuretalentnetworktestdemo2.pagination.model.repository.PaginationPageKeyedDataSource
import com.home.futuretalentnetworktestdemo2.pagination.model.repository.PaginationRepository
import javax.inject.Inject

class PaginationViewModel @Inject constructor(
    repository: PaginationRepository
) : ViewModel() {

    var pagedListLiveData: LiveData<PagedList<RepositoriesPojo>>? = null

    init {
        // If you want to do better here
        // you can listen to the callback, and then be managed by the viewState
        val dataSourceFactory = PaginationDataSourceFactory(viewModelScope, repository)
        val config: PagedList.Config = (PagedList.Config.Builder())
            .setEnablePlaceholders(false)
            .setPageSize(PaginationPageKeyedDataSource.PAGE_SIZE)
            .build()
        pagedListLiveData = LivePagedListBuilder(dataSourceFactory, config).build()
    }
}