package com.android.elk.common

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import com.android.elk.common.TestrailResultManager.Process.addResult
import com.android.elk.common.TestrailResultManager.Process.checkIfEnabled
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.rules.TestWatcher
import javax.security.auth.callback.Callback


/**
 * Lambda for driving navigation. Example: navigate { toLoadboardTab() } will navigate to the
 * LoadBoard tab. Includes variable generic notation for applying a block of code when creating a single instance across several robots (Views)
 */
class MainView  {

    infix fun thenVerify(robot: MainViewResult.() -> Unit): MainViewResult {
        return MainViewResult().apply { robot() }
    }

    fun doThisFirst() {
    }

    infix fun goat(init: MainView.() -> Unit) = screen(init)

}

fun verifyNavigation(init: MainViewResult.() -> Unit) =
    MainViewResult().apply { init() }

class MainViewResult : Base() {

    fun doSomething() {
    }

    infix fun thenVerify(view: MainView.()-> Unit) = screen(MainView(), view)

}

class Head(): Base() {

    fun head(init: Head.() -> Unit) = screen(Head(), init)
    fun goat(init: MainView.() -> Unit) = screen(init)
}

fun goat(init: MainView.() -> Unit) = screen(init)

open class Base {
    fun <T> screen(tag: T, init: T.() -> Unit): T {
        tag.init()
        return tag
    }
}

fun <T> screen(init: T.() -> Unit): T = init as T

fun <T> screen(obj: T, init: T.() -> Unit): T = obj.apply { init() }

inline fun <T> T.screen(block: T.() -> Unit): T {
    block()
    return this
}

interface Nav {
    /**
     * Main navigation entry point
     */
    fun navigate(robot: Bot.() -> Unit) = screen(Bot(), robot)

    /**
     * Generic function that checks if the specified id is even present prior to performing actions against it
     * Obj objects are bound to Bound.
     */
    fun <T : Bound> screen(obj: T, init: T.() -> Unit): T {
        view(id of view) verifyIt isDisplayed
        return obj.apply { init() }
    }

}
// as class
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
