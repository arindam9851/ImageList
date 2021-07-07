package com.example.imagelist.di

import com.example.imagelist.data.api.ApiInterface
import com.example.imagelist.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        apiInterface: ApiInterface
    ): MainRepository {
        return MainRepository(apiInterface)
    }
}