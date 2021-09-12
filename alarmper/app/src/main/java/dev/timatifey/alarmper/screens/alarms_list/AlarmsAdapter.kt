package dev.timatifey.alarmper.screens.alarms_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.timatifey.alarmper.R
import dev.timatifey.alarmper.data.AlarmModel

class AlarmsAdapter(private val listener: AlarmViewHolder.Listener) :
    ListAdapter<AlarmModel, AlarmsAdapter.AlarmViewHolder>(AlarmDiffUtil()) {

    class AlarmViewHolder(private val listener: Listener, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val tvAlarmTime: TextView = itemView.findViewById(R.id.alarm_time)
        private val switcherAlarm: SwitchCompat = itemView.findViewById(R.id.alarm_switcher)
        private val tvDescription: TextView = itemView.findViewById(R.id.alarm_description)
        private val daysOfWeekCheckedTv: List<CheckedTextView> = with(itemView) {
            listOf(
                findViewById(R.id.monday),
                findViewById(R.id.tuesday),
                findViewById(R.id.wednesday),
                findViewById(R.id.thursday),
                findViewById(R.id.friday),
                findViewById(R.id.saturday),
                findViewById(R.id.sunday)
            )
        }

        interface Listener {
            fun onAlarmCardClicked(alarm: AlarmModel)
            fun onAlarmSwitcherChange(newAlarm: AlarmModel)
        }

        init {
            itemView.setOnClickListener {
                listener.onAlarmCardClicked(currentItem)
            }
            switcherAlarm.setOnCheckedChangeListener { _, isChecked ->
                currentItem.isPower = isChecked
                listener.onAlarmSwitcherChange(currentItem)
            }
        }

        private lateinit var currentItem: AlarmModel

        @SuppressLint("SetTextI18n")
        fun bind(alarmModel: AlarmModel) {
            currentItem = alarmModel
            alarmModel.apply {
                tvAlarmTime.text = "$hours:$minutes"
                tvDescription.apply {
                    isVisible = description.isNotEmpty()
                    text = description
                }
                switcherAlarm.isChecked = isPower
                daysOfWeekCheckedTv.forEachIndexed { index, checkedTextView ->
                    checkedTextView.isChecked = daysOfWeekPower[index]
                }
            }
        }

        fun bind(bundle: Bundle) {
            bundle.apply {
                if (containsKey(AlarmModel.EXTRA_POWER)) {
                    getBoolean(AlarmModel.EXTRA_POWER).let {
                        switcherAlarm.isChecked = it
                        currentItem.isPower = it
                    }
                }
                if (containsKey(AlarmModel.EXTRA_VIBRATE)) {
                    getBoolean(AlarmModel.EXTRA_VIBRATE).let { currentItem.isVibrate = it }
                }
                if (containsKey(AlarmModel.EXTRA_DELETE)) {
                    getBoolean(AlarmModel.EXTRA_DELETE).let { currentItem.needDeleteAfter = it }
                }
                getBooleanArray(AlarmModel.EXTRA_DAYS)?.let { array ->
                    daysOfWeekCheckedTv.forEachIndexed { index, checkedTextView ->
                        checkedTextView.isChecked = array[index]
                    }
                }
                getString(AlarmModel.EXTRA_DESCRIPTION)?.let {
                    currentItem.description = it
                    tvDescription.text = it
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_alarm_item, parent, false)
        return AlarmViewHolder(listener, view)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: AlarmViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNullOrEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.bind(payloads.last() as Bundle)
        }

    }

}

class AlarmDiffUtil : DiffUtil.ItemCallback<AlarmModel>() {

    override fun areItemsTheSame(oldItem: AlarmModel, newItem: AlarmModel): Boolean {
        if (oldItem::class != newItem::class) return false
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlarmModel, newItem: AlarmModel): Boolean {
        if (oldItem::class != newItem::class) return false
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: AlarmModel, newItem: AlarmModel): Any? {
        if (oldItem::class != newItem::class) return false

        if (oldItem.hours != newItem.hours || oldItem.minutes != newItem.minutes) {
            return super.getChangePayload(oldItem, newItem)
        }

        val diff = Bundle()

        if (oldItem.isPower != newItem.isPower) {
            diff.putBoolean(AlarmModel.EXTRA_POWER, newItem.isPower)
        }

        if (oldItem.needDeleteAfter != newItem.needDeleteAfter) {
            diff.putBoolean(AlarmModel.EXTRA_DELETE, newItem.needDeleteAfter)
        }

        if (oldItem.isVibrate != newItem.isVibrate) {
            diff.putBoolean(AlarmModel.EXTRA_VIBRATE, newItem.isVibrate)
        }

        if (oldItem.description != newItem.description) {
            diff.putString(AlarmModel.EXTRA_DESCRIPTION, newItem.description)
        }

        if (oldItem.daysOfWeekPower != newItem.daysOfWeekPower) {
            diff.putBooleanArray(AlarmModel.EXTRA_DAYS, newItem.daysOfWeekPower.toBooleanArray())
        }

        if (diff.size() == 0) {
            return super.getChangePayload(oldItem, newItem)
        }

        return diff
    }

}
