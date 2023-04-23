package com.example.fourthsemestr1.di.module

import androidx.viewbinding.BuildConfig
import com.example.fourthsemestr1.data.api.Api
import com.example.fourthsemestr1.di.annotation.ApiKey
import com.example.fourthsemestr1.di.annotation.Lang
import com.example.fourthsemestr1.di.annotation.Logger
import com.example.fourthsemestr1.di.annotation.Units
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Qualifier

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val API_KEY = "b27d37e0816963e5e76c1ac07836193f"
private const val QUERY_API_KEY = "appid"
private const val QUERY_LANG = "lang"
private const val LANG = "RU"
private const val QUERY_UNITS = "units"
private const val UNITS = "metric"

@Module
class NetModule {

    @Provides
    @ApiKey
    fun apiKeyInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val newUrl = request.url.newBuilder()
            .addQueryParameter(QUERY_API_KEY, API_KEY)
            .build()

        chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }

    @Provides
    @Lang
    fun langInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val newUrl = request.url.newBuilder()
            .addQueryParameter(QUERY_LANG, LANG)
            .build()

        chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }

    @Provides
    @Units
    fun unitsInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val newUrl = request.url.newBuilder()
            .addQueryParameter(QUERY_UNITS, UNITS)
            .build()

        chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }

    @Provides
    @Logger
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
    }

    @Provides
    fun provideCacheDirectory(): File {
        return File("cache");
    }

    @Qualifier
    annotation class CacheMaxSize

    @CacheMaxSize
    @Provides
    fun provideCacheMaxSize(): Long {
        return 50 * 1024 * 1024;
    }

    @Provides
    fun provideCache(
        cacheDirectory: File,
        @CacheMaxSize cacheMaxSize: Long
    ): Cache {
        return Cache(cacheDirectory, cacheMaxSize)
    }

    @Provides
    fun provideOkhttpClient(
        cache: Cache,
        @ApiKey apiKeyInterceptor: Interceptor,
        @Lang langInterceptor: Interceptor,
        @Units unitsInterceptor: Interceptor,
        @Logger loggingInterceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(langInterceptor)
            .addInterceptor(unitsInterceptor)
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(
                        loggingInterceptor
                    )
                }
            }
            .build()

    @Provides
    fun provideApi(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        gsonConverter: GsonConverterFactory,
    ): Api =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverter)
            .build()
            .create(Api::class.java)

    @Provides
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()
}