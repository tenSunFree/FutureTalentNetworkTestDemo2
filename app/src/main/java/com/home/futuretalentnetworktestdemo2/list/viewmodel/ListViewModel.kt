package com.home.futuretalentnetworktestdemo2.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.futuretalentnetworktestdemo2.list.model.repository.ListRepository
import com.home.futuretalentnetworktestdemo2.list.model.viewdata.ListViewData
import com.home.futuretalentnetworktestdemo2.list.model.viewstate.ListViewState
import com.home.futuretalentnetworktestdemo2.list.model.viewstate.Loading
import com.home.futuretalentnetworktestdemo2.list.model.viewstate.NetworkError
import com.home.futuretalentnetworktestdemo2.list.model.viewstate.Success
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val repository: ListRepository
) : ViewModel() {

    private val viewStateLiveData: MutableLiveData<ListViewState> = MutableLiveData()

    fun requestData() {
        viewStateLiveData.value = Loading
        val coroutineExceptionHandler =
            CoroutineExceptionHandler { _, exception ->
                onError(exception)
            }
        viewModelScope.launch(coroutineExceptionHandler) {
            val photos = repository.getList()
            viewStateLiveData.value = Success(ListViewData(photos))
        }
    }

    private fun onError(throwable: Throwable) {
        viewStateLiveData.value = NetworkError(throwable.message)
    }

    fun getHomeViewState(): LiveData<ListViewState> {
        return viewStateLiveData
    }
}
