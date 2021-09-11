package dev.timatifey.alarmper.screens

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dev.timatifey.alarmper.R
import dev.timatifey.alarmper.data.AlarmModel
import dev.timatifey.alarmper.utils.generateAlarmModel
import dev.timatifey.alarmper.utils.showAlarmDetailsDialog

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), MainView {

    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var ivPlusButton: ImageView

    private lateinit var stateAdapter: MainFragmentStateAdapter

    private val alarmsViewModel: AlarmsViewModel by viewModels()

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
            alarmsViewModel.insertAlarm(generateAlarmModel())
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