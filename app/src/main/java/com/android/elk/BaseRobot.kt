package com.android.elk

import org.junit.Test

open class BaseRobot {
    fun <T> screen(init: T.() -> Unit): T = init as T
    fun <T> verify(init: T.() -> Unit): T = init as T
}

class SampleTest:BaseRobot() {

    @Test
    fun sampleTestFunction() {
        screen<SampleObject>{
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
