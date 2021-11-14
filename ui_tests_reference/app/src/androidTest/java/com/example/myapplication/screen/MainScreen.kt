package com.example.myapplication.screen

import com.example.myapplication.main_screens.MainActivity
import dev.timatifey.navigation_demo.R
import io.github.kakaocup.kakao.drawer.KDrawerView
import io.github.kakaocup.kakao.navigation.KNavigationView

object MainScreen : BaseScreen<MainScreen>(R.id.drawer) {

    override val layoutId: Int = R.layout.activity_main
    override val viewClass: Class<*> = MainActivity::class.java

    private val drawer = KDrawerView { withId(R.id.drawer) }

    private val drawerNavView = KNavigationView { withId(R.id.drawer_nav) }

    fun openAboutScreen() {
        drawer.open()
        drawerNavView.navigateTo(R.id.nav_about)
    }
}