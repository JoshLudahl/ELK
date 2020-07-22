package com.android.elk.espresso

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.android.elk.common.stringValue
import com.android.elk.common.targetContext
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Actions
 */
infix fun ViewAction.on(matcher: Matcher<View>) {
    onView(matcher).perform(this)
}

infix fun ViewAction.on(viewInteraction: ViewInteraction) {
    viewInteraction.perform(this)
}

infix fun ViewAction.onto(matcher: Matcher<View>) {
    onView(matcher).perform(this)
}

infix fun ViewAction.onto(viewInteraction: ViewInteraction) {
    viewInteraction.perform(this)
}

infix fun ViewAction.into(matcher: Matcher<View>) {
    onView(matcher).perform(this)
}

infix fun ViewAction.into(viewInteraction: ViewInteraction) {
    viewInteraction.perform(this)
}

/**
 * Assertions
 */
infix fun Matcher<View>.verify(viewAssertion: ViewAssertion) {
    onView(this).check(viewAssertion)
}

infix fun ViewInteraction.verify(viewAssertion: ViewAssertion) {
    this.check(viewAssertion)
}

infix fun Matcher<View>.verifyThat(viewAssertion: ViewAssertion) {
    onView(this).check(viewAssertion)
}

infix fun ViewInteraction.verifyThat(viewAssertion: ViewAssertion) {
    this.check(viewAssertion)
}

infix fun Matcher<View>.confirm(viewAssertion: ViewAssertion) {
    onView(this).check(viewAssertion)
}

infix fun ViewInteraction.confirm(viewAssertion: ViewAssertion) {
    this.check(viewAssertion)
}

infix fun Matcher<View>.confirmThat(viewAssertion: ViewAssertion) {
    onView(this).check(viewAssertion)
}

infix fun ViewInteraction.confirmThat(viewAssertion: ViewAssertion) {
    this.check(viewAssertion)
}

/**
 * Bulk operations
 */
fun checkViewsAreHidden(@IdRes vararg viewIds: Int) {
    for (viewId in viewIds) {
        onView(withId(viewId))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }
}

fun checkTextsAreHidden(@StringRes vararg viewIds: Int) {
    for (viewId in viewIds) {
        onView(withText(targetContext stringValue viewId))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }
}

fun Matcher<View>.checkViewsAreHidden(@IdRes vararg viewIds: Int) {
    with(onView(this)) {
        for (view in viewIds) {
            check(matches(ViewMatchers.hasDescendant(withId(view))))
        }
    }
}

fun ViewInteraction.checkViewsAreHidden(@IdRes vararg viewIds: Int) {
    with(this) {
        for (view in viewIds) {
            check(ViewAssertions.matches(ViewMatchers.hasDescendant(withId(view))))
        }
    }
}

fun Int.checkViewsAreHidden(@IdRes vararg viewIds: Int) {
    with(onView(withId(this))) {
        for (view in viewIds) {
            check(ViewAssertions.matches(ViewMatchers.hasDescendant(withId(view))))
        }
    }
}

fun ViewInteraction.cheskViewsAreHidden(@IdRes vararg viewIds: Int) {
    this.let {
        for (view in viewIds) {
            check(ViewAssertions.matches(ViewMatchers.hasDescendant(withId(view))))
        }
    }
}