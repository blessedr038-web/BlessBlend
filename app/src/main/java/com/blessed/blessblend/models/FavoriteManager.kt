package com.blessed.blessblend.models

import androidx.compose.runtime.mutableStateListOf
import com.blessed.blessblend.ui.screens.finale.ProductImage

object FavoritesManager {
    val savedImages = mutableStateListOf<ProductImage>()
    private val repo = FavoritesRepository()

    // 🔥 Use the UID from your screenshot so you can see it in the console!
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
                imageUrl = "https://placeholder.com/img_${product.id}.jpg" // 🔥 Dummy URL so it appears
            )
            repo.addFavorite(userId, backendModel)
        }
    }

    fun isFavorite(product: ProductImage): Boolean = savedImages.any { it.id == product.id }

    fun loadFromFirebase(function: () -> Unit) {
        repo.getFavorites(userId) { list ->
            savedImages.clear()
            savedImages.addAll(list.map {
                ProductImage(id = it.id, resId = it.imageResId, label = it.label)
            })
        }
    }
}