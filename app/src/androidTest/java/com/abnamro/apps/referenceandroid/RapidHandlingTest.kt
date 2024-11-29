package com.abnamro.apps.referenceandroid



import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RapidHandlingTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var uiDevice: UiDevice
    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun testRapidHandling() {
        // Stress test by rapid interactions
        repeat(50) {
            try {
                // Attempt to tap Hello World label multiple times rapidly
                onView(withText("Hello World!")).perform(click())
            } catch (e: Exception) {
                assert(false) { "Unexpected error during rapid interactions: ${e.message}" }
            }
        }
    }

    @Test
    fun testOfflineMode() {
        // Disable network connectivity
        uiDevice.executeShellCommand("settings put global airplane_mode_on 1")
        uiDevice.executeShellCommand("am broadcast -a android.intent.action.AIRPLANE_MODE")

        try {
            // Verify app still launches
            onView(withText("Hello World!")).check(matches(isDisplayed()))

            // Open the overflow menu (hamburger menu)
            openActionBarOverflowOrOptionsMenu(
                InstrumentationRegistry.getInstrumentation().targetContext
            )

            // Check that "Settings" option is displayed
            onView(withText("Settings"))
                .check(matches(isDisplayed()))
        } finally {
            // Re-enable network connectivity
            uiDevice.executeShellCommand("settings put global airplane_mode_on 0")
            uiDevice.executeShellCommand("am broadcast -a android.intent.action.AIRPLANE_MODE")
        }
    }
}