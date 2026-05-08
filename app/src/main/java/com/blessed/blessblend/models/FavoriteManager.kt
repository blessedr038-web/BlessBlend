package com.blessed.blessblend.models



import androidx.compose.runtime.mutableStateListOf
import com.blessed.blessblend.ui.screens.finale.ProductImage

object FavoritesManager {

    // UI STATE: Screens will observe this list for changes
    val savedImages = mutableStateListOf<ProductImage>()

    private val repo = FavoritesRepository()

    // Placeholder until you implement FirebaseAuth
    private const val userId = "defaultUser"

    // -------------------------------
    // TOGGLE FAVORITE (ADD / REMOVE)
    // -------------------------------
    fun toggleFavorite(product: ProductImage) {
        // We find the item by ID to ensure a perfect match
        val existingItem = savedImages.find { it.id == product.id }

        if (existingItem != null) {
            // ❌ REMOVE FROM UI STATE
            savedImages.remove(existingItem)

            // ❌ REMOVE FROM FIREBASE
            repo.removeFavorite(userId, product.id)
        } else {
            // ✔ ADD TO UI STATE
            savedImages.add(product)

            // ✔ CONVERT TO BACKEND MODEL
            val backendModel = FavoriteImage(
                id = product.id,
                imageResId = product.resId,
                label = product.label,
                // We add a string so it shows up in the Firebase Console
                imageUrl = "pending_url_for_id_${product.id}"
            )

            // ✔ SAVE TO FIREBASE
            repo.addFavorite(userId, backendModel)
        }
    }

    // -------------------------------
    // CHECK IF FAVORITE
    // -------------------------------
    fun isFavorite(product: ProductImage): Boolean {
        return savedImages.any { it.id == product.id }
    }

    // -------------------------------
    // LOAD FROM FIREBASE (SYNC BACKEND → UI)
    // -------------------------------
    fun loadFromFirebase(onDone: () -> Unit = {}) {
        repo.getFavorites(userId) { list ->
            // Clear current list to avoid duplicates on refresh
            savedImages.clear()

            // Map the Firebase data back to your ProductImage UI model
            val uiList = list.map { favorite ->
                ProductImage(
                    id = favorite.id,
                    resId = favorite.imageResId,
                    label = favorite.label
                )
            }

            savedImages.addAll(uiList)
            onDone()
        }
    }
}