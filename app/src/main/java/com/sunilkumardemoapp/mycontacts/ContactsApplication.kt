package com.sunilkumardemoapp.mycontacts

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import com.sunilkumardemoapp.mycontacts.factory.ContactsViewModelFactory
import com.sunilkumardemoapp.mycontacts.networkcall.ContactsApiCalls
import com.sunilkumardemoapp.mycontacts.networkcall.NetworkConnectionInterceptor
import com.sunilkumardemoapp.weather.repositories.ContactRepositories
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class ContactsApplication : MultiDexApplication(), KodeinAware {


    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)

    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@ContactsApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from provider { ContactsApiCalls(instance()) }


        /**
         * REPOSITORIES
         * =============================================
         * Bind all the repositories that your create here
         * */
        bind() from provider { ContactRepositories(instance()) }


        /**
         * VIEWMODEL FACTORIES
         * =============================================
         * Bind all the ViewModel Factories that your create here
         * */

        bind() from provider { ContactsViewModelFactory(instance()) }


    }
}