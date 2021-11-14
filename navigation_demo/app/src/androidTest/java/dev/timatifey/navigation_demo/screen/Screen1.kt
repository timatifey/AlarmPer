package dev.timatifey.navigation_demo.screen

import dev.timatifey.navigation_demo.R
import dev.timatifey.navigation_demo.main_screens.Fragment1
import io.github.kakaocup.kakao.text.KButton

object Screen1 : BaseScreen<Screen1>(R.id.fragment1) {

    override val layoutId: Int = R.layout.fragment1
    override val viewClass: Class<*> = Fragment1::class.java

    val btnToSecond = KButton { withId(R.id.bnToSecond) }

    fun toSecond() {
        btnToSecond {
            isDisplayed()
            click()
        }
    }
}