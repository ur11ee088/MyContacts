package com.sunilkumardemoapp.weather.repositories

import com.sunilkumardemoapp.mycontacts.networkcall.ContactsApiCalls
import com.sunilkumardemoapp.mycontacts.networkcall.SafeApiRequest


class ContactRepositories(private val api: ContactsApiCalls) : SafeApiRequest() {
    suspend fun getContactsList(page: Int,results:Int) =
        apiRequest { api.getContactsList(page,results) }
}