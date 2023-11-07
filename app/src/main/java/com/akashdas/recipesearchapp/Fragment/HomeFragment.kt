package com.akashdas.recipesearchapp.Fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.akashdas.recipesearchapp.Adapter.RecipeAdapter
import com.akashdas.recipesearchapp.utils.Singleton
import com.akashdas.recipesearchapp.R
import com.akashdas.recipesearchapp.Repository.RecipeRepository
import com.akashdas.recipesearchapp.ViewModel.RecipeViewModel
import com.akashdas.recipesearchapp.ViewModel.RecipeViewModelFactory
import com.akashdas.recipesearchapp.databinding.FragmentHomeBinding
import com.akashdas.recipesearchapp.db.ApiClient
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {
    private lateinit var viewModel: RecipeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // all about chip
        chip()
        searchData("carrot")

        binding.profileButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.searchButton.setOnClickListener {
            val text = binding.editTextText.text.toString()

            searchData(text)
        }
    }

    private fun chip() {
        binding.chipGroup.isSingleSelection = true

        Singleton.chipTexts.forEach { text ->
            val chip = Chip(context).apply {
                this.text = text
                isCheckable = true
                // Define color in your colors.xml
                chipStrokeColor = ColorStateList
                    .valueOf(ContextCompat.getColor(context, R.color.chip_color))
            }
            binding.chipGroup.addView(chip)
        }

        // select the first chip by default
        (binding.chipGroup.getChildAt(0) as? Chip)?.isChecked = true

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            // The 'checkedId' is the ID of the selected chip.
            val chip = group.findViewById<Chip>(checkedId)
            chip?.let {
                val selectedText: String = it.text.toString()

                binding.editTextText.setText(selectedText)

                searchData(selectedText)
            }

        }

    }

    private fun searchData(selectedText: String) {
        val repository = RecipeRepository(ApiClient.instance)
        val factory = RecipeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(RecipeViewModel::class.java)

        binding.recycle.layoutManager = LinearLayoutManager(context)

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading){
                binding.gif.visibility = View.VISIBLE
                loadGifIntoImageView(binding.gif, R.drawable.loading)
            }else{
                binding.gif.visibility = View.GONE
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {errorMessage ->
            if (errorMessage.isNotEmpty()){
                Log.d("DataError", errorMessage)
                binding.gif.visibility = View.VISIBLE
                loadGifIntoImageView(binding.gif, R.drawable.nodata)
            }
        }

        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            binding.totalRecipe.text = "Total recipe list of $selectedText: ${recipes.size}"
            val adapter = RecipeAdapter(recipes, repository)
            binding.recycle.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        // search by selected chip
        viewModel.searchRecipes(selectedText)

    }

    fun loadGifIntoImageView(imageView: ImageView, gifUrl: Int) {
        Glide.with(requireContext())
            .asGif()
            .load(gifUrl)
            .into(imageView)
    }
}