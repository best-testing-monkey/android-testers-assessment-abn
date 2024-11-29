package com.abnamro.apps.referenceandroid

import android.content.Intent
import androidx.test.core.app.ActivityScenario
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
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppPerformanceAndBackgroundTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var uiDevice: UiDevice

    @Before
    fun setup() {
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    /*
     * ##Test Case 5: App Launch performance
     *  - Measure app launch time
     *  - Confirm app launches within acceptable time frame (i took < 2 seconds as the bench mark)
     *  - Check no crash occurs during startup
    */
    @Test
    fun appLaunch_PerformanceTest() {
        val startTime = System.currentTimeMillis()

        //Espresso waits to perform actions, so it should wait for the label to be displayed.
        onView(withText("Hello World!")).check(matches(isDisplayed()))

        val endTime = System.currentTimeMillis()
        val launchTime = endTime - startTime

        // Assert launch time is within acceptable range (e.g., less than 2 seconds)
        assertTrue("App launch took too long: $launchTime ms", launchTime < 2000)
    }

    /*
     * ##Test Case 6: Background/Foreground Transition
     *  - Launch app
     *  - Press home button
     *  - Reopen app
     *  - Verify app state is preserved
     *  - Confirm no unexpected behaviors
    */
    @Test
    fun backgroundForeground_StatePreservation() {
        // Initial state verification
        onView(withText("Hello World!")).check(matches(isDisplayed()))

        // Simulate home button press (background)
        buttonHome()

        // Wait a moment
        Thread.sleep(500)

        // Relaunch the app
        ActivityScenario.launch(MainActivity::class.java)

        // Verify app state is preserved
        onView(withText("Hello World!")).check(matches(isDisplayed()))
    }

    /*
     * ##Test Case 7: No Exceptions At rapid interactions
     *  - Perform multiple interactions with app
     *  - Attempt to stress test by rapid tapping of label and menu
     *  - Confirm no app crashes or force closes
    */
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

    /*
     * ##Test Case 8: Offline Mode
     *  - Test app behavior with no network connection
     *  - Confirm app functions normally
     *  - Verify no network-dependent errors occur
    */
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

    /*
     * ## Test Case 9: Multiple launches
     *  - Launch
     *  - Confirm display of "Hello World!"
     *  - Close app
     *  - repeat
     */
    @Test
    fun multipleQuickLaunches_Stability() {
        // Attempt multiple rapid launches
        repeat(3) {
            val scenario = ActivityScenario.launch(MainActivity::class.java)

            // Verify UI elements on each launch
            onView(withText("Hello World!")).check(matches(isDisplayed()))

            // Close the scenario to prevent resource leaks
            scenario.close()
        }
    }

    private fun buttonHome() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val homeIntent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_HOME)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(homeIntent)
    }
}