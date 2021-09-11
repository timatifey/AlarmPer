package dev.timatifey.alarmper.screens

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.timatifey.alarmper.R
import dev.timatifey.alarmper.data.AlarmModel
import dev.timatifey.alarmper.utils.showAlarmDetailsDialog

class MainFragment : Fragment(R.layout.fragment_main), MainView {

    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var ivPlusButton: ImageView

    private lateinit var stateAdapter: MainFragmentStateAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        view.apply {
            viewPager = findViewById(R.id.view_pager)
            bottomNavView = findViewById(R.id.bottom_nav_view)
            ivPlusButton = findViewById(R.id.plus_button)
        }

        stateAdapter = MainFragmentStateAdapter(this)
        viewPager.apply {
            adapter = stateAdapter
            isUserInputEnabled = false
        }

        bottomNavView.apply {
            selectedItemId = R.id.alarmsFragment
            itemIconTintList = null
            setOnItemSelectedListener { item ->
                viewPager.currentItem = when (item.itemId) {
                    R.id.alarmsFragment -> MainFragmentStateAdapter.MainFragments.ALARM_LIST.index
                    R.id.settingsFragment -> MainFragmentStateAdapter.MainFragments.SETTINGS.index
                    else -> throw IllegalArgumentException("Unknown item id")
                }
                return@setOnItemSelectedListener true
            }
        }

        ivPlusButton.setOnClickListener {
            Toast.makeText(context, "Click on plus button", Toast.LENGTH_SHORT).show()
            requireContext().showAlarmDetailsDialog(
                initAlarm = AlarmModel(
                    hours = 16,
                    minutes = 34,
                    isPower = true,
                    isVibrate = false,
                    needDeleteAfter = true,
                    description = "My super description!",
                    daysOfWeekPower = listOf(false, false, true, true, false, true, true)
                ),
                onDeleteButtonClicked = {
                    Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show()
                },
                onSaveButtonClicked = { hours, minutes, alarmPower, listDaysWeekOn, newDescription, deleteAfter, vibrate ->
                    val newAlarm = AlarmModel(
                        hours = hours,
                        minutes = minutes,
                        isPower = alarmPower,
                        isVibrate = vibrate,
                        needDeleteAfter = deleteAfter,
                        description = newDescription,
                        daysOfWeekPower = listDaysWeekOn
                    )
                    Toast.makeText(context, "New alarm: $newAlarm", Toast.LENGTH_SHORT).show()
                })
        }
    }

    override fun isBottomBarVisible(isVisible: Boolean) {
        bottomNavView.animateVisibility(isVisible)
        ivPlusButton.animateVisibility(isVisible)
    }

    private fun View.animateVisibility(isVisible: Boolean) {
        if (isVisible) {
            this.isVisible = true
            alpha = 0.0f
            animate()
                .translationY(0f)
                .alpha(1.0f)
                .setListener(null)
        } else {
            animate()
                .translationY(height.toFloat())
                .alpha(0.0f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        this@animateVisibility.isVisible = false
                    }
                })
        }
    }

    override fun setBottomBarSelected(itemId: Int) {
        bottomNavView.selectedItemId = itemId
    }

    override fun setViewPagerSelected(fragment: MainFragmentStateAdapter.MainFragments) {
        viewPager.currentItem = fragment.index
    }
}