package ru.yanot.practicum.dagger

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yanot.practicum.data.SharedPreferenceManager
import ru.yanot.practicum.data.SharedPreferenceManagerImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindSharedPreferenceManager(sharedPreferenceManagerImpl: SharedPreferenceManagerImpl): SharedPreferenceManager
}
