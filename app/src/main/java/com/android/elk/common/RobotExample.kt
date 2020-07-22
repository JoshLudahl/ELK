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

    infix fun goat(init: MainView.() -> Unit) = screen(init)

}


fun verifyNavigation(init: MainViewResult.() -> Unit) =

    MainViewResult().apply { init() }

class MainViewResult : Base() {

    fun doSomething() {

    }

    infix fun thenVerify(view: MainView.()-> Unit) = screen(MainView(), view)

}
class Head(): Base() {

    fun head(init: Head.() -> Unit) = screen(Head(), init)

    fun goat(init: MainView.() -> Unit) = screen(init)
}

fun goat(init: MainView.() -> Unit) = screen(init)

open class Base {
    fun <T> screen(tag: T, init: T.() -> Unit): T {
        tag.init()
        return tag
    }

}

fun <T> screen(init: T.() -> Unit): T {
    return init as T
}