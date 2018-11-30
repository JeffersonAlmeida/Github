package com.doublef.github.app

import android.app.Application
import com.doublef.github.features.list.MainViewModel
import com.doublef.github.features.list.Repository
import com.doublef.github.network.Network
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin

class AppTest : Application() {

    @Override
    override fun onCreate() {
        super.onCreate()
        startKoin(listOf(appModule))
    }

    private val appModule = module {
        single { MainViewModel(get()) }
        single { Repository(get()) }
        single { Network.Builder()
            .baseUrl("http://localhost:8888")
            .build()
        }
    }
}