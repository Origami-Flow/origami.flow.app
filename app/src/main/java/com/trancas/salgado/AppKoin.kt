package com.trancas.salgado

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AppKoin : Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@AppKoin)

            modules(appModule)
        }
    }
}