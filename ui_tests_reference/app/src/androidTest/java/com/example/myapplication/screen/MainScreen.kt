package com.example.myapplication.screen

import com.example.myapplication.MainActivity
import com.example.myapplication.R
import io.github.kakaocup.kakao.drawer.KDrawerView
import io.github.kakaocup.kakao.navigation.KNavigationView

object MainScreen : BaseScreen<MainScreen>(R.id.activity_main) {

    override val layoutId: Int = R.layout.activity_main
    override val viewClass: Class<*> = MainActivity::class.java

    private val drawer = KDrawerView { withId(R.id.drawer_layout) }

    private val drawerNavView = KNavigationView { withId(R.id.drawer_nav_view) }

    fun openAboutScreen() {
        drawer.open()
        drawerNavView.navigateTo(R.id.aboutActivity)
    }
}