package com.techascent.restaurantdemo

sealed class Screen(val route : String){

    object Category : Screen("category")
    object Details : Screen("details")

    fun withArgs(vararg args:String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
