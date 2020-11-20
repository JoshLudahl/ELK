package com.android.elk.rules

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.intent.Intents
import com.android.elk.espresso.lib.DataBindingIdlingResource
import com.android.elk.espresso.lib.EspressoIdlingResource
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class EspressoSetupRule(activityScenario: ActivityScenario<*>) : TestWatcher() {
    private val dataBindingIdlingResource = DataBindingIdlingResource(activityScenario)

    override fun starting(description: Description?) {
        Intents.init()
        IdlingRegistry
            .getInstance()
            .register(
                EspressoIdlingResource.countingIdlingResource,
                dataBindingIdlingResource
            )

        super.starting(description)
    }

    override fun finished(description: Description?) {
        Intents.release()
        IdlingRegistry
            .getInstance()
            .unregister(
                EspressoIdlingResource.countingIdlingResource,
                dataBindingIdlingResource
            )

        super.finished(description)
    }
}
