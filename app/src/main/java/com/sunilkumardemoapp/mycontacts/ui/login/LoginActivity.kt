package com.sunilkumardemoapp.mycontacts.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sunilkumardemoapp.mycontacts.R
import com.sunilkumardemoapp.mycontacts.utils.Login
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class LoginActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()
        Login()

            return


    }
}


