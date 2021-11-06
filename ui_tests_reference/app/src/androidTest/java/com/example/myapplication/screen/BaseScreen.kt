package com.example.myapplication.screen

import androidx.annotation.IdRes
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView

abstract class BaseScreen<out T : KScreen<T>>(@IdRes containerId: Int) : KScreen<T>() {

    private val container = KView { withId(containerId) }

    fun isDisplayed() {
        container.isDisplayed()
    }

}