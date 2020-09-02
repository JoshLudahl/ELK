package com.android.elk.common

import android.view.View
import android.widget.TextView
import androidx.test.espresso.*
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.android.elk.common.TestrailResultManager.Process.addResult
import com.android.elk.common.TestrailResultManager.Process.checkIfEnabled
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
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

fun <T> screen2(obj: T, init: T.() -> Unit): T = obj.apply { init() }

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



/**
 *  Custom BottomSheet Behavior
 */


@Test
fun testSwipeUpToExpand() {
    Espresso.onView(ViewMatchers.withId(id.map_search_options))
        .perform(
            DesignViewActions.withCustomConstraints(
                GeneralSwipeAction(
                    Swipe.FAST,
                    GeneralLocation.VISIBLE_CENTER,
                    CoordinatesProvider { view: View -> floatArrayOf(view.width / 2.toFloat(), 0f) },
                    Press.FINGER
                ),
                ViewMatchers.isDisplayingAtLeast(5)
            )
        )

    try {
        scrollToAndCheckDisplayedAndClick(id.btn_food)
    } finally {

    }
}

@Test
fun checking() {
    Espresso.onView(ViewMatchers.withId(id.map_search_drawer_fragment))
        .perform(setDrawerFullyExpanded())

    scrollToAndCheckDisplayedAndClick(id.btn_truck_stop)

    Thread.sleep(3000)
}


object DesignViewActions {
    /** Overwrites the constraints of the specified [ViewAction].  */
    fun withCustomConstraints(
        action: ViewAction, constraints: Matcher<View>
    ): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController?, view: View?) {
                action.perform(uiController, view)
            }
        }
    }

    fun setVisibility(visibility: Int): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isEnabled()
            }

            override fun getDescription(): String {
                return "Set view visibility"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                view.visibility = visibility
                uiController.loopMainThreadForAtLeast(250)
            }
        }
    }
}

private fun setDrawerFullyExpanded(): ViewAction? {
    return object : ViewAction {

        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isDisplayed()
        }

        override fun getDescription(): String {
            return "set full height"
        }

        override fun perform(uiController: UiController, view: View) {
            val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(view)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            while(behavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                uiController.loopMainThreadForAtLeast(10)
            }
        }
    }
}

private fun collapseDrawer(): ViewAction? {
    return object : ViewAction {

        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isDisplayed()
        }

        override fun getDescription(): String {
            return "set tall peek height"
        }

        override fun perform(uiController: UiController, view: View) {
            val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(view)
            behavior.halfExpand()
        }
    }
}

private fun waitForIdler() {
    InstrumentationRegistry.getInstrumentation().waitForIdleSync()
}

/**
 * custom ViewAction Template
 */

fun customViewActionTemplate(): ViewAction? {
    return object: ViewAction {
        override fun getConstraints(): Matcher<View> {
            TODO("Not yet implemented")
        }

        override fun getDescription(): String {
            TODO("Not yet implemented")
        }

        override fun perform(uiController: UiController?, view: View?) {
            TODO("Not yet implemented")
        }
    }
}

/**
 * Custom ViewAssertion template
 */
fun customViewAssertion(): ViewAssertion? {
    return object: ViewAssertion {
        override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
            TODO("Not yet implemented")

            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            if (view !is [desired view to compare]) {
                throw IllegalStateException("The asserted view is not RecyclerView")
            }

            ViewMatchers.assertThat(Make assumption here, or not.  )
        }

    }
}

/**
 * Custom Matcher template
 */
fun customViewMatcher(): Matcher<View> {
    return object: TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            TODO("Not yet implemented")
        }

        override fun matchesSafely(item: View?): Boolean {
            TODO("Not yet implemented")
        }
    }
}

/**
 * Example of a BoundedMatcher matching the Bounds of the View and a TextView
 */
fun customBoundedMatcher(): Matcher<View> {
    return object : BoundedMatcher<View, TextView>(TextView::class.java) {
        override fun describeTo(description: Description?) {
            TODO("Not yet implemented")
        }

        override fun matchesSafely(item: TextView?): Boolean {
            TODO("Not yet implemented")
        }
    }
}
