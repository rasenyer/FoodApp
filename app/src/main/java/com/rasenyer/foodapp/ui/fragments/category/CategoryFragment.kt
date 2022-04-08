package com.rasenyer.foodapp.ui.fragments.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rasenyer.foodapp.data.models.Category
import com.rasenyer.foodapp.databinding.FragmentCategoryBinding
import com.rasenyer.foodapp.ui.activities.food.FoodActivity
import com.rasenyer.foodapp.ui.fragments.category.adapter.CategoryAdapter
import com.rasenyer.foodapp.util.Constants.Companion.CATEGORY

class CategoryFragment : Fragment() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        categoryAdapter = CategoryAdapter()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        observeCategories()
        setOnClickMaterialCardView()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView() {

        binding.mRecyclerView.apply {
            adapter = categoryAdapter
        }

    }

    private fun observeCategories() {

        categoryViewModel.observeCategories().observe(viewLifecycleOwner) {
            categoryAdapter.setCategories(it!!.categories)
        }

    }

    private fun setOnClickMaterialCardView() {

        categoryAdapter.setOnClickMaterialCardView(object : CategoryAdapter.OnClickMaterialCardView {

            override fun onClick(category: Category) {

                val intent = Intent(context, FoodActivity::class.java)
                intent.putExtra(CATEGORY, category.name)
                startActivity(intent)

            }

        })

    }

}