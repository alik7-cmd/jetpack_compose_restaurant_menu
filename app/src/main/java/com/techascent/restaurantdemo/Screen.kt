package com.techascent.restaurantdemo

sealed class Screen(val route : String){

    object Home : Screen("home")
    object Details : Screen("details")
    object Food : Screen("food")

    fun withArgs(vararg args:String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
