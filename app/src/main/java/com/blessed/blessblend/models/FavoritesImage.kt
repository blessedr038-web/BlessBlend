package com.blessed.blessblend.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class FavoriteImage(
    val id: Int = 0,
    val imageUrl: String = "pending", // 🔥 Changed from "" to "pending" so it's visible in console
    val imageResId: Int = 0,
    val label: String = "",
    val timestamp: Long = System.currentTimeMillis()
) {
    constructor() : this(0, "pending", 0, "", 0L)
}