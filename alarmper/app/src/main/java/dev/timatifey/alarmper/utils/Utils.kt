package dev.timatifey.alarmper.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.CheckedTextView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dev.timatifey.alarmper.R
import dev.timatifey.alarmper.data.AlarmModel

fun Window.setTransparent() {
    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    statusBarColor = Color.TRANSPARENT
    navigationBarColor = Color.BLACK
}

fun Context.showTimePickerDialog(
    initHour: Int? = null,
    initMinute: Int? = null,
    callBack: (newHours: Int, newMinutes: Int) -> Unit
) {
    TimePickerDialog(
        this,
        { _, hourOfDay, _minute ->
            callBack(hourOfDay, _minute)
        },
        initHour ?: 0,
        initMinute ?: 0,
        true
    ).show()
}


@SuppressLint("SetTextI18n")
fun Context.showAlarmDetailsDialog(
    initAlarm: AlarmModel,
    onSaveButtonClicked: (
        hours: Int,
        minutes: Int,
        alarmPower: Boolean,
        listDaysWeekOn: List<Boolean>,
        newDescription: String,
        deleteAfter: Boolean,
        vibrate: Boolean
    ) -> Unit,
    onDeleteButtonClicked: () -> Unit,
) {
    val dialog: Dialog = MaterialAlertDialogBuilder(this)
        .setView(R.layout.dialog_fragment_alarm_details)
        .show()

    dialog.apply {
        window!!.setBackgroundDrawable(
            ContextCompat.getDrawable(context, android.R.color.transparent)
        )

        val switcherAlarm: SwitchCompat = findViewById(R.id.alarm_switcher)
        switcherAlarm.isChecked = initAlarm.isPower

        val switcherDelete: SwitchCompat = findViewById(R.id.delete_after_switcher)
        switcherDelete.isChecked = initAlarm.needDeleteAfter

        val switcherVibrate: SwitchCompat = findViewById(R.id.vibrate_switcher)
        switcherVibrate.isChecked = initAlarm.isVibrate

        val etDescription: EditText = findViewById(R.id.alarm_description)
        etDescription.setText(initAlarm.description)

        val daysOfWeekCheckedTv: List<CheckedTextView> = listOf(
            findViewById(R.id.monday),
            findViewById(R.id.tuesday),
            findViewById(R.id.wednesday),
            findViewById(R.id.thursday),
            findViewById(R.id.friday),
            findViewById(R.id.saturday),
            findViewById(R.id.sunday)
        )
        daysOfWeekCheckedTv.forEachIndexed { index, checkedTextView ->
            checkedTextView.apply {
                isChecked = initAlarm.daysOfWeekPower[index]
                setOnClickListener {
                    isChecked = isChecked.not()
                }
            }
        }

        var currentHour = initAlarm.hours
        var currentMinutes = initAlarm.minutes

        findViewById<TextView>(R.id.alarm_time).apply {
            text = "$currentHour:$currentMinutes"
            setOnClickListener {
                showTimePickerDialog(
                    initHour = currentHour,
                    initMinute = currentMinutes
                ) { newHours, newMinutes ->
                    currentHour = newHours
                    currentMinutes = newMinutes
                    text = "$currentHour:$currentMinutes"
                }
            }
        }

        findViewById<AppCompatButton>(R.id.delete_btn).setOnClickListener {
            onDeleteButtonClicked()
            dialog.dismiss()
        }

        findViewById<AppCompatButton>(R.id.save_btn).setOnClickListener {
            onSaveButtonClicked(
                currentHour,
                currentMinutes,
                switcherAlarm.isChecked,
                daysOfWeekCheckedTv.map { it.isChecked },
                etDescription.text.toString(),
                switcherDelete.isChecked,
                switcherVibrate.isChecked,
            )
            dialog.dismiss()
        }

        findViewById<ImageView>(R.id.ic_close).setOnClickListener {
            dialog.dismiss()
        }
    }
}