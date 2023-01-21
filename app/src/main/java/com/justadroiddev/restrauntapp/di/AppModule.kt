package com.justadroiddev.restrauntapp.di

import com.justadroiddev.restrauntapp.data.RepositoryImpl
import com.justadroiddev.restrauntapp.data.network.BaseCloudDataSource
import com.justadroiddev.restrauntapp.data.network.CloudDataSource
import com.justadroiddev.restrauntapp.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {


    @Binds
    @Singleton
    abstract fun bindCloudDataSource(dataSource: BaseCloudDataSource) : CloudDataSource


}