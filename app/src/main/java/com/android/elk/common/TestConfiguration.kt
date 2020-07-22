package com.android.elk.common

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

val targetContext: Context get() = InstrumentationRegistry.getInstrumentation().targetContext

infix fun Context.stringValue(id: Int) = getString(id)