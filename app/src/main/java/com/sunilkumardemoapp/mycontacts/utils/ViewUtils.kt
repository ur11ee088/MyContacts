package com.sunilkumardemoapp.mycontacts.utils

import android.content.Context
import android.content.Intent
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.sunilkumardemoapp.mycontacts.MainActivity


fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

fun Context.Login() {
    Intent(this, MainActivity::class.java).also {
        startActivity(it)
    }
}

















