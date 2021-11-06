package com.example.myapplication.screen

import com.example.myapplication.AboutActivity
import com.example.myapplication.R

object AboutScreen: BaseScreen<AboutScreen>(R.id.activity_about) {

    override val layoutId: Int = R.layout.activity_about
    override val viewClass: Class<*> = AboutActivity::class.java
}