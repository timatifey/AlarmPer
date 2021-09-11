package dev.timatifey.alarmper.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.timatifey.alarmper.data.AlarmCacheModel
import dev.timatifey.alarmper.data.AlarmModel

@Database(entities = [AlarmCacheModel::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun alarmsDao(): AlarmsDao
}