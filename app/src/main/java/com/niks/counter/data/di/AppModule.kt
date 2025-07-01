package com.niks.counter.data.di

import android.content.Context
import com.niks.counter.domain.repository.SoundPlayerRepository
import com.niks.counter.data.repository.SoundPlayerRepositoryImpl
import com.niks.counter.data.repository.DataStoreRepositoryImpl
import com.niks.counter.data.repository.VibratorRepositoryImpl
import com.niks.counter.domain.repository.DataStoreRepository
import com.niks.counter.domain.repository.VibratorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository {
        return DataStoreRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideSoundPlayerRepository(@ApplicationContext context: Context): SoundPlayerRepository {
        return SoundPlayerRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideVibratorRepository(@ApplicationContext context: Context): VibratorRepository {
        return VibratorRepositoryImpl(context)
    }
}