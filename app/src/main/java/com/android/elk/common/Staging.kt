package com.android.elk.common

import android.view.View
import android.widget.TextView
import androidx.test.espresso.*
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import javax.security.auth.callback.Callback



inline fun <reified K: Enum<K> , reified T: Enum<T>> onlyOneIsSelected(enum: K, enumMap: MutableMap<Enum<T>, Int>) {
    view(enumMap[enumValues<T>()[enum.ordinal]] as Int) checkIf isChecked
    enumMap.remove(enum as Enum<*>)

    for (value in enumMap) {
        view(value.value) checkIf isUnChecked
    }
}


/**
 *  Custom BottomSheet Behavior
 */

//fun testSwipeUpToExpand() {
//    Espresso.onView(ViewMatchers.withId(id.map_search_options))
//        .perform(
//            DesignViewActions.withCustomConstraints(
//                GeneralSwipeAction(
//                    Swipe.FAST,
//                    GeneralLocation.VISIBLE_CENTER,
//                    CoordinatesProvider { view: View -> floatArrayOf(view.width / 2.toFloat(), 0f) },
//                    Press.FINGER
//                ),
//                ViewMatchers.isDisplayingAtLeast(5)
//            )
//        )
//
//    try {
//        //
//    } finally {
//
//    }
//}

/**
 * Intents stubbing
 */
//intending(not(isInternal())).respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))

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

 fun setDrawerFullyExpanded(): ViewAction? {
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
            behavior.halfExpandedRatio
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
    return object : ViewAssertion {
        override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
            TODO("Not yet implemented")

//            if (noViewFoundException != null) {
//                throw noViewFoundException
//            }
//
//            if (view !is [desired view to compare]) {
//                throw IllegalStateException("The asserted view is not RecyclerView")
//            }
//
//            ViewMatchers.assertThat(Make assumption here, or not.  )
//        }

            //}
        }

        /**
         * Custom Matcher template
         */
        fun customViewMatcher(): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
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
    }
}