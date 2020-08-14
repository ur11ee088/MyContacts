package com.sunilkumardemoapp.mycontacts.ui.home.adapter

import androidx.paging.DataSource
import com.sunilkumardemoapp.weather.repositories.ContactRepositories
import com.sunilkumardemoapp.mycontacts.ui.home.model.Result


class ContactsDataSourceFactory(
    private val repository: ContactRepositories
) : DataSource.Factory<Int, Result>() {

    override fun create(): DataSource<Int, Result> {
        return ContactsDataSource(repository)
    }
}