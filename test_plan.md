# Test plan

This wouldn't actually be in the repository on a real project: This is better left in either a wiki or dedicated tooling. 
Depending on the choices, requirements and available resources.
(actual test plans could even be completely skipped)

Since the provided application is a basic "hello world" with not much going on, we'll be limited to the functionality that is basic to most apps:
 * Does it look as intended ("Hello world" centered, hamburger menu)
 * Respond as intended (hamburger opens, shows settings option)
 * At multiple orientations
 * Verify a bench mark for launch and restore
 * Verify it can't crash it easily by overloading with taps
 * Verify a screen reader could access the Hello World label to read it out loud (turned out to be too challenging to realize within 6 hours)
 * And everything everything still works without networking.

1. UI Tests
[HelloWorldLayoutTest.kt](app/src/androidTest/java/com/abnamro/apps/referenceandroid/HelloWorldLayoutTest.kt)

# Test Case 1: Initial Screen Layout
 * Verify "Hello World" label is present 
 * Confirm label is centered on the screen
 * Check label text is exactly "Hello World"
 * Validate label's text color, font size, and visibility

# Test Case 2: Hamburger Menu Presence
 * Confirm hamburger menu icon is visible in top right corner
 * Verify hamburger menu icon is tappable

# Test Case 3: Hamburger Menu Interaction
 * Tap hamburger menu
 * Verify "Settings" option appears
 * Confirm "Settings" option is visible and spelled correctly
 * Validate that tapping "Settings" does not trigger any action

# Test Case 4: Label Interaction Test
 * Tap "Hello World" label
 * Confirm no unexpected behavior occurs
 * Verify app remains on the same screen


3. Orientation Change Tests
[OrientationTest.kt](app/src/androidTest/java/com/abnamro/apps/referenceandroid/OrientationTest.kt)

# Test Case 5: Screen Rotation
 * Launch app in portrait mode
 * Verify UI elements remain intact
 * Rotate device to landscape
 * Confirm "Hello World" label remains centered
 * Check hamburger menu is still present and functional
 * Rotate back to portrait
 * Validate UI consistency

4. Performance and Stability Tests
[AppPerformanceAndBackgroundTest.kt](app/src/androidTest/java/com/abnamro/apps/referenceandroid/AppPerformanceAndBackgroundTest.kt)

# Test Case 7: App Launch
 * Measure app launch time
 * Confirm app launches within acceptable time frame (e.g., < 2 seconds)
 * Check no crash occurs during startup

# Test Case 6: Screen Size Compatibility
A valid testcase, but beyond the scope of this assessment.

* Test on multiple device emulators with different screen sizes
* Ensure label remains centered
* Verify all UI elements are proportionally scaled

# Test Case 8: Background/Foreground Transition
 * Launch app
 * Press home button
 * Reopen app
 * Verify app state is preserved
 * Confirm no unexpected behaviors

# Test Case 10: No Exceptions At rapid interactions
 * Perform multiple interactions with app
 * Attempt to stress test by rapid tapping of label and menu
 * Confirm no app crashes or force closes

5. Accessibility Tests
[AccessabilityTest.kt](app/src/androidTest/java/com/abnamro/apps/referenceandroid/AccessabilityTest.kt)
The package with the espresso accessibility checks seems to be incompatible with some of the imports i used.

I consider it to be beyond the scope of this assesment as it would take more time than alotted.

# Test Case 9: Accessibility Checks
 * Enable espresso accessibility checks
 * Verify "Hello World" label is readable
 * Confirm hamburger menu is accessible
 * Check color contrast meets basic accessibility standards

7. Network and Resource Tests

# Test Case 10: Offline Mode
 * Test app behavior with no network connection
 * Confirm app functions normally
 * Verify no network-dependent errors occur
 