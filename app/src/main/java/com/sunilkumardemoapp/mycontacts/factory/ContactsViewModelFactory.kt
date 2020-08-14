package com.sunilkumardemoapp.mycontacts.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sunilkumardemoapp.mycontacts.ui.home.HomeViewModel
import com.sunilkumardemoapp.weather.repositories.ContactRepositories


@Suppress("UNCHECKED_CAST")
class ContactsViewModelFactory(
    private val repository: ContactRepositories
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}
