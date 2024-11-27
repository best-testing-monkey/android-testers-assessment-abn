# Test plan

This wouldn't actually be in the repository on a real project: This is better left in either a wiki or dedicated tooling. 
Depending on the choices, requirements and available resources.  

(actual test plans could even be completely skipped)

Since the provided application is a basic "hello world" with not much going on, we'll be limited to the functionality that is basic to most apps:
 * Does it look as intended ("Hello world" centered, hamburger menu)
 * Respond as intended (hamburger opens, shows settings option)
 * At multiple orientations
 * Verify i can't crash it easily by overloading
 * Verify a bench mark for launch and restore
 * Verify a screen reader could access the Hello World label to read it out loud
 * And for the heck of it, see dif everything everything still works without networking.

1. UI Tests
```
Test Case 1: Initial Screen Layout
- Verify "Hello World" label is present
- Confirm label is centered on the screen
- Check label text is exactly "Hello World"
- Validate label's text color, font size, and visibility

Test Case 2: Hamburger Menu Presence
- Confirm hamburger menu icon is visible in top right corner
- Verify hamburger menu icon is tappable
```

2. Interaction Tests
```
Test Case 3: Hamburger Menu Interaction
- Tap hamburger menu
- Verify "Settings" option appears
- Confirm "Settings" option is visible and spelled correctly
- Validate that tapping "Settings" does not trigger any action (as per requirement)

Test Case 4: Label Interaction Test
- Tap "Hello World" label
- Confirm no unexpected behavior occurs
- Verify app remains on the same screen
```

3. Orientation Change Tests
```
Test Case 5: Screen Rotation
- Launch app in portrait mode
- Verify UI elements remain intact
- Rotate device to landscape
- Confirm "Hello World" label remains centered
- Check hamburger menu is still present and functional
- Rotate back to portrait
- Validate UI consistency

Test Case 6: Screen Size Compatibility
- Test on multiple device emulators with different screen sizes
- Ensure label remains centered
- Verify all UI elements are proportionally scaled
```

4. Performance and Stability Tests
```
Test Case 7: App Launch
- Measure app launch time
- Confirm app launches within acceptable time frame (e.g., < 2 seconds)
- Check no crash occurs during startup

Test Case 8: Background/Foreground Transition
- Launch app
- Press home button
- Reopen app
- Verify app state is preserved
- Confirm no unexpected behaviors
```

5. Accessibility Tests
```
Test Case 9: Accessibility Checks
- Enable screen reader
- Verify "Hello World" label is readable
- Confirm hamburger menu is accessible
- Check color contrast meets basic accessibility standards
```

6. Error Handling
```
Test Case 10: No Unhandled Exceptions
- Perform multiple interactions with app
- Attempt to stress test by rapid tapping of label and menu
- Confirm no app crashes or force closes
```

7. Network and Resource Tests
```
Test Case 11: Offline Mode
- Test app behavior with no network connection
- Confirm app functions normally
- Verify no network-dependent errors occur
```