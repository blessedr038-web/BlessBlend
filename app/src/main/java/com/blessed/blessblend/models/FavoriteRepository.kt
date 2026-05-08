package com.blessed.blessblend.models

import com.google.firebase.database.FirebaseDatabase

class FavoritesRepository {
    // 🔥 Changed path to "Users" to match your screenshot
    private val db = FirebaseDatabase.getInstance().reference.child("Users")

    fun addFavorite(userId: String, item: FavoriteImage) {
        db.child(userId)
            .child("favorites") // Saves inside the user's folder
            .child(item.id.toString())
            .setValue(item)
    }

    fun removeFavorite(userId: String, itemId: Int) {
        db.child(userId)
            .child("favorites")
            .child(itemId.toString())
            .removeValue()
    }

    fun getFavorites(userId: String, onResult: (List<FavoriteImage>) -> Unit) {
        db.child(userId).child("favorites").get().addOnSuccessListener { snapshot ->
            val list = snapshot.children.mapNotNull { it.getValue(FavoriteImage::class.java) }
            onResult(list)
        }.addOnFailureListener { onResult(emptyList()) }
    }
}