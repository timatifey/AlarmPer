package dev.timatifey.alarmper.data

import dev.timatifey.alarmper.data.cache.AlarmsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface AlarmsRepository {
    fun getAlarms(): Flow<List<AlarmModel>>
    suspend fun insertAlarm(alarmModel: AlarmModel)
    suspend fun deleteAlarm(alarmModel: AlarmModel)
    suspend fun updateAlarm(alarmModel: AlarmModel)
}

class AlarmsRepositoryImpl @Inject constructor(
    private val dao: AlarmsDao
) : AlarmsRepository {

    override fun getAlarms(): Flow<List<AlarmModel>> {
        return dao.getAllAlarms().map {
            it.map { alarmCacheModel ->
                alarmCacheModel.toModel()
            }
        }
    }

    override suspend fun insertAlarm(alarmModel: AlarmModel) {
        dao.insertAlarm(alarmModel.toCache())
    }

    override suspend fun deleteAlarm(alarmModel: AlarmModel) {
        dao.deleteAlarm(alarmModel.toCache())
    }

    override suspend fun updateAlarm(alarmModel: AlarmModel) {
        dao.updateAlarm(alarmModel.toCache())
    }
}