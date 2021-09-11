package dev.timatifey.alarmper.screens.settings

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import dev.timatifey.alarmper.BuildConfig
import dev.timatifey.alarmper.R
import dev.timatifey.alarmper.screens.MainView

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var tvVersion: TextView
    private lateinit var swNotification: SwitchCompat

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        view.apply {
            tvVersion = findViewById(R.id.version)
            swNotification = findViewById(R.id.notifications_switcher)
        }

        tvVersion.text = BuildConfig.VERSION_NAME

        swNotification.apply {
            isChecked = true
            setOnCheckedChangeListener { _, isChecked ->
                Toast.makeText(
                    context,
                    "Change notifications, but not change visibility bottom bar",
                    Toast.LENGTH_SHORT
                ).show()
                (requireParentFragment() as MainView).isBottomBarVisible(isChecked)
            }
        }
    }
}