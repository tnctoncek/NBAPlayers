package cz.antoninmarek.nbaplayers.application

import android.app.Application
import cz.antoninmarek.nbaplayers.BuildConfig
import cz.antoninmarek.nbaplayers.di.appModule
import cz.antoninmarek.nbaplayers.di.networkModule
import cz.antoninmarek.nbaplayers.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

//konfigurace koinu
class NBAPlayersApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NBAPlayersApp)

            modules(
                listOf(
                    appModule,
                    networkModule,
                    viewModelModule
                )
            )

            properties(
                mapOf(
                    "balldontlieApiKey" to BuildConfig.BALLDONTLIE_API_KEY
                )
            )

        }

    }


}