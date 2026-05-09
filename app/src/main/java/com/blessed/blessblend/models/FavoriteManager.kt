package com.blessed.blessblend.models

import androidx.compose.runtime.mutableStateListOf
import com.blessed.blessblend.ui.screens.finale.ProductImage

// 🎯 This import MUST match where your ProductImage file is located

object FavoritesManager {
    val savedImages = mutableStateListOf<ProductImage>()
    private val repo = FavoritesRepository()

    // 🎯 Matches your Firebase console UID
    private const val userId = "PdP2w9020zQi1OK1oMEzzBjszBh2"

    fun toggleFavorite(product: ProductImage) {
        val existing = savedImages.find { it.id == product.id }

        if (existing != null) {
            savedImages.remove(existing)
            repo.removeFavorite(userId, product.id)
        } else {
            savedImages.add(product)
            val backendModel = FavoriteImage(
                id = product.id,
                imageResId = product.resId,
                label = product.label,
                // 🔥 "pending" ensures the URL key is created in the console
                imageUrl = if (product.imageUrl.isEmpty()) "pending" else product.imageUrl
            )
            repo.addFavorite(userId, backendModel)
        }
    }

    fun isFavorite(product: ProductImage): Boolean = savedImages.any { it.id == product.id }

    fun loadFromFirebase(onDone: () -> Unit = {}) {
        repo.getFavorites(userId) { list ->
            savedImages.clear()
            // Map the backend data back into UI-friendly ProductImage objects
            savedImages.addAll(list.map {
                ProductImage(
                    id = it.id,
                    resId = it.imageResId,
                    label = it.label,
                    imageUrl = it.imageUrl
                )
            })
            onDone()
        }
    }
}