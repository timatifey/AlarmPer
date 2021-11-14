package dev.timatifey.navigation_demo.screen

import dev.timatifey.navigation_demo.R
import dev.timatifey.navigation_demo.main_screens.Fragment2
import io.github.kakaocup.kakao.text.KButton

object Screen2 : BaseScreen<Screen2>(R.id.fragment2) {

    override val layoutId: Int = R.layout.fragment2
    override val viewClass: Class<*> = Fragment2::class.java

    val btnToFirst = KButton { withId(R.id.bnToFirst) }

    val btnToThird = KButton { withId(R.id.bnToThird) }

    fun toFirst() {
        btnToFirst {
            isDisplayed()
            click()
        }
    }

    fun toThird() {
        btnToThird {
            isDisplayed()
            click()
        }
    }
}