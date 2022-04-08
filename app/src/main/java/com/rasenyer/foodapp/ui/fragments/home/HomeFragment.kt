package com.rasenyer.foodapp.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rasenyer.foodapp.data.models.Food
import com.rasenyer.foodapp.databinding.FragmentHomeBinding
import com.rasenyer.foodapp.ui.activities.detail.DetailActivity
import com.rasenyer.foodapp.ui.fragments.home.adapter.HomeAdapter
import com.rasenyer.foodapp.util.Constants.Companion.ID

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeAdapter = HomeAdapter()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        getFoodsByCategory()
        observeFoods()
        setOnClickMaterialCardView()
        showProgressBar()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView() {

        binding.mRecyclerView.apply {
            adapter = homeAdapter
        }

    }

    private fun getFoodsByCategory() {

        homeViewModel.getFoodsByCategory("beef")

    }

    private fun observeFoods() {

        homeViewModel.observeFoods().observe(viewLifecycleOwner) {
            homeAdapter.setFoods(it!!.meals)
            hideProgressBar()
        }

    }

    private fun setOnClickMaterialCardView() {

        homeAdapter.setOnClickMaterialCardView(object : HomeAdapter.OnClickMaterialCardView {

            override fun onClick(food: Food) {

                val intent = Intent(activity, DetailActivity::class.java)
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