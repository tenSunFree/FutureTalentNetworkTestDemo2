package com.home.futuretalentnetworktestdemo2.pagination.model.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.home.futuretalentnetworktestdemo2.pagination.model.pojo.RepositoriesPojo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class PaginationPageKeyedDataSource @Inject constructor(
    private val scope: CoroutineScope, private val repository: PaginationRepository
) : PageKeyedDataSource<Int, RepositoriesPojo>() {

    companion object {
        const val FIRST_PAGE = 0
        const val PAGE_SIZE = 40
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RepositoriesPojo>
    ) {
        scope.launch {
            try {
                val response =
                    repository.getList(FIRST_PAGE)
                when {
                    response.isSuccessful -> {
                        callback.onResult(
                            response.body()!!, null,
                            FIRST_PAGE + 1
                        )
                    }
                }
            } catch (exception: Exception) {
                Log.e("loadInitial", "1" + exception.message)
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, RepositoriesPojo>
    ) {
        scope.launch {
            try {
                val response =
                    repository.getList(params.key)
                when {
                    response.isSuccessful -> {
                        val key: Int? = if (response.body()?.isNotEmpty()!!) {
                            params.key + 1
                        } else {
                            null
                        }
                        callback.onResult(response.body()!!, key)
                    }
                }
            } catch (exception: Exception) {
                Log.e("loadAfter", "1" + exception.message)
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, RepositoriesPojo>
    ) {
        scope.launch {
            try {
                val response =
                    repository.getList(params.key)
                val key: Int? = if (params.key > 1) {
                    params.key - 1
                } else {
                    null
                }
                when {
                    response.isSuccessful -> {
                        callback.onResult(response.body()!!, key)
                    }
                }
            } catch (exception: Exception) {
                Log.e("loadBefore", "1" + exception.message)
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}