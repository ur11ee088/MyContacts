package com.sunilkumardemoapp.mycontacts.ui.home.model

import java.io.Serializable

data class Info(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
):Serializable