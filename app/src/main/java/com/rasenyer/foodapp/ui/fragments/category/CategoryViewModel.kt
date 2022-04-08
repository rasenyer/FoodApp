package com.rasenyer.foodapp.ui.fragments.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rasenyer.foodapp.data.models.CategoryList
import com.rasenyer.foodapp.data.remote.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel : ViewModel() {

    private var categories = MutableLiveData<CategoryList>()

    init {
        getCategories()
    }

    private fun getCategories() {

        RetrofitInstance.mealApi.getCategories().enqueue(object : Callback<CategoryList> {

            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                categories.value = response.body()
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("Error: ", t.message.toString())
            }

        })

    }

    fun observeCategories(): LiveData<CategoryList> {
        return categories
    }

}