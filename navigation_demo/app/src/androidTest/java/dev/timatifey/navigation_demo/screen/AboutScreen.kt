package dev.timatifey.navigation_demo.screen

import dev.timatifey.navigation_demo.R
import dev.timatifey.navigation_demo.about.AboutActivity

object AboutScreen: BaseScreen<AboutScreen>(R.id.activity_about) {

    override val layoutId: Int = R.layout.activity_about
    override val viewClass: Class<*> = AboutActivity::class.java
}