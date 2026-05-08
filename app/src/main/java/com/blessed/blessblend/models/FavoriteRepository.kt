package com.blessed.blessblend.models



import com.google.firebase.database.FirebaseDatabase

class FavoritesRepository {

    private val db = FirebaseDatabase.getInstance().reference.child("favorites")

    // -------------------------------
    // ADD FAVORITE
    // -------------------------------
    fun addFavorite(userId: String, item: FavoriteImage) {

        db.child(userId)
            .child("items")
            .child(item.id.toString())
            .setValue(item)
    }

    // -------------------------------
    // REMOVE FAVORITE
    // -------------------------------
    fun removeFavorite(userId: String, itemId: Int) {

        db.child(userId)
            .child("items")
            .child(itemId.toString())
            .removeValue()
    }

    // -------------------------------
    // GET FAVORITES
    // -------------------------------
    fun getFavorites(
        userId: String,
        onResult: (List<FavoriteImage>) -> Unit
    ) {

        db.child(userId)
            .child("items")
            .get()
            .addOnSuccessListener { snapshot ->

                val list = snapshot.children.mapNotNull { child ->

                    child.getValue(FavoriteImage::class.java)
                }

                onResult(list)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }
}