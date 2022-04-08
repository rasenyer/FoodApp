package com.rasenyer.foodapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rasenyer.foodapp.data.models.Favorite

@Dao
interface FavoriteDao {

    @Insert
    fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite_table WHERE id = :id")
    fun deleteFavoriteById(id: String)

    @Query("SELECT * FROM favorite_table WHERE id = :id")
    fun getFavoriteById(id: String): Favorite

    @Query("SELECT * FROM favorite_table order by name ASC")
    fun getAllFavorites(): LiveData<List<Favorite>>

}