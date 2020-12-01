package com.android.elk.espresso


import android.annotation.SuppressLint
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.android.elk.common.stringValue
import com.android.elk.common.targetContext
import com.google.android.material.internal.ContextUtils.getActivity
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.core.IsNot.not

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

fun simpleBulkCheck(vararg viewIds: Matcher<View>): ViewInteraction =
    onView(Matchers.allOf(*viewIds))
    .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))

fun view(@IdRes id: Int): Matcher<View> = withId(id)

fun view(text: String): Matcher<View> = withText(text)

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

/**
 *  ToastMatcher (requires access to activity and context)
 */
@SuppressLint("RestrictedApi")
fun toastMatcher(message: String) {
    val context = InstrumentationRegistry.getInstrumentation().context
    onView(ViewMatchers.withText(message))
        .inRoot(
            withDecorView(not(Matchers.`is`(getActivity(context)?.window?.decorView)))
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
}
