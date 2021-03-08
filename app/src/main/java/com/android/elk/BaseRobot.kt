package com.android.elk

import com.android.elk.common.Given
import com.android.elk.common.Then
import com.android.elk.common.When
import org.junit.Test

inline fun <reified T: Any> screen(init: T.() -> Unit): T? = T::class.java.newInstance().apply { init() }
inline fun <reified T: Any> operation(init: T.() -> Unit): T? = T::class.java.newInstance().apply { init() }
inline fun <reified T: Any> verify(init: T.() -> Unit): T? = T::class.java.newInstance().apply { init() }

class SampleTest {

    @Test
    fun sampleTestFunction() {
        screen<SampleObject> {
            sampleFunction()
        }

        @Given("I am on the main screen")
        @When("I do simpleFunction")
        operation<SampleObject> {
            sampleFunction()
        }

        @Then("the simpleFunction is displayed")
        verify<SampleObject> {
            sampleFunction()
        }
    }
}

class SampleObject {
    fun sampleFunction() {
        /* No Op */
    }
}
