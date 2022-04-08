package com.rasenyer.foodapp.ui.activities.food.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rasenyer.foodapp.R
import com.rasenyer.foodapp.data.models.Food
import com.rasenyer.foodapp.databinding.ItemFoodBinding

class FoodAdapter: RecyclerView.Adapter<FoodAdapter.MyFoodViewHolder>() {

    private var foods: List<Food> = ArrayList()
    private lateinit var onClickMaterialCardView: OnClickMaterialCardView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFoodViewHolder {
        return MyFoodViewHolder(ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(myFoodViewHolder: MyFoodViewHolder, position: Int) {

        val food = foods[position]

        myFoodViewHolder.binding.apply {

            mImageView.load(food.imageLink) {
                placeholder(R.color.purple_200)
                error(R.color.purple_200)
                crossfade(true)
                crossfade(400)
            }
            mTextViewName.text = food.name

        }

        myFoodViewHolder.binding.mMaterialCardView.setOnClickListener {
            onClickMaterialCardView.onClick(food)
        }

    }

    override fun getItemCount(): Int {
        return foods.size
    }

    class MyFoodViewHolder(val binding: ItemFoodBinding): RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setFoods(foods: List<Food>) {
        this.foods = foods
        notifyDataSetChanged()
    }

    fun setOnClickMaterialCardView(onClickMaterialCardView: OnClickMaterialCardView) {
        this.onClickMaterialCardView = onClickMaterialCardView
    }

    interface OnClickMaterialCardView {
        fun onClick(food: Food)
    }

}