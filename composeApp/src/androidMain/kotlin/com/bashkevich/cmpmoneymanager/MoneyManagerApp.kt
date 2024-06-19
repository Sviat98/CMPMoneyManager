package com.bashkevich.cmpmoneymanager

import android.app.Application
import di.appModule
import di.viewModelModule
import org.koin.core.context.startKoin

class MoneyManagerApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule+ viewModelModule)
        }
    }
}