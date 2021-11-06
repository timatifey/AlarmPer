package com.example.myapplication.test

import androidx.test.espresso.action.ViewActions.click
import com.example.myapplication.screen.Screen1
import com.example.myapplication.screen.Screen2
import com.example.myapplication.screen.Screen3
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class FirstToThirdScenario<ScenarioData> : BaseScenario<ScenarioData>() {

    override val steps: TestContext<ScenarioData>.() -> Unit = {
        step("1. Check first screen is displayed and navigate to second screen") {
            Screen1 {
                isDisplayed()
                btnToSecond {
                    isDisplayed()
                    click()
                }
            }
        }
        step("2. Check second screen is displayed and navigate to third screen") {
            Screen2 {
                isDisplayed()
                btnToThird {
                    isDisplayed()
                    click()
                }
            }
        }
        step("3. Check third screen is displayed") {
            Screen3 {
                isDisplayed()
            }
        }
    }
}