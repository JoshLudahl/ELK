package com.android.elk.common

import android.app.Instrumentation
import android.content.Context
import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import androidx.test.platform.app.InstrumentationRegistry
import java.util.*

/**
 *  Application context
 */
val targetContext: Context get() = InstrumentationRegistry.getInstrumentation().targetContext

/**
 *  Instrumentation context
 */
val testContext: Context get() = InstrumentationRegistry.getInstrumentation().context

/**
 *  AssetManager for androidTest environment
 */
val testAssets: AssetManager = testContext.assets

/**
 *  Test runner instrumentation
 */
val instrumentation: Instrumentation get() = InstrumentationRegistry.getInstrumentation()

/**
 *  Returns
 */
fun Context.getConfigurationResources(locale: Locale): Resources {
    val configuration = Configuration(resources.configuration)
    configuration.setLocale(locale)
    return createConfigurationContext(configuration).resources
}

infix fun Resources.nameOf(viewId: Int): String = getResourceName(viewId)

/**
 * Gets a application configuration with forced [Locale.GERMAN]
 */
fun Context.getGermanConfiguration() = getConfigurationResources(Locale.GERMAN)

/**
 * Gets a application configuration with forced [Locale.ENGLISH]
 */
fun Context.getEnglishConfiguration() = getConfigurationResources(Locale.ENGLISH)

/**
 * Sugar syntax for getting a string value
 */
infix fun Context.stringValue(id: Int) = getString(id)
