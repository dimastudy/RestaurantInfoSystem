package com.justadroiddev.restrauntapp.di

import com.justadroiddev.restrauntapp.data.RepositoryImpl
import com.justadroiddev.restrauntapp.data.network.BaseCloudDataSource
import com.justadroiddev.restrauntapp.data.network.CloudDataSource
import com.justadroiddev.restrauntapp.data.network.RestaurantApi
import com.justadroiddev.restrauntapp.data.network.ignoreAllSSLErrors
import com.justadroiddev.restrauntapp.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRestaurantApi() : RestaurantApi {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .ignoreAllSSLErrors()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestaurantApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(cloudDataSource: CloudDataSource) : Repository = RepositoryImpl(cloudDataSource)


}