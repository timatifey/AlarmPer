package com.example.myapplication.screen

import com.example.myapplication.about.ActivityAbout
import dev.timatifey.navigation_demo.R

object AboutScreen: BaseScreen<AboutScreen>(R.id.activity_about) {

    override val layoutId: Int = R.layout.activity_about
    override val viewClass: Class<*> = ActivityAbout::class.java
}