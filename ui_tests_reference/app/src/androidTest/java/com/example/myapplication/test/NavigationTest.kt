package com.example.myapplication.test

import android.content.pm.ActivityInfo
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.NoActivityResumedException
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import com.example.myapplication.main_screens.MainActivity
import com.example.myapplication.screen.MainScreen.openAboutScreen
import com.example.myapplication.screen.MainScreen.pressBack
import com.example.myapplication.screen.Screen1
import com.example.myapplication.screen.Screen2
import com.example.myapplication.screen.Screen3

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest : TestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun accessToAboutScreenFromAllOtherScreensTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            step("Check first screen is displayed and open about screen") {
                Screen1 {
                    isDisplayed()
                    openAboutScreen()
                }
                rotateActivity()
            }
            step("Check from first screen") {
                scenario(CheckAboutScreenAndPressBackScenario())
            }
            step("Check first screen is displayed, navigate to second") {
                Screen1 {
                    isDisplayed()
                    btnToSecond {
                        isDisplayed()
                        click()
                    }
                }
            }
            step("Check second screen is displayed and open about screen") {
                Screen2 {
                    isDisplayed()
                    openAboutScreen()
                }
                rotateActivity()
            }
            step("Check from second screen") {
                scenario(CheckAboutScreenAndPressBackScenario())
            }
            step("Check second screen is displayed, navigate to third") {
                Screen2 {
                    isDisplayed()
                    btnToThird {
                        isDisplayed()
                        click()
                    }
                }
            }
            step("Check third screen is displayed and open about screen") {
                Screen3 {
                    isDisplayed()
                    openAboutScreen()
                }
                rotateActivity()
            }
            step("Check from third screen") {
                scenario(CheckAboutScreenAndPressBackScenario())
            }
            step("Check third screen is displayed") {
                Screen3 {
                    isDisplayed()
                }
            }
        }
    }

    @Test
    fun fullCycleTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            step("Navigate from first to third screen") {
                scenario(FirstToThirdScenario())
            }
            step("Navigate to first screen") {
                Screen3 {
                    btnToFirst {
                        isDisplayed()
                        click()
                    }
                }
            }
            step("Check first screen is displayed") {
                Screen1 {
                    isDisplayed()
                }
            }
            step("Check press back button close app") {
                pressBackWithCheckAppClose()
            }
        }
    }

    @Test
    fun fromThirdToFirstWithBackButtonTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            step("Navigate from first to third screen") {
                scenario(FirstToThirdScenario())
                rotateActivity()
            }
            step("Navigate to second screen with back button") {
                Screen3 {
                    pressBack()
                }
            }
            step("Check second screen is displayed and navigate to first screen with back button") {
                Screen2 {
                    isDisplayed()
                    pressBack()
                }
                rotateActivity()
            }
            step("Check first screen is displayed") {
                Screen1 {
                    isDisplayed()
                }
                rotateActivity()
            }
            step("Check press back button close app") {
                pressBackWithCheckAppClose()
            }
        }
    }

    @Test
    fun fromThirdToFirstTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            step("Navigate from first to third screen") {
                scenario(FirstToThirdScenario())
            }
            rotateActivity()
            step("Navigate to second screen") {
                Screen3 {
                    btnToSecond {
                        isDisplayed()
                        click()
                    }
                }
            }
            rotateActivity()
            step("Check second screen is displayed and navigate to first screen with back button") {
                Screen2 {
                    isDisplayed()
                    pressBack()
                }
            }
            rotateActivity()
            step("Check first screen is displayed") {
                Screen1 {
                    isDisplayed()
                }
            }
            step("Check press back button close app") {
                pressBackWithCheckAppClose()
            }
        }
    }

    @Test
    fun fromSecondToFirstTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            step("Check first screen is displayed and navigate to second screen") {
                Screen1 {
                    isDisplayed()
                    btnToSecond {
                        isDisplayed()
                        click()
                    }
                }
            }
            step("Check second screen is displayed and navigate to first screen") {
                Screen2 {
                    isDisplayed()
                    btnToFirst {
                        isDisplayed()
                        click()
                    }
                }
            }
            rotateActivity()
            step("Check first screen is displayed") {
                Screen1 {
                    isDisplayed()
                }
            }
            step("Check press back button close app") {
                pressBackWithCheckAppClose()
            }
        }
    }

    private fun rotateActivity() {
        activityScenarioRule.scenario.onActivity {
            it.apply {
                requestedOrientation = when (requestedOrientation) {
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    else -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                }
            }
        }
    }

    private fun pressBackWithCheckAppClose() {
        var appClosed = false
        try {
            pressBack()
        } catch (e: NoActivityResumedException) {
            appClosed = true
        }
        assertTrue(appClosed)
    }
}