package com.blessed.blessblend.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class FavoriteImage(
    val id: Int = 0,
    val imageUrl: String = "",
    val imageResId: Int = 0,
    val label: String = "",
    val timestamp: Long = System.currentTimeMillis()
) {
    // This empty constructor is the most common reason for Firebase crashes
    constructor() : this(0, "", 0, "", 0L)
}