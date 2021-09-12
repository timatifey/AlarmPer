package dev.timatifey.alarmper.screens

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.timatifey.alarmper.screens.alarms_list.AlarmsListFragment
import dev.timatifey.alarmper.screens.settings.SettingsFragment

class MainFragmentStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    enum class MainFragments(val index: Int) {
        ALARM_LIST(0),
        SETTINGS(1),
    }

    override fun getItemCount(): Int {
        return MainFragments.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            MainFragments.ALARM_LIST.index -> AlarmsListFragment()
            MainFragments.SETTINGS.index -> SettingsFragment()
            else -> throw IllegalArgumentException("Unknown position: $position")
        }
    }
}