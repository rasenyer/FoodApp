package com.rasenyer.foodapp.ui.activities.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rasenyer.foodapp.data.local.FavoriteDatabase
import com.rasenyer.foodapp.data.local.FavoriteRepository
import com.rasenyer.foodapp.data.models.Favorite
import com.rasenyer.foodapp.data.models.Meal
import com.rasenyer.foodapp.data.models.MealList
import com.rasenyer.foodapp.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): AndroidViewModel(application) {

    private var favoriteRepository: FavoriteRepository
    private var favorites: LiveData<List<Favorite>>
    private val meal = MutableLiveData<Meal>()

    init {

        val favoriteDao = FavoriteDatabase.getInstance(application).favoriteDao()
        favoriteRepository = FavoriteRepository(favoriteDao)
        favorites = favoriteRepository.getAllFavorites

    }

    fun insertFavorite(favorite: Favorite) {

        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.insertFavorite(favorite)
            withContext(Dispatchers.Main) {}
        }

    }

    fun deleteFavoriteById(id: String) {

        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteFavoriteById(id)
        }

    }

    fun isFavorite(id: String): Boolean {

        var favorite: Favorite?

        runBlocking(Dispatchers.IO) {
            favorite = favoriteRepository.getFavoriteById(id)
        }

        if (favorite == null) return false
        return true

    }

    fun getMealById(id: String) {

        RetrofitInstance.mealApi.getMealById(id).enqueue(object : Callback<MealList> {

            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                meal.value = response.body()!!.meals[0]
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.e("Error: ", t.message.toString())
            }

        })

    }

    fun observeMeal(): LiveData<Meal> {
        return meal
    }

}