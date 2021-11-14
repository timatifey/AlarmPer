package dev.timatifey.navigation_demo.screen

import dev.timatifey.navigation_demo.R
import dev.timatifey.navigation_demo.main_screens.MainActivity
import io.github.kakaocup.kakao.drawer.KDrawerView
import io.github.kakaocup.kakao.navigation.KNavigationView

object MainScreen : BaseScreen<MainScreen>(R.id.drawer_layout) {

    override val layoutId: Int = R.layout.activity_main
    override val viewClass: Class<*> = MainActivity::class.java

    private val drawer = KDrawerView { withId(R.id.drawer_layout) }

    private val drawerNavView = KNavigationView { withId(R.id.drawer_nav_view) }

    fun openAboutScreen() {
        drawer.open()
        drawerNavView.navigateTo(R.id.aboutActivity)
    }
}