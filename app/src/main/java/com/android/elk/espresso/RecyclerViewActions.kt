package com.android.elk.espresso

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher

fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? =
    object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val viewHolder =
                view.findViewHolderForAdapterPosition(position) // has no item on such position
                    ?: return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }

fun recyclerViewSafeSizeMatcher(rvSize: Int): Matcher<View> =
    object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("RecyclerView size didn't matcher expected size $rvSize")
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
            return rvSize == item?.adapter?.itemCount
        }
    }

fun Matcher<View>.checkEachListItemForSubItemDisplayed(@IdRes vararg ids: Int, position: Int) {
    for ((index, resource) in ids.withIndex()) {
        Espresso.onView(this)
            .check(ViewAssertions.matches(atPosition(position, ViewMatchers.hasDescendant(withId(resource)))))
            .isDisplayed()
    }
}

fun <VH : RecyclerView.ViewHolder> Matcher<View>.clickOnPosition(position: Int) {
    Espresso.onView(this)
        .perform(RecyclerViewActions.actionOnItemAtPosition<VH>(position, ViewActions.click()))
}

fun <VH : RecyclerView.ViewHolder> Matcher<View>.scrollToPosition(position: Int) {
    Espresso.onView(this).perform(RecyclerViewActions.scrollToPosition<VH>(position))
}
