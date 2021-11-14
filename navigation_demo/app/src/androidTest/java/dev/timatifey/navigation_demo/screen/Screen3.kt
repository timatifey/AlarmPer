package dev.timatifey.navigation_demo.screen

import dev.timatifey.navigation_demo.R
import dev.timatifey.navigation_demo.main_screens.Fragment3
import io.github.kakaocup.kakao.text.KButton

object Screen3 : BaseScreen<Screen3>(R.id.fragment3) {

    override val layoutId: Int = R.layout.fragment3
    override val viewClass: Class<*> = Fragment3::class.java

    val btnToFirst = KButton { withId(R.id.bnToFirst) }

    val btnToSecond = KButton { withId(R.id.bnToSecond) }

    fun toFirst() {
        btnToFirst {
            isDisplayed()
            click()
        }
    }

    fun toSecond() {
        btnToSecond {
            isDisplayed()
            click()
        }
    }
}