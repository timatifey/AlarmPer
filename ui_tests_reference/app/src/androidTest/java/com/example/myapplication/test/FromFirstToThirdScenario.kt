package com.example.myapplication.test

import com.example.myapplication.screen.Screen2
import com.example.myapplication.screen.Screen3
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

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