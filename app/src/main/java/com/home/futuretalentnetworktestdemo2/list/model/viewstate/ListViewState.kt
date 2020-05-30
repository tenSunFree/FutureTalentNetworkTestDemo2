package com.home.futuretalentnetworktestdemo2.list.model.viewstate

import com.home.futuretalentnetworktestdemo2.list.model.viewdata.ListViewData

sealed class ListViewState

object Loading : ListViewState()
data class Success(val data: ListViewData) : ListViewState()
data class NetworkError(val message: String?) : ListViewState()