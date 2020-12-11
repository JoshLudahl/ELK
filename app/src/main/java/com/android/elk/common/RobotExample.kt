package com.android.elk.common

import android.content.res.Configuration
import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RegexTextViewMatcher(private val pattern: String) : BoundedMatcher<View, TextView>(TextView::class.java) {

    override fun describeTo(description: Description?) {
        description?.appendText("Checking the matcher on received view: with pattern=$pattern")
    }

    override fun matchesSafely(item: TextView?) =
        item?.text?.let {
            pattern
                .toRegex()
                .toPattern()
                .matcher(it)
                .matches()
        } ?: false
}

private fun withPattern(regex: String): Matcher<View> = RegexTextViewMatcher(regex)

// usage: onView(withId(R.id.element_id)).check(matches(withPattern("\\+d")))


//As function
fun regexMatcher(pattern: String): Matcher<View> =
    object : BoundedMatcher<View, TextView>(TextView::class.java) {

        override fun describeTo(description: Description?) {
            description?.appendText("Checking the matcher on received view: with pattern=$pattern")
        }

        override fun matchesSafely(item: TextView): Boolean {
            return item?.text?.let {
                pattern
                    .toRegex()
                    .toPattern()
                    .matcher(it)
                    .matches()
            } ?: false
        }
    }

    //usage: onView(withText("something")).check(matches(regexMatcher("\\+d")))

class DeviceDisplayModeMatcher (private val displayMode: Int) : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("Getting display mode of: $displayMode")
    }

    override fun matchesSafely(item: View?): Boolean {
        val mode = item?.context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)
        return displayMode == mode
    }
}
//onView(isRoot()).check(matches(DeviceDisplayModeMatcher(Configuration.UI_MODE_NIGHT_NO)))
//val mode = context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)
//when (mode) {
//    Configuration.UI_MODE_NIGHT_YES -> {}
//    Configuration.UI_MODE_NIGHT_NO -> {}
//    Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
//}
