package com.rasenyer.foodapp.ui.activities.food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.rasenyer.foodapp.data.models.Food
import com.rasenyer.foodapp.databinding.ActivityFoodBinding
import com.rasenyer.foodapp.ui.activities.detail.DetailActivity
import com.rasenyer.foodapp.ui.activities.food.adapter.FoodAdapter
import com.rasenyer.foodapp.util.Constants.Companion.CATEGORY
import com.rasenyer.foodapp.util.Constants.Companion.ID

class FoodActivity : AppCompatActivity() {

    private lateinit var foodViewModel: FoodViewModel
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var binding: ActivityFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]
        foodAdapter = FoodAdapter()

        setUpRecyclerView()
        getFoodsByCategory()
        observeFoods()
        setOnClickMaterialCardView()
        showProgressBar()

    }

    private fun setUpRecyclerView() {

        binding.mRecyclerView.apply {
            adapter = foodAdapter
        }

    }

    private fun getFoodsByCategory() {

        foodViewModel.getFoodsByCategory(intent.getStringExtra(CATEGORY)!!)

    }

    private fun observeFoods() {

        foodViewModel.observeFoods().observe(this) {

            foodAdapter.setFoods(it!!.meals)
            hideProgressBar()

        }

    }

    private fun setOnClickMaterialCardView() {

        foodAdapter.setOnClickMaterialCardView(object : FoodAdapter.OnClickMaterialCardView {

            override fun onClick(food: Food) {

                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra(ID, food.id)
                startActivity(intent)

            }

        })

    }

    private fun showProgressBar() {

        binding.mProgressBar.visibility = View.VISIBLE

    }

    private fun hideProgressBar() {

        binding.mProgressBar.visibility = View.GONE

    }

}