package com.example.myapplication.screen

import com.example.myapplication.Fragment1
import com.example.myapplication.R
import io.github.kakaocup.kakao.text.KButton

object Screen1 : BaseScreen<Screen1>(R.id.fragment1) {

    override val layoutId: Int = R.layout.fragment1
    override val viewClass: Class<*> = Fragment1::class.java

    val btnToSecond = KButton { withId(R.id.bnToSecond) }
}