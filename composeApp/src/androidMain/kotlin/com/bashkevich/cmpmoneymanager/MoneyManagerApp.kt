package com.bashkevich.cmpmoneymanager

import android.app.Application
import di.appModule
import di.databaseBuilderModule
import di.datastoreCreatorModule
import di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoneyManagerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoneyManagerApp)
            modules(appModule + viewModelModule + databaseBuilderModule + datastoreCreatorModule)
        }
    }
}