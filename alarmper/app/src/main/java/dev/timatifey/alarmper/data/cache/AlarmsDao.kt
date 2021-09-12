package dev.timatifey.alarmper.data.cache

import androidx.room.*
import dev.timatifey.alarmper.data.AlarmCacheModel
import dev.timatifey.alarmper.data.AlarmModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmsDao {

    @Query("SELECT * FROM ${AlarmCacheModel.TABLE_NAME} ORDER BY hours ASC, minutes ASC")
    fun getAllAlarms(): Flow<List<AlarmCacheModel>>

    @Insert(entity = AlarmCacheModel::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlarm(alarm: AlarmCacheModel)

    @Delete(entity = AlarmCacheModel::class)
    suspend fun deleteAlarm(alarm: AlarmCacheModel)

    @Update(entity = AlarmCacheModel::class)
    suspend fun updateAlarm(alarm: AlarmCacheModel)
}