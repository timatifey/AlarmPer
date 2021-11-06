package com.example.myapplication.screen

import dev.timatifey.navigation_demo.R
import com.example.myapplication.main_screens.Fragment1
import io.github.kakaocup.kakao.text.KButton

object Screen1 : BaseScreen<Screen1>(R.id.fragment1) {

    override val layoutId: Int = R.layout.fragment_1
    override val viewClass: Class<*> = Fragment1::class.java

    val btnToSecond = KButton { withId(R.id.bnToSecond) }
}