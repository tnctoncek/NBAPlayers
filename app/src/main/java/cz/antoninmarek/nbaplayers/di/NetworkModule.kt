package cz.antoninmarek.nbaplayers.di

import cz.antoninmarek.nbaplayers.BuildConfig
import cz.antoninmarek.nbaplayers.data.remote.images.ImageApiServiceFAKE
import cz.antoninmarek.nbaplayers.data.remote.images.ImageApiClient
import cz.antoninmarek.nbaplayers.data.remote.images.ImageApiService
import cz.antoninmarek.nbaplayers.data.remote.nba.ApiService
import cz.antoninmarek.nbaplayers.data.remote.nba.NBAApiClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Provides network-related dependencies for the application.
 */
val networkModule = module {
    /**
     * Provides a singleton instance of OkHttpClient with logging and authorization interceptors.
     */
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(Interceptor { chain ->
                chain.proceed(chain.request().newBuilder()
                    .header("Authorization", BuildConfig.BALLDONTLIE_API_KEY)
                    .build())
            })
            .build()
    }

    /**
     * Provides a singleton instance of Retrofit configured with the base URL and OkHttpClient.
     */
    single {
        Retrofit.Builder()
            .baseUrl("https://api.balldontlie.io/v1/")
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    /**
     * Provides a singleton instance of NBAApiClient.
     */
    single { NBAApiClient(get()) }

    /**
     * Provides a singleton instance of ImageApiClient.
     */
    single { ImageApiClient(get()) }

    /**
     * Provides a singleton instance of ImageApiService using a fake implementation.
     */
    single<ImageApiService> { ImageApiServiceFAKE() }
}