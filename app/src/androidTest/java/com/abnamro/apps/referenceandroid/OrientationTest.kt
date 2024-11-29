package com.abnamro.apps.referenceandroid
import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OrientationTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun screenRotation_VerifyUIConsistency() {
        // Verify initial state in portrait
        onView(withText("Hello World!"))
            .check(matches(isDisplayed()))

        // Rotate to landscape
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // Check label is still present and displayed after rotation
        onView(withText("Hello World!"))
            .check(matches(
                allOf(
                    isDisplayed(),
                    CustomMatchers.isCenterInParent()
                )
            ))

        // Rotate back to portrait
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        // Verify label is still present and centered
        onView(withText("Hello World!"))
            .check(matches(
                allOf(
                    isDisplayed(),
                    CustomMatchers.isCenterInParent()
                )
            ))
    }
}