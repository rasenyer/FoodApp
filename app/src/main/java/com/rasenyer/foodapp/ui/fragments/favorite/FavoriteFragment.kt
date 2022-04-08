package com.rasenyer.foodapp.ui.fragments.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rasenyer.foodapp.data.models.Favorite
import com.rasenyer.foodapp.databinding.FragmentFavoriteBinding
import com.rasenyer.foodapp.ui.activities.detail.DetailActivity
import com.rasenyer.foodapp.ui.fragments.favorite.adapter.FavoriteAdapter
import com.rasenyer.foodapp.util.Constants.Companion.ID

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var favoriteAdapter: FavoriteAdapter
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        favoriteAdapter = FavoriteAdapter()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        observeFavorites()
        setOnClickMaterialCardView()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView() {

        binding.mRecyclerView.apply {
            adapter = favoriteAdapter
        }

    }

    private fun observeFavorites() {

        favoriteViewModel.observeFavorites().observe(viewLifecycleOwner) {

            favoriteAdapter.setFavorites(it)
            if (it.isEmpty()) binding.mTextView.visibility = View.VISIBLE
            else binding.mTextView.visibility = View.GONE

        }

    }

    private fun setOnClickMaterialCardView() {

        favoriteAdapter.setOnClickMaterialCardView(object : FavoriteAdapter.OnClickMaterialCardView {

            override fun onClick(favorite: Favorite) {

                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(ID, favorite.id.toString())
                startActivity(intent)

            }

        })

    }

}