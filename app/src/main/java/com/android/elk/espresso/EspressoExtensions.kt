package com.android.elk.espresso

import android.annotation.SuppressLint
import android.os.strictmode.ResourceMismatchViolation
import android.util.NoSuchPropertyException
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
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
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsNot.not

enum class ResourceType {
    ID, STRING
}

val Int.resType: ResourceType
get() {
    return when (targetContext.resources.getResourceTypeName(this)) {
        "id" -> ResourceType.ID
        "string" -> ResourceType.STRING
        else -> throw NoSuchPropertyException("No matching property.")
    }
}

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

/**
 * Checks if many views are hidden
 *
 * @param viewIds
 */
fun checkViewsAreHidden(@IdRes vararg viewIds: Int) {
    for (viewId in viewIds) {
        onView(withId(viewId))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }
}

/**
 * Checks if a list of Matchers of is in the same View, great for allOf(...)
 *
 * @param viewIds
 * @return ViewInteraction
 */
fun bulkIsMatcherIsDisplayed(vararg viewIds: Matcher<View>): ViewInteraction =
    onView(Matchers.allOf(*viewIds))
    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

/**
 * Takes in view matchers and checks if each one is displayed
 * @param views
 */
fun viewsAreDisplayed(vararg views: Matcher<View>) {
    for(view in views) {
        view verify isDisplayed
    }
}

/**
 * View matcher - matches a view of either string or id resource.
 *
 * @param id - id resource
 *
 * @return Matcher<View>
 */
fun view(@IdRes @StringRes id: Int): Matcher<View> = when(id.resType) {
    ResourceType.ID -> withId(id)
    ResourceType.STRING -> withText(id)
}

/**
 * View matcher - matches a view with text
 *
 * @param text
 * @return Matcher<View>
 */
fun view(text: String): Matcher<View> = withText(text)

/**
 * View Matcher - matches a view with a class
 *
 * @param clazz
 * @return Matcher<View>
 */
fun view(clazz: Class<out View>): Matcher<View> = ViewMatchers.isAssignableFrom(clazz)

/**
 * Views Matcher - matches several compound views, shorthand for allOf(), but takes Matchers
 * @sample views(view(R.id.some_resource), view("A String"), withId(R.id.another_id))
 * @param matchers takes a list of matchers
 * @return Matcher<View> object
 */
fun views(vararg matchers: Matcher<View>): Matcher<View> = allOf(*matchers)

/**
 * Checks a varable number of arguments of type Int that is a string from a string value
 *
 * @param viewIds
 */
fun checkTextsAreHidden(@StringRes vararg viewIds: Int) {
    for (viewId in viewIds) {
        onView(withText(targetContext stringValue viewId))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }
}

/**
 * Checks a variable number of arguments of type id resource for the hidden assertion
 * from a Matcher receiver
 *
 * @param viewIds
 */
fun Matcher<View>.checkViewsAreHidden(@IdRes vararg viewIds: Int) {
    with(onView(this)) {
        for (view in viewIds) {
            check(matches(ViewMatchers.hasDescendant(withId(view))))
        }
    }
}

/**
 * Checks a variable number of arguments of id resource Int for the hidden assertion
 * from a ViewInteraction receiver
 *
 * @param viewIds
 */
fun ViewInteraction.checkViewsAreHidden(@IdRes vararg viewIds: Int) {
    with(this) {
        for (view in viewIds) {
            check(ViewAssertions.matches(ViewMatchers.hasDescendant(withId(view))))
        }
    }
}

/**
 * Checks that a given toast is displayed
 *
 * @param message
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
