package dev.timatifey.alarmper.data

data class AlarmModel(
    val hours: Int,
    val minutes: Int,
    val description: String,
    val isPower: Boolean,
    val daysOfWeekPower: List<Boolean>,
    val needDeleteAfter: Boolean,
    val isVibrate: Boolean,
)
