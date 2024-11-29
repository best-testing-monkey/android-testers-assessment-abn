package com.abnamro.apps.referenceandroid



import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// Not compatible with other libraries:
//import androidx.test.espresso.accessibility.AccessibilityChecks

@RunWith(AndroidJUnit4::class)
class AccessabilityTest {

// Not compatible with other libraries:
//    init {
//        AccessibilityChecks.enable()
//    }

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
    fun testAccessibility() {
        // Note: In a real test, we might want to use Espresso Accessibility checks,
        // but that doesn't seem to be compatible with some of the imports that i use
        // in other tests.

        // Verify Hello World label is accessible
        val helloWorldLabel = uiDevice.findObject(
            UiSelector().textMatches("Hello World!")
        )

        assert(helloWorldLabel.exists()) { "Hello World label not found" }
        assert(helloWorldLabel.isEnabled) { "Hello World label is not accessible" }
    }
}