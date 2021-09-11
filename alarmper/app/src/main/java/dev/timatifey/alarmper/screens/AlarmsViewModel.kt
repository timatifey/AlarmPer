package dev.timatifey.alarmper.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.timatifey.alarmper.data.AlarmModel
import dev.timatifey.alarmper.data.AlarmsRepository
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmsViewModel @Inject constructor(
    private val alarmsRepository: AlarmsRepository,
) : ViewModel() {

    val alarmList: SharedFlow<List<AlarmModel>> = alarmsRepository
        .getAlarms()
        .shareIn(viewModelScope, started = SharingStarted.Lazily)

    fun insertAlarm(alarmModel: AlarmModel) {
        viewModelScope.launch {
            alarmsRepository.insertAlarm(alarmModel)
        }
    }

    fun updateAlarm(newAlarmModel: AlarmModel) {
        viewModelScope.launch {
            alarmsRepository.updateAlarm(newAlarmModel)
        }
    }

    fun deleteAlarm(alarm: AlarmModel) {
        viewModelScope.launch {
            alarmsRepository.deleteAlarm(alarm)
        }
    }

}