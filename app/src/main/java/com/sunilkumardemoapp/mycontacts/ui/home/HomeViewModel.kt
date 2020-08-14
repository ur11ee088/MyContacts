package com.sunilkumardemoapp.mycontacts.ui.home

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sunilkumardemoapp.mycontacts.base.BaseViewModel
import com.sunilkumardemoapp.mycontacts.ui.home.adapter.ContactsDataSource
import com.sunilkumardemoapp.mycontacts.ui.home.adapter.ContactsDataSourceFactory
import com.sunilkumardemoapp.mycontacts.ui.home.model.Result
import com.sunilkumardemoapp.weather.repositories.ContactRepositories

class HomeViewModel(private val repository: ContactRepositories) : BaseViewModel() {

    val mPagedList: LiveData<PagedList<Result>>
    private val notifictionDataSourceFactory: ContactsDataSourceFactory =
        ContactsDataSourceFactory(repository)

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ContactsDataSource.PAGE_SIZE).build()
        mPagedList =
            LivePagedListBuilder(notifictionDataSourceFactory, pagedListConfig)
                .build()
    }
}