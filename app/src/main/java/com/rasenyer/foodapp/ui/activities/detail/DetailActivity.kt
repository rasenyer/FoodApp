package com.rasenyer.foodapp.ui.activities.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.rasenyer.foodapp.R
import com.rasenyer.foodapp.data.models.Favorite
import com.rasenyer.foodapp.data.models.Meal
import com.rasenyer.foodapp.databinding.ActivityDetailBinding
import com.rasenyer.foodapp.util.Constants.Companion.ID

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding
    private lateinit var meal: Meal
    private var id = ""
    private var linkVideo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        getIdFromIntent()
        getMealById()
        observeMeal()
        setFloatingActionButtonStatues()
        setOnClickTextViewWatchVideo()
        setOnClickFloatingActionButton()
        showLoading()

    }

    private fun getIdFromIntent() {

        val intent = intent
        id = intent.getStringExtra(ID)!!

    }

    private fun getMealById() {

        detailViewModel.getMealById(id)

    }

    private fun observeMeal() {

        detailViewModel.observeMeal().observe(this) {

            this.meal = it
            linkVideo = meal.videoLink!!

            setViews()
            hideLoading()

        }

    }

    @SuppressLint("SetTextI18n")
    private fun setViews() {

        binding.apply {

            binding.mImageView.load(meal.imageLink) {
                placeholder(R.color.purple_200)
                error(R.color.purple_200)
                crossfade(true)
                crossfade(400)
            }
            mTextViewCategory.text = resources.getString(R.string.category) + meal.category
            mTextViewArea.text = resources.getString(R.string.area) + meal.area
            mTextViewName.text = meal.name
            mTextViewInstruction.text = meal.instruction

            if (meal.ingredient1 == "") {
                binding.mTextViewIngredient1.visibility = View.GONE
                binding.mTextViewMeasure1.visibility = View.GONE
            } else {
                binding.mTextViewIngredient1.visibility = View.VISIBLE
                binding.mTextViewMeasure1.visibility = View.VISIBLE
                binding.mTextViewIngredient1.text = meal.ingredient1
                binding.mTextViewMeasure1.text = meal.strMeasure1
            }

            if (meal.ingredient2 == "") {
                binding.mTextViewIngredient2.visibility = View.GONE
                binding.mTextViewMeasure2.visibility = View.GONE
            } else {
                binding.mTextViewIngredient2.visibility = View.VISIBLE
                binding.mTextViewMeasure2.visibility = View.VISIBLE
                binding.mTextViewIngredient2.text = meal.ingredient2
                binding.mTextViewMeasure2.text = meal.strMeasure2
            }

            if (meal.ingredient3 == "") {
                binding.mTextViewIngredient3.visibility = View.GONE
                binding.mTextViewMeasure3.visibility = View.GONE
            } else {
                binding.mTextViewIngredient3.visibility = View.VISIBLE
                binding.mTextViewMeasure3.visibility = View.VISIBLE
                binding.mTextViewIngredient3.text = meal.ingredient3
                binding.mTextViewMeasure3.text = meal.strMeasure3
            }

            if (meal.ingredient4 == "") {
                binding.mTextViewIngredient4.visibility = View.GONE
                binding.mTextViewMeasure4.visibility = View.GONE
            } else {
                binding.mTextViewIngredient4.visibility = View.VISIBLE
                binding.mTextViewMeasure4.visibility = View.VISIBLE
                binding.mTextViewIngredient4.text = meal.ingredient4
                binding.mTextViewMeasure4.text = meal.strMeasure4
            }

            if (meal.ingredient5 == "") {
                binding.mTextViewIngredient5.visibility = View.GONE
                binding.mTextViewMeasure5.visibility = View.GONE
            } else {
                binding.mTextViewIngredient5.visibility = View.VISIBLE
                binding.mTextViewMeasure5.visibility = View.VISIBLE
                binding.mTextViewIngredient5.text = meal.ingredient5
                binding.mTextViewMeasure5.text = meal.strMeasure5
            }

            if (meal.ingredient6 == "") {
                binding.mTextViewIngredient6.visibility = View.GONE
                binding.mTextViewMeasure6.visibility = View.GONE
            } else {
                binding.mTextViewIngredient6.visibility = View.VISIBLE
                binding.mTextViewMeasure6.visibility = View.VISIBLE
                binding.mTextViewIngredient6.text = meal.ingredient6
                binding.mTextViewMeasure6.text = meal.strMeasure6
            }

            if (meal.ingredient7 == "") {
                binding.mTextViewIngredient7.visibility = View.GONE
                binding.mTextViewMeasure7.visibility = View.GONE
            } else {
                binding.mTextViewIngredient7.visibility = View.VISIBLE
                binding.mTextViewMeasure7.visibility = View.VISIBLE
                binding.mTextViewIngredient7.text = meal.ingredient7
                binding.mTextViewMeasure7.text = meal.strMeasure7
            }

            if (meal.ingredient8 == "") {
                binding.mTextViewIngredient8.visibility = View.GONE
                binding.mTextViewMeasure8.visibility = View.GONE
            } else {
                binding.mTextViewIngredient8.visibility = View.VISIBLE
                binding.mTextViewMeasure8.visibility = View.VISIBLE
                binding.mTextViewIngredient8.text = meal.ingredient8
                binding.mTextViewMeasure8.text = meal.strMeasure8
            }

            if (meal.ingredient9 == "") {
                binding.mTextViewIngredient9.visibility = View.GONE
                binding.mTextViewMeasure9.visibility = View.GONE
            } else {
                binding.mTextViewIngredient9.visibility = View.VISIBLE
                binding.mTextViewMeasure9.visibility = View.VISIBLE
                binding.mTextViewIngredient9.text = meal.ingredient9
                binding.mTextViewMeasure9.text = meal.strMeasure9
            }

            if (meal.ingredient10 == "") {
                binding.mTextViewIngredient10.visibility = View.GONE
                binding.mTextViewMeasure10.visibility = View.GONE
            } else {
                binding.mTextViewIngredient10.visibility = View.VISIBLE
                binding.mTextViewMeasure10.visibility = View.VISIBLE
                binding.mTextViewIngredient10.text = meal.ingredient10
                binding.mTextViewMeasure10.text = meal.strMeasure10
            }

            if (meal.ingredient11 == "") {
                binding.mTextViewIngredient11.visibility = View.GONE
                binding.mTextViewMeasure11.visibility = View.GONE
            } else {
                binding.mTextViewIngredient11.visibility = View.VISIBLE
                binding.mTextViewMeasure11.visibility = View.VISIBLE
                binding.mTextViewIngredient11.text = meal.ingredient11
                binding.mTextViewMeasure11.text = meal.strMeasure11
            }

            if (meal.ingredient12 == "") {
                binding.mTextViewIngredient12.visibility = View.GONE
                binding.mTextViewMeasure12.visibility = View.GONE
            } else {
                binding.mTextViewIngredient12.visibility = View.VISIBLE
                binding.mTextViewMeasure12.visibility = View.VISIBLE
                binding.mTextViewIngredient12.text = meal.ingredient12
                binding.mTextViewMeasure12.text = meal.strMeasure12
            }

            if (meal.ingredient13 == "") {
                binding.mTextViewIngredient13.visibility = View.GONE
                binding.mTextViewMeasure13.visibility = View.GONE
            } else {
                binding.mTextViewIngredient13.visibility = View.VISIBLE
                binding.mTextViewMeasure13.visibility = View.VISIBLE
                binding.mTextViewIngredient13.text = meal.ingredient13
                binding.mTextViewMeasure13.text = meal.strMeasure13
            }

            if (meal.ingredient14 == "") {
                binding.mTextViewIngredient14.visibility = View.GONE
                binding.mTextViewMeasure14.visibility = View.GONE
            } else {
                binding.mTextViewIngredient14.visibility = View.VISIBLE
                binding.mTextViewMeasure14.visibility = View.VISIBLE
                binding.mTextViewIngredient14.text = meal.ingredient14
                binding.mTextViewMeasure14.text = meal.strMeasure14
            }

            if (meal.ingredient15 == "") {
                binding.mTextViewIngredient15.visibility = View.GONE
                binding.mTextViewMeasure15.visibility = View.GONE
            } else {
                binding.mTextViewIngredient15.visibility = View.VISIBLE
                binding.mTextViewMeasure15.visibility = View.VISIBLE
                binding.mTextViewIngredient15.text = meal.ingredient15
                binding.mTextViewMeasure15.text = meal.strMeasure15
            }

            if (meal.ingredient16 == "") {
                binding.mTextViewIngredient16.visibility = View.GONE
                binding.mTextViewMeasure16.visibility = View.GONE
            } else {
                binding.mTextViewIngredient16.visibility = View.VISIBLE
                binding.mTextViewMeasure16.visibility = View.VISIBLE
                binding.mTextViewIngredient16.text = meal.ingredient16
                binding.mTextViewMeasure16.text = meal.strMeasure16
            }

            if (meal.ingredient17 == "") {
                binding.mTextViewIngredient17.visibility = View.GONE
                binding.mTextViewMeasure17.visibility = View.GONE
            } else {
                binding.mTextViewIngredient17.visibility = View.VISIBLE
                binding.mTextViewMeasure17.visibility = View.VISIBLE
                binding.mTextViewIngredient17.text = meal.ingredient17
                binding.mTextViewMeasure17.text = meal.strMeasure17
            }

            if (meal.ingredient18 == "") {
                binding.mTextViewIngredient18.visibility = View.GONE
                binding.mTextViewMeasure18.visibility = View.GONE
            } else {
                binding.mTextViewIngredient18.visibility = View.VISIBLE
                binding.mTextViewMeasure18.visibility = View.VISIBLE
                binding.mTextViewIngredient18.text = meal.ingredient18
                binding.mTextViewMeasure18.text = meal.strMeasure18
            }

            if (meal.ingredient19 == "") {
                binding.mTextViewIngredient19.visibility = View.GONE
                binding.mTextViewMeasure19.visibility = View.GONE
            } else {
                binding.mTextViewIngredient19.visibility = View.VISIBLE
                binding.mTextViewMeasure19.visibility = View.VISIBLE
                binding.mTextViewIngredient19.text = meal.ingredient19
                binding.mTextViewMeasure19.text = meal.strMeasure19
            }

            if (meal.ingredient20 == "") {
                binding.mTextViewIngredient20.visibility = View.GONE
                binding.mTextViewMeasure20.visibility = View.GONE
            } else {
                binding.mTextViewIngredient20.visibility = View.VISIBLE
                binding.mTextViewMeasure20.visibility = View.VISIBLE
                binding.mTextViewIngredient20.text = meal.ingredient1
                binding.mTextViewMeasure20.text = meal.strMeasure1
            }

        }

    }

    private fun setFloatingActionButtonStatues() {

        if (isFavorite()) {
            binding.mFloatingActionButton.setImageResource(R.drawable.ic_favored)
        } else {
            binding.mFloatingActionButton.setImageResource(R.drawable.ic_favorite)
        }

    }

    private fun setOnClickTextViewWatchVideo() {

        binding.mTextViewWatchVideo.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(linkVideo)))
        }

    }

    private fun setOnClickFloatingActionButton() {

        binding.mFloatingActionButton.setOnClickListener {

            if (isFavorite()) {

                deleteFavoriteById()
                binding.mFloatingActionButton.setImageResource(R.drawable.ic_favorite)
                Snackbar.make(findViewById(android.R.id.content), R.string.meal_removed_from_favorites, Snackbar.LENGTH_SHORT).show()

            } else {

                insertFavorite()
                binding.mFloatingActionButton.setImageResource(R.drawable.ic_favored)
                Snackbar.make(findViewById(android.R.id.content), R.string.meal_saved_in_favorites, Snackbar.LENGTH_SHORT).show()

            }

        }

    }

    private fun showLoading() {

        binding.mImageView.visibility = View.GONE
        binding.mTextViewCategory.visibility = View.GONE
        binding.mTextViewArea.visibility = View.GONE
        binding.mTextViewName.visibility = View.GONE
        binding.mTextViewInstruction.visibility = View.GONE
        binding.mTextViewWatchVideo.visibility = View.GONE
        binding.mFloatingActionButton.visibility = View.GONE
        binding.mProgressBar.visibility = View.VISIBLE

    }

    private fun hideLoading() {

        binding.mImageView.visibility = View.VISIBLE
        binding.mTextViewCategory.visibility = View.VISIBLE
        binding.mTextViewArea.visibility = View.VISIBLE
        binding.mTextViewName.visibility = View.VISIBLE
        binding.mTextViewInstruction.visibility = View.VISIBLE
        binding.mTextViewWatchVideo.visibility = View.VISIBLE
        binding.mFloatingActionButton.visibility = View.VISIBLE
        binding.mProgressBar.visibility = View.GONE

    }

    private fun insertFavorite() {

        val favorite = Favorite(
            id = meal.id!!.toInt(),
            name = meal.name!!,
            imageLink = meal.imageLink!!
        )

        detailViewModel.insertFavorite(favorite)

    }

    private fun deleteFavoriteById() {

        detailViewModel.deleteFavoriteById(id)

    }

    private fun isFavorite(): Boolean {

        return detailViewModel.isFavorite(id)

    }

}