package dev.timatifey.navigation_demo.test

import android.content.pm.ActivityInfo
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.NoActivityResumedException
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dev.timatifey.navigation_demo.main_screens.MainActivity
import dev.timatifey.navigation_demo.screen.MainScreen.pressBack
import dev.timatifey.navigation_demo.screen.MainScreen.pressUp
import dev.timatifey.navigation_demo.screen.Screen1
import dev.timatifey.navigation_demo.screen.Screen2
import dev.timatifey.navigation_demo.screen.Screen3
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest : TestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun accessToAboutScreenFromOtherScreensBackTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            step("First screen") {
                Screen1 {
                    isDisplayed()
                    scenario(AboutScreenScenario())
                    rotateActivity()
                    isDisplayed()
                    toSecond()
                }
            }
            step("Second screen") {
                Screen2 {
                    isDisplayed()
                    scenario(AboutScreenScenario())
                    rotateActivity()
                    isDisplayed()
                    toThird()
                }
            }
            step("Third screen") {
                Screen3 {
                    isDisplayed()
                    scenario(AboutScreenScenario())
                    rotateActivity()
                    isDisplayed()
                }
            }
        }
    }

    @Test
    fun accessToAboutScreenFromOtherScreensUpTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            step("First screen") {
                Screen1 {
                    isDisplayed()
                    scenario(AboutScreenScenario(false))
                    rotateActivity()
                    isDisplayed()
                    toSecond()
                }
            }
            step("Second screen") {
                Screen2 {
                    isDisplayed()
                    scenario(AboutScreenScenario(false))
                    rotateActivity()
                    isDisplayed()
                    toThird()
                }
            }
            step("Third screen") {
                Screen3 {
                    isDisplayed()
                    scenario(AboutScreenScenario(false))
                    rotateActivity()
                    isDisplayed()
                }
            }
        }
    }

    @Test
    fun popUpBackstackFromThirdToFirstTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            step("Navigate from first to third screen") {
                scenario(FromFirstToThirdScenario())
            }
            step("Navigate to first screen") {
                rotateActivity()
                Screen3 {
                    isDisplayed()
                    toFirst()
                }
            }
            step("Check first screen is displayed") {
                Screen1 {
                    rotateActivity()
                    isDisplayed()
                    navUpButton.doesNotExist()
                }
            }
            step("Check back button close app") {
                checkAppCloseByBackButton()
            }
        }
    }

    @Test
    fun popUpBackstackFromThirdToSecondBackTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            step("Navigate from first to third screen") {
                scenario(FromFirstToThirdScenario())
            }
            step("Navigate to second screen") {
                rotateActivity()
                Screen3 {
                    isDisplayed()
                    toSecond()
                }
            }
            step("Check second screen is displayed") {
                Screen2 {
                    rotateActivity()
                    isDisplayed()
                    navUpButton.isDisplayed()
                }
            }
            step("Press back and check is first screen") {
                pressBack()
                Screen1 {
                    isDisplayed()
                    navUpButton.doesNotExist()
                }
            }
            step("Check back button close app") {
                checkAppCloseByBackButton()
            }
        }
    }

    @Test
    fun popUpBackstackFromThirdToSecondUpTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            step("Navigate from first to third screen") {
                scenario(FromFirstToThirdScenario())
            }
            step("Navigate to second screen") {
                rotateActivity()
                Screen3 {
                    isDisplayed()
                    toSecond()
                }
            }
            step("Check second screen is displayed") {
                Screen2 {
                    rotateActivity()
                    isDisplayed()
                    navUpButton.isDisplayed()
                }
            }
            step("Press up and check is first screen") {
                pressUp()
                Screen1 {
                    isDisplayed()
                    navUpButton.doesNotExist()
                }
            }
            step("Check back button close app") {
                checkAppCloseByBackButton()
            }
        }
    }

    @Test
    fun popUpBackstackFromSecondToFirstBackTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            step("Navigate from first to second screen") {
                scenario(FromFirstToSecondScenario())
            }
            step("Navigate to first screen") {
                Screen2.toFirst()
                Screen1 {
                    isDisplayed()
                    navUpButton.doesNotExist()
                }
            }
            step("Check back button close app") {
                checkAppCloseByBackButton()
            }
        }
    }

    @Test
    fun backstackFromSecondToFirstBackTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            step("Navigate from first to second screen") {
                scenario(FromFirstToSecondScenario())
            }
            step("Press back and check is first screen") {
                pressBack()
                Screen1 {
                    isDisplayed()
                    navUpButton.doesNotExist()
                }
            }
            step("Check back button close app") {
                checkAppCloseByBackButton()
            }
        }
    }

    @Test
    fun backstackFromSecondToFirstUpTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            step("Navigate from first to second screen") {
                scenario(FromFirstToSecondScenario())
            }
            step("Press up and check is first screen") {
                pressUp()
                Screen1 {
                    isDisplayed()
                    navUpButton.doesNotExist()
                }
            }
            step("Check back button close app") {
                checkAppCloseByBackButton()
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
            scenario(FromFirstToThirdScenario())
            step("Navigate to second screen and check") {
                pressBack()
                Screen2.isDisplayed()
            }
            step("Navigate to first screen and check") {
                pressBack()
                Screen1.isDisplayed()
            }
            step("Check back button close app") {
                checkAppCloseByBackButton()
            }
        }
    }

    @Test
    fun fromThirdToFirstWithUpButtonTest() {
        val scenario = activityScenarioRule.scenario
        before {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
        }.run {
            scenario(FromFirstToThirdScenario())
            step("Navigate to second screen and check") {
                pressUp()
                Screen2.isDisplayed()
            }
            step("Navigate to first screen and check") {
                pressUp()
                Screen1 {
                    isDisplayed()
                    navUpButton.doesNotExist()
                }
            }
            step("Check back button close app") {
                checkAppCloseByBackButton()
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
            Thread.sleep(500)
        }
    }

    private fun checkAppCloseByBackButton() {
        var appClosed = false
        try {
            pressBack()
        } catch (e: NoActivityResumedException) {
            appClosed = true
        }
        assertTrue(appClosed)
    }

}