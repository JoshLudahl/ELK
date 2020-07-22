package com.android.elk.espresso

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.PositionAssertions
import org.hamcrest.Matcher


/**
 * Extension function for [POSITION ASSERTIONS] for [View]
 */
fun Matcher<View>.isCompletelyLeftOf(matcher: Matcher<View>): ViewInteraction =
    Espresso.onView(this).check(PositionAssertions.isCompletelyLeftOf(matcher))

fun ViewInteraction.isCompletelyLeftOf(matcher: Matcher<View>): ViewInteraction =
    check(PositionAssertions.isCompletelyLeftOf(matcher))

