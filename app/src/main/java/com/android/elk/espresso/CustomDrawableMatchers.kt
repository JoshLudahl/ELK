package com.android.elk.espresso

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toColor
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * Takes in the id of a drawable and attempts to match it against the view in question
 *
 * @param id
 */
fun withDrawable(@DrawableRes id: Int) = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("ImageView with drawable same as drawable with id $id")
    }

    override fun matchesSafely(view: View): Boolean {
        val context = view.context
        val expectedBitmap = context.getDrawable(id)?.toBitmap()

        return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
    }
}

fun withDrawable(
    @DrawableRes id: Int,
    @ColorRes tint: Int? = null,
) = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("ImageView with drawable same as drawable with id $id")
        tint?.let { description.appendText(", tint color id: $tint") }
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun matchesSafely(view: View): Boolean {
        val context = view.context
        val expectedBitmap = context.getDrawable(id)?.tinted(tint)?.toBitmap()

        return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
    }

    private fun Drawable.tinted(@ColorInt tintColor: Int? = null) =
        apply {
            setTintList(tintColor?.toColorStateList())
        }

    private fun Int.toColorStateList() = ColorStateList.valueOf(this)
}