package com.example.myapplication.test

import com.example.myapplication.screen.AboutScreen
import com.example.myapplication.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class AboutScreenScenario<ScenarioData>(useBackBtn: Boolean = true) : BaseScenario<ScenarioData>() {

    override val steps: TestContext<ScenarioData>.() -> Unit = {
        MainScreen.openAboutScreen()
        AboutScreen {
            isDisplayed()
            navUpButton.isDisplayed()
            if (useBackBtn) {
                pressBack()
            } else {
                pressUp()
            }
        }
    }

}