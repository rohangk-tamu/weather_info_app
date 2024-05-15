package com.example.weatherinfo.data.di

import android.content.Context
import com.example.weatherinfo.domain.location.LocationTracker
import com.example.weatherinfo.data.location.DefaultLocationTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    fun provideLocationTracker(defaultLocationTracker: DefaultLocationTracker): LocationTracker {
        return defaultLocationTracker
    }

        @Provides
        @Singleton
        fun provideContext(@ApplicationContext context: Context): Context {
            return context
        }
    }
