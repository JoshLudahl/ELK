package com.android.elk.common


/**
 * Lambda for driving navigation. Example: navigate { toLoadboardTab() } will navigate to the
 * LoadBoard tab.
 */
class MainView  {

    infix fun thenVerify(robot: MainViewResult.() -> Unit): MainViewResult {
        return MainViewResult().apply { robot() }
    }

    fun doThisFirst() {

    }

    infix fun goat(init: MainView.() -> Unit) = robotics(init)

}


fun verifyNavigation(robot: MainViewResult.() -> Unit) =

    MainViewResult().apply { robot() }

class MainViewResult : Base() {

    fun doSomething() {

    }

    infix fun thenVerify(view: MainView.()-> Unit) = robot(MainView(), view)

}
class Head(): Base() {

    fun head(init: Head.() -> Unit) = robot(Head(), init)

    fun goat(init: MainView.() -> Unit) = robotics(init)
}

fun goat(init: MainView.() -> Unit) = robotics(init)

open class Base {
    fun <T> robot(tag: T, init: T.() -> Unit): T {
        tag.init()
        return tag
    }

    fun <T> robotics(init: T.() -> Unit): T {
        return init as T
    }
}

fun <T> robotics(init: T.() -> Unit): T {
    return init as T
}