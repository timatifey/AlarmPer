package dev.timatifey.navigation_demo.test

import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import dev.timatifey.navigation_demo.screen.Screen1
import dev.timatifey.navigation_demo.screen.Screen2

class FromFirstToSecondScenario<ScenarioData> : BaseScenario<ScenarioData>() {

    override val steps: TestContext<ScenarioData>.() -> Unit = {
        step("Navigate from first to second") {
            Screen1 {
                isDisplayed()
                navUpButton.doesNotExist()
                toSecond()
            }
        }
        step("Check is second screen") {
            Screen2 {
                isDisplayed()
                navUpButton.isDisplayed()
            }
        }
    }
}