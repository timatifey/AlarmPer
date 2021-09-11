package dev.timatifey.alarmper.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.timatifey.alarmper.data.AlarmsRepository
import dev.timatifey.alarmper.data.AlarmsRepositoryImpl
import dev.timatifey.alarmper.data.cache.AlarmsDao
import dev.timatifey.alarmper.data.cache.AppDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "database"
        ).build()
    }

    @[Provides Singleton]
    fun provideDao(appDatabase: AppDataBase): AlarmsDao {
        return appDatabase.alarmsDao()
    }

    @[Provides Singleton]
    fun provideAlarmRepository(alarmsRepositoryImpl: AlarmsRepositoryImpl): AlarmsRepository =
        alarmsRepositoryImpl


}