package com.abnamro.apps.referenceandroid.Utils
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

// I couldn't find these matchers in the documentation
object CustomMatchers {
    fun isCenterInParent(): Matcher<in View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("is centered in parent")
            }

            override fun matchesSafely(view: View): Boolean {
                val parent = view.parent as View

                // Calculate view's center
                val viewCenterX = view.x + view.width / 2f
                val viewCenterY = view.y + view.height / 2f

                // Calculate parent's center
                val parentCenterX = parent.width / 2f
                val parentCenterY = parent.height / 2f

                // Check if view's center is very close to parent's center
                return Math.abs(viewCenterX - parentCenterX) < 10 &&
                        Math.abs(viewCenterY - parentCenterY) < 10
            }
        }
    }

    fun withTextSize(expectedSize: Float): Matcher<in View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with text size: ").appendValue(expectedSize)
            }

            override fun matchesSafely(textView: TextView): Boolean {
                Log.println(Log.INFO,"textView.textSize", textView.textSize.toString())
                Log.println(Log.INFO,"textView.textColors", textView.textColors.toString())
                return textView.textSize == expectedSize
            }
        }
    }
}