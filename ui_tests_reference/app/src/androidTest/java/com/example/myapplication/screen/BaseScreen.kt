package com.example.myapplication.screen

import androidx.annotation.IdRes
import com.example.myapplication.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.text.KButton

abstract class BaseScreen<out T : KScreen<T>>(@IdRes containerId: Int) : KScreen<T>() {

    private val container = KView { withId(containerId) }

    val navUpButton =
        KButton { withContentDescription(R.string.nav_app_bar_navigate_up_description) }

    fun isDisplayed() {
        container.isDisplayed()
    }

    fun pressUp() {
        navUpButton {
            isDisplayed()
            click()
        }
    }

}