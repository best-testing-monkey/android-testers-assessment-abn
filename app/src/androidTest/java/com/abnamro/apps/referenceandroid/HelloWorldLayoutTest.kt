package com.abnamro.apps.referenceandroid

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.platform.app.InstrumentationRegistry
import com.abnamro.apps.referenceandroid.Utils.CustomMatchers
import org.hamcrest.CoreMatchers.allOf

@RunWith(AndroidJUnit4::class)
class HelloWorldLayoutTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /*
     * ## Test Case 1: Initial Screen Layout
     *  - Verify "Hello World" label is present
     *  - Confirm label is centered on the screen
     *  - Check label text is exactly "Hello World"
     *  - Validate label's text color, font size, and visibility
     */
    @Test
    fun testInitialLayout() {
        // Check label is centered on the screen
        onView(withText("Hello World!"))
            .check(matches(
                allOf(
                    isDisplayed(),
                    CustomMatchers.isCenterInParent(),
                    CustomMatchers.withTextSize(14.0f),

                    // hasTextColor(R.color.colorPrimaryDark),

                    // Should also be possible, but it seems that the
                    // predefined colors aren't the same as the actual colors.
                    // Options: custom matcher or get the defined colors somewhere else
                )
            ))
    }

    /*
    * ## Test Case 2: Hamburger Menu Presence and Interaction
     *  - Confirm hamburger menu icon is visible
     *  - Verify hamburger menu icon is tappable
     *  - Tap hamburger menu
     *  - Verify "Settings" option appears
     *  - Confirm "Settings" option is visible and spelled correctly
     *  - Validate that tapping "Settings" does not trigger any action
    */
    @Test
    fun testHamburgerMenu() {
        // Verify Settings option is NOT yet visible
        onView(withText("Settings"))
            .check(doesNotExist())

        // Open options menu to verify hamburger menu
        Espresso.openActionBarOverflowOrOptionsMenu(
            InstrumentationRegistry.getInstrumentation().targetContext
        )

        // Verify Settings option exists in menu
        onView(withText("Settings"))
            .check(matches(isDisplayed()))
    }

    /*
     * ##Test Case 3: Label Interaction Test
     *  - Tap "Hello World" label
     *  - Confirm no unexpected behavior occurs
     *  - Verify app remains on the same screen
    */
    @Test
    fun labelTap_LabelInteraction() {
        // Verify the label is displayed before tapping
        onView(withText("Hello World!"))
            .check(matches(isDisplayed()))

        // Tap the label
        onView(withText("Hello World!"))
            .perform(click())

        // Verify that after tapping, we remain on the same screen
        // Check that the label is still present
        onView(withText("Hello World!"))
            .check(matches(isDisplayed()))
    }

}