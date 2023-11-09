package com.example.movieapp.di

import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .readTimeout(timeout = 10000, unit = TimeUnit.SECONDS)
            .writeTimeout(timeout = 10000, unit = TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofitInstance(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.MOVIE_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesMovieApi(
        retrofit: Retrofit
    ): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}