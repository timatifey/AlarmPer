package dev.timatifey.navigation_demo.test

import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import dev.timatifey.navigation_demo.screen.Screen2
import dev.timatifey.navigation_demo.screen.Screen3

class FromFirstToThirdScenario<ScenarioData> : BaseScenario<ScenarioData>() {

    override val steps: TestContext<ScenarioData>.() -> Unit = {
        scenario(FromFirstToSecondScenario())
        step("Navigate from second to third") {
            Screen2.toThird()
        }
        step("Check third screen is displayed") {
            Screen3 {
                isDisplayed()
                navUpButton.isDisplayed()
            }
        }
    }
}