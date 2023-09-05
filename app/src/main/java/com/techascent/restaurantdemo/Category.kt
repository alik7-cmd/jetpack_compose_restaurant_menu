package com.techascent.restaurantdemo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Category(
    val id: Long,
    val name: String,
    val image: String,
    val lastUpdated : Date = Date()
) : Parcelable{

    constructor() : this(0, "", "")
}
