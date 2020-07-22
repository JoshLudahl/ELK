package com.android.elk.common


/**
 * Lambda for driving navigation. Example: navigate { toLoadboardTab() } will navigate to the
 * LoadBoard tab.
 */
//fun navigate(robot: NavigationRobot.() -> Unit) = NavigationRobot().apply { robot() }

//class NavigationRobot {
//
//    fun toDashboardTab() = click on tab(HOME)
//
//    fun toLoadBoardTab() = click on tab(LOADBOARD)
//
//    fun toWorklistTab() = click on tab(WORKLIST)
//
//    fun toMapTab() = click on tab(MAP)
//
//    fun toAccountTab() = click on tab(ACCOUNT)
//}
//
//fun selectNavigationTab(tab: Tabs): Matcher<View> {
//    return when (tab) {
//        HOME -> withId(R.id.nav_home)
//        LOADBOARD -> withId(R.id.nav_loadboard)
//        WORKLIST -> withId(R.id.nav_worklist)
//        MAP -> withId(R.id.nav_map)
//        ACCOUNT -> withId(R.id.nav_account)
//    }
//}