package com.abnamro.apps.referenceandroid

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HamburgerMenuTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun hamburgerMenu_SettingsOptionPresent() {
        // Open the overflow menu (hamburger menu)
        openActionBarOverflowOrOptionsMenu(
            InstrumentationRegistry.getInstrumentation().targetContext
        )

        // Check that "Settings" option is displayed
        onView(withText("Settings"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun hamburgerMenu_SettingsOptionNoAction() {
        // Open the overflow menu (hamburger menu)
        openActionBarOverflowOrOptionsMenu(
            InstrumentationRegistry.getInstrumentation().targetContext
        )

        // Tap on "Settings"
        onView(withText("Settings"))
            .perform(click())

        // Verify that no navigation or action occurs
        // This could involve checking that we're still on the same activity
        // For example, checking a unique element of the main activity
        onView(withText("Hello World!"))
            .check(matches(isDisplayed()))
    }
}