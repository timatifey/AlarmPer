package dev.timatifey.alarmper.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.timatifey.alarmper.data.AlarmCacheModel.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class AlarmCacheModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var hours: Int,
    var minutes: Int,

    var description: String,

    var isPower: Boolean,

    var monday: Boolean = false,
    var tuesday: Boolean = false,
    var wednesday: Boolean = false,
    var thursday: Boolean = false,
    var friday: Boolean = false,
    var saturday: Boolean = false,
    var sunday: Boolean = false,

    var needDeleteAfter: Boolean,
    var isVibrate: Boolean,
) {
    companion object {
        const val TABLE_NAME = "alarms_table"
    }
}

data class AlarmModel(
    val id: Int,

    var hours: Int,
    var minutes: Int,

    var description: String,

    var isPower: Boolean,
    var daysOfWeekPower: List<Boolean>,

    var needDeleteAfter: Boolean,
    var isVibrate: Boolean,
) {
    companion object {
        const val EXTRA_POWER = "extra_power"
        const val EXTRA_DELETE = "extra_delete"
        const val EXTRA_VIBRATE = "extra_vibrate"
        const val EXTRA_DAYS = "extra_days"
        const val EXTRA_DESCRIPTION = "extra_description"
    }
}

fun AlarmModel.toCache(): AlarmCacheModel =
    AlarmCacheModel(
        id,
        hours,
        minutes,
        description,
        isPower,
        daysOfWeekPower[0],
        daysOfWeekPower[1],
        daysOfWeekPower[2],
        daysOfWeekPower[3],
        daysOfWeekPower[4],
        daysOfWeekPower[5],
        daysOfWeekPower[6],
        needDeleteAfter,
        isVibrate
    )


fun AlarmCacheModel.toModel(): AlarmModel =
    AlarmModel(
        id,
        hours,
        minutes,
        description,
        isPower,
        listOf(
            monday,
            tuesday,
            wednesday,
            thursday,
            friday,
            saturday,
            sunday
        ),
        needDeleteAfter,
        isVibrate
    )