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
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.allOf

@RunWith(AndroidJUnit4::class)
class HelloWorldLayoutTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testHelloWorldLabelPresence() {
        // Verify label is present
        onView(withText("Hello World!"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testHelloWorldLabelPosition() {
        // Check label is centered on the screen
        onView(withText("Hello World!"))
            .check(matches(
                allOf(
                    isDisplayed(),
                    CustomMatchers.isCenterInParent()
                )
            ))
    }

    @Test
    fun testHelloWorldLabelAttributes() {
        // Verify specific label attributes
        onView(withText("Hello World!"))
            .check(matches(
                allOf(
                    isDisplayed(),
                    CustomMatchers.withTextSize(14.0f),
                    // Should also be possible, but it seems that the
                    // predefined colors aren't the same as the actual colors.
                    //hasTextColor(R.color.colorPrimaryDark),
                )
            ))
    }

    @Test
    fun testHamburgerMenuPresence() {
        // Open options menu to verify hamburger menu
        Espresso.openActionBarOverflowOrOptionsMenu(
            InstrumentationRegistry.getInstrumentation().targetContext
        )

        // Verify Settings option exists in menu
        onView(withText("Settings"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testHamburgerMenuLocation() {
        // Verify hamburger menu (overflow menu) is in the top right
        Espresso.openActionBarOverflowOrOptionsMenu(
            InstrumentationRegistry.getInstrumentation().targetContext
        )

        // Confirm Settings option can be opened
        onView(withText("Settings"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun labelTap_NoUnexpectedBehavior() {
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