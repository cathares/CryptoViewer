package com.cathares.cryptoviewer

import android.app.Application
import com.cathares.cryptoviewer.di.appModules
import org.koin.core.context.startKoin

class CryptoViewerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }
}