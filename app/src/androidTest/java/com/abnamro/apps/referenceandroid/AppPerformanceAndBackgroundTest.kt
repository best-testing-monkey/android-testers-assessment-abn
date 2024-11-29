package com.abnamro.apps.referenceandroid

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppPerformanceAndBackgroundTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

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

    @Test
    fun backgroundForeground_StatePreservation() {
        // Initial state verification
        onView(withText("Hello World!")).check(matches(isDisplayed()))

        // Simulate home button press (background)
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val homeIntent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_HOME)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(homeIntent)

        // Wait a moment
        Thread.sleep(500)

        // Relaunch the app
        ActivityScenario.launch(MainActivity::class.java)

        // Verify app state is preserved
        onView(withText("Hello World!")).check(matches(isDisplayed()))
    }

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

    @Test
    fun multipleLabelTaps_StableState() {
        // Tap the label multiple times
        repeat(3) {
            onView(withText("Hello World!"))
                .perform(click())
        }

        // Verify that the app remains in the same state
        onView(withText("Hello World!"))
            .check(matches(isDisplayed()))
    }
}