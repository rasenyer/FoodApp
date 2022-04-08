package com.rasenyer.foodapp.ui.fragments.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rasenyer.foodapp.data.local.FavoriteDatabase
import com.rasenyer.foodapp.data.local.FavoriteRepository
import com.rasenyer.foodapp.data.models.Favorite

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var favoriteRepository: FavoriteRepository
    private var favorites: LiveData<List<Favorite>>

    init {

        val favoriteDao = FavoriteDatabase.getInstance(application).favoriteDao()
        favoriteRepository = FavoriteRepository(favoriteDao)
        favorites = favoriteRepository.getAllFavorites

    }

    fun observeFavorites(): LiveData<List<Favorite>> {
        return favorites
    }

}