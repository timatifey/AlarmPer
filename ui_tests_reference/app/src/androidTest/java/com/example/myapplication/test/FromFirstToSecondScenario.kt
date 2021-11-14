package com.example.myapplication.test

import com.example.myapplication.screen.Screen1
import com.example.myapplication.screen.Screen2
import com.example.myapplication.screen.Screen3
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

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