package com.android.elk.common

import android.app.Instrumentation
import android.content.Context
import android.content.res.AssetManager
import androidx.test.platform.app.InstrumentationRegistry

/**
 * Application context.
 */
val targetContext: Context get() = InstrumentationRegistry.getInstrumentation().targetContext

/**
 * Instrumentation context
 */
val testContext: Context get() = InstrumentationRegistry.getInstrumentation().context

/**
 * AssetManager for androidTest environment
 */
val testAssets: AssetManager = testContext.assets

/**
 * Test runner instrumentation
 */
val instrumentation: Instrumentation get() = InstrumentationRegistry.getInstrumentation()

/**
 * Sugar syntax for getting a string value
 */
infix fun Context.stringValue(id: Int) = getString(id)