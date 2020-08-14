package com.sunilkumardemoapp.mycontacts

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule


class ContactsApplication : MultiDexApplication(), KodeinAware {


    override fun onCreate() {
        super.onCreate()

    }





    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@ContactsApplication))



    }
}