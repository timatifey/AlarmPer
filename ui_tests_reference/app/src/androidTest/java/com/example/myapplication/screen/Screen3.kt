package com.example.myapplication.screen

import com.example.myapplication.main_screens.Fragment3
import dev.timatifey.navigation_demo.R
import io.github.kakaocup.kakao.text.KButton

object Screen3 : BaseScreen<Screen3>(R.id.fragment3) {

    override val layoutId: Int = R.layout.fragment_3
    override val viewClass: Class<*> = Fragment3::class.java

    val btnToFirst = KButton { withId(R.id.bnToFirst) }

    val btnToSecond = KButton { withId(R.id.bnToSecond) }
}