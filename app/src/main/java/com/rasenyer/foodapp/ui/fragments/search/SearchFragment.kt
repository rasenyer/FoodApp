package com.rasenyer.foodapp.ui.fragments.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.rasenyer.foodapp.R
import com.rasenyer.foodapp.databinding.FragmentSearchBinding
import com.rasenyer.foodapp.ui.activities.detail.DetailActivity
import com.rasenyer.foodapp.util.Constants.Companion.ID

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var id = ""
    private var name = ""
    private var imageLink = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeMeal()
        setOnClickSearchView()
        setOnClickMaterialCardView()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeMeal() {

        searchViewModel.observeMeal().observe(viewLifecycleOwner) {

            binding.apply {

                id = it.id!!
                name = it.name!!
                imageLink = it.imageLink!!

                binding.mImageView.load(it.imageLink) {
                    placeholder(R.color.purple_200)
                    error(R.color.purple_200)
                    crossfade(true)
                    crossfade(400)
                }
                mTextViewName.text = it.name

                mMaterialCardView.visibility = View.VISIBLE

            }

        }

    }

    private fun setOnClickSearchView() {

        binding.mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(mealName: String?): Boolean {

                if (mealName != null) {
                    searchViewModel.getMealByName(mealName, context)
                }

                return true

            }

            override fun onQueryTextChange(newMealName: String?): Boolean {
                return true
            }

        })

    }

    private fun setOnClickMaterialCardView() {

        binding.mMaterialCardView.setOnClickListener {

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ID, id)
            startActivity(intent)

        }

    }

}