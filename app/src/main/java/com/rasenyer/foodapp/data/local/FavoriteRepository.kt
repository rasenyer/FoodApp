package com.rasenyer.foodapp.data.local

import androidx.lifecycle.LiveData
import com.rasenyer.foodapp.data.models.Favorite

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    fun insertFavorite(favorite: Favorite) {
        favoriteDao.insertFavorite(favorite)
    }

    fun deleteFavoriteById(id: String) {
        favoriteDao.deleteFavoriteById(id)
    }

    fun getFavoriteById(id: String): Favorite {
        return favoriteDao.getFavoriteById(id)
    }

    val getAllFavorites: LiveData<List<Favorite>> = favoriteDao.getAllFavorites()

}