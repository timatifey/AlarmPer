package com.example.myapplication.test

import com.example.myapplication.screen.AboutScreen
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class CheckAboutScreenAndPressBackScenario<ScenarioData> : BaseScenario<ScenarioData>() {

    override val steps: TestContext<ScenarioData>.() -> Unit = {
        step("Check about screen is displayed and press back button") {
            AboutScreen {
                isDisplayed()
                pressBack()
            }
        }
    }

}