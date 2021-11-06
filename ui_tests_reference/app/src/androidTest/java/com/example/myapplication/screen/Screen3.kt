package com.example.myapplication.screen

import com.example.myapplication.Fragment3
import com.example.myapplication.R
import io.github.kakaocup.kakao.text.KButton

object Screen3 : BaseScreen<Screen3>(R.id.fragment3) {

    override val layoutId: Int = R.layout.fragment3
    override val viewClass: Class<*> = Fragment3::class.java

    val btnToFirst = KButton { withId(R.id.bnToFirst) }

    val btnToSecond = KButton { withId(R.id.bnToSecond) }
}