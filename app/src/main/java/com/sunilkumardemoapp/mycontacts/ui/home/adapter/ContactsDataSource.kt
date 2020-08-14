package com.sunilkumardemoapp.mycontacts.ui.home.adapter

import androidx.paging.PageKeyedDataSource
import com.sunilkumardemoapp.weather.exceptions.ApiException
import com.sunilkumardemoapp.weather.exceptions.NoInternetException
import com.sunilkumardemoapp.weather.repositories.ContactRepositories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import com.sunilkumardemoapp.mycontacts.ui.home.model.Result

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ContactsDataSource(
    private val repository: ContactRepositories
) : PageKeyedDataSource<Int, Result>(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    companion object {
        const val FIRST_PAGE = 1
        const val PAGE_SIZE = 1000
        const val PAGE_LIST_DATA = 50
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        launch {
            try {
                callback.onResult(
                    repository.getContactsList(FIRST_PAGE,PAGE_LIST_DATA).results,
                    null,
                    FIRST_PAGE + 1
                )
            } catch (e: ApiException) {
            } catch (e: NoInternetException) {
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        launch {
            try {
                val mResponse = repository.getContactsList(params.key,PAGE_LIST_DATA)
                val key = if (params.requestedLoadSize>40) params.key + 1 else 0
                callback.onResult(mResponse.results, key)
            } catch (e: ApiException) {
            } catch (e: NoInternetException) {
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        launch {
            try {
                val mList =
                    repository.getContactsList(params.key,PAGE_LIST_DATA).results
                val adjacentKey = if (params.key > 1) params.key - 1 else null
                callback.onResult(mList, adjacentKey)
            } catch (e: ApiException) {
            } catch (e: NoInternetException) {
            }
        }
    }
}