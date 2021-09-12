package dev.timatifey.alarmper.screens.alarms_list

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.timatifey.alarmper.R
import dev.timatifey.alarmper.data.AlarmModel
import dev.timatifey.alarmper.screens.AlarmsViewModel
import dev.timatifey.alarmper.utils.showAlarmDetailsDialog
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AlarmsListFragment : Fragment(R.layout.fragment_alarms),
    AlarmsAdapter.AlarmViewHolder.Listener {

    private lateinit var rvAlarmsList: RecyclerView
    private lateinit var tvEmpty: TextView

    private lateinit var alarmsAdapter: AlarmsAdapter

    private val alarmsViewModel: AlarmsViewModel by viewModels( {requireParentFragment()} )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)

        alarmsViewModel
            .alarmList
            .onEach { list ->
                if (list.isNotEmpty()) {
                    rvAlarmsList.isVisible = true
                    tvEmpty.isVisible = false
                    alarmsAdapter.submitList(list)
                } else {
                    rvAlarmsList.isVisible = false
                    tvEmpty.isVisible = true
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun initViews(view: View) {
        view.apply {
            rvAlarmsList = findViewById(R.id.alarms_list)
            tvEmpty = findViewById(R.id.empty_list)
        }

        alarmsAdapter = AlarmsAdapter(this)

        rvAlarmsList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = alarmsAdapter
        }
    }

    override fun onAlarmCardClicked(alarm: AlarmModel) {
        requireContext().showAlarmDetailsDialog(
            initAlarm = alarm,
            onDeleteButtonClicked = {
                alarmsViewModel.deleteAlarm(alarm)
            },
            onSaveButtonClicked = { hours, minutes, alarmPower, listDaysWeekOn, newDescription, deleteAfter, vibrate ->
                val newAlarm = AlarmModel(
                    id = alarm.id,
                    hours = hours,
                    minutes = minutes,
                    isPower = alarmPower,
                    isVibrate = vibrate,
                    needDeleteAfter = deleteAfter,
                    description = newDescription,
                    daysOfWeekPower = listDaysWeekOn
                )
                alarmsViewModel.updateAlarm(newAlarm)
            })
    }

    override fun onAlarmSwitcherChange(newAlarm: AlarmModel) {
        alarmsViewModel.updateAlarm(newAlarm)
    }
}