package com.android.elk

import org.junit.Test

inline fun <reified T: Any> screen(init: T.() -> Unit): T? = T::class.java.newInstance().apply { init() }
inline fun <reified T: Any> verify(init: T.() -> Unit): T? = T::class.java.newInstance().apply { init() }

class SampleTest {

    @Test
    fun sampleTestFunction() {
        screen<SampleObject> {
            sampleFunction()
        }

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
