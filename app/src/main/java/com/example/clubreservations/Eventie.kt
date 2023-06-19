package com.example.clubreservations

import android.app.Application
import com.example.clubreservations.di.databaseModule
import com.example.clubreservations.di.repositoryModule
import com.example.clubreservations.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class Eventie : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@Eventie)
            modules(
                databaseModule,
                repositoryModule,
                viewmodelModule
            )
        }
    }

    companion object {
        lateinit var application: Application
    }

}