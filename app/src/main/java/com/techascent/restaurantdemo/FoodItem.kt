package com.techascent.restaurantdemo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodItem(
    val id: Long,
    val name: String,
    val description: String,
    val image : String
) : Parcelable{
    constructor(): this(0, "", "", "")
}
