package com.akashdas.recipesearchapp.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akashdas.recipesearchapp.R
import com.akashdas.recipesearchapp.Repository.RecipeRepository
import com.akashdas.recipesearchapp.databinding.ItemRecipeBinding
import com.akashdas.recipesearchapp.model.Recipe
import com.akashdas.recipesearchapp.utils.Singleton
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeAdapter(
    private var recipes: List<Recipe>,
    private val repository: RecipeRepository) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding, viewModelScope, repository)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    class RecipeViewHolder(
        private val binding: ItemRecipeBinding,
        private val scope: CoroutineScope,
        private val repository: RecipeRepository
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.titleText.text = recipe.title
            binding.publisher.text = "${Singleton.publish} ${recipe.publisher}"
            binding.socialRank.text = "${Singleton.socialRank} ${recipe.socialRank}"
            binding.recipeId.text = "#${recipe.recipeId}"

            Log.d("ImageUrl", recipe.imageUrl)
            Log.d("ImageUrl", recipe.socialRank.toString())
            Glide.with(binding.root.context)
                .load(recipe.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.logo)
                .into(binding.imageUrl)

            // get data from specific recipe and set to ingredientsText
            scope.launch {
                try {
                    val details = withContext(Dispatchers.IO) {
                        repository.getRecipeDetail(recipe.recipeId)
                    }
                    binding.ingredientsText.text = details.recipe.ingredients.joinToString(", ")
                } catch (e: Exception) {
                    // Handle errors
                }
            }
        }
    }
}
