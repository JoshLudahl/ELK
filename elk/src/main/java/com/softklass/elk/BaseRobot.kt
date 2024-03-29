package com.softklass.elk

import com.softklass.elk.common.Given
import com.softklass.elk.common.Then
import com.softklass.elk.common.When
import org.junit.Test

// Generic
inline fun <reified T: Any> screen(init: T.() -> Unit): T? =
    T::class.java.getDeclaredConstructor().newInstance().apply { init() }

// Action
inline fun <reified T: Any> operation(init: T.() -> Unit): T? =
    T::class.java.getDeclaredConstructor().newInstance().apply { init() }

// Confirm
inline fun <reified T: Any> verify(init: T.() -> Unit): T? =
    T::class.java.getDeclaredConstructor().newInstance().apply { init() }

// Espresso generic centric
inline fun <reified T: Any> perform(init: T.() -> Unit): T? =
    T::class.java.getDeclaredConstructor().newInstance().apply { init() }
inline fun <reified T: Any> check(init: T.() -> Unit): T? =
    T::class.java.getDeclaredConstructor().newInstance().apply { init() }


class SampleTest {

    @Test
    fun sampleTestFunction() {
        screen<SampleObject> {
            sampleFunction()
        }

        @Given("I am on the main screen")
        @When("I do simpleFunction")
        (operation<SampleObject> {
        sampleFunction()
    })

        @Then("the simpleFunction is displayed")
        (verify<SampleObject> {
        sampleFunction()
    })
    }
}

class SampleObject {
    fun sampleFunction() {
        /* No Op */
    }
}
