package com.home.futuretalentnetworktestdemo2.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.futuretalentnetworktestdemo2.detail.model.repository.DetailRepository
import com.home.futuretalentnetworktestdemo2.detail.model.viewdata.DetailViewData
import com.home.futuretalentnetworktestdemo2.detail.model.viewstate.DetailViewState
import com.home.futuretalentnetworktestdemo2.detail.model.viewstate.Loading
import com.home.futuretalentnetworktestdemo2.detail.model.viewstate.NetworkError
import com.home.futuretalentnetworktestdemo2.detail.model.viewstate.Success
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {

    private var userName = ""
    private val viewStateLiveData: MutableLiveData<DetailViewState> = MutableLiveData()

    fun requestData() {
        viewStateLiveData.value = Loading
        val coroutineExceptionHandler =
            CoroutineExceptionHandler { _, exception ->
                onError(exception)
            }
        viewModelScope.launch(coroutineExceptionHandler) {
            val userDetail = repository.getUserDetail(userName)
            viewStateLiveData.value = Success(DetailViewData(userDetail))
        }
    }

    private fun onError(throwable: Throwable) {
        viewStateLiveData.value = NetworkError(throwable.message)
    }

    fun getViewState(): LiveData<DetailViewState> {
        return viewStateLiveData
    }

    fun setUserName(userName: String) {
        this.userName = userName
    }
}
