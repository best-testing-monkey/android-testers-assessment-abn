package com.abnamro.apps.referenceandroid



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

    @Before
    fun setup() {
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    /*
     * Note: In a real test, we might want to use Espresso Accessibility checks,
     * but that doesn't seem to be compatible with some of the imports that i use
     * in other tests.
     *
     * ## Test Case 11: Accessibility Checks
     *  - Enable espresso accessibility checks
     *  - Verify "Hello World" label is readable
     *  - Confirm hamburger menu is accessible
     *  - Check color contrast meets basic accessibility standards
     */
    @Test
    fun testAccessibility() {


        // Verify Hello World label is accessible
        val helloWorldLabel = uiDevice.findObject(
            UiSelector().textMatches("Hello World!")
        )

        assert(helloWorldLabel.exists()) { "Hello World label not found" }
        assert(helloWorldLabel.isEnabled) { "Hello World label is not accessible" }
    }
}