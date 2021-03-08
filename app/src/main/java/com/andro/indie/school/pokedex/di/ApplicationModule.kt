package com.andro.indie.school.pokedex.di

import com.andro.indie.school.pokedex.BuildConfig
import com.andro.indie.school.pokedex.data.services.PokedexService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
        } else {
            OkHttpClient.Builder()
                .build()
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providePokedexService(retrofit: Retrofit): PokedexService {
        return retrofit.create(PokedexService::class.java)
    }

}