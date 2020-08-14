package com.sunilkumardemoapp.mycontacts.ui.home.model

import java.io.Serializable

data class ContactsResponse(
    val info: Info,
    val results: List<Result>
):Serializable