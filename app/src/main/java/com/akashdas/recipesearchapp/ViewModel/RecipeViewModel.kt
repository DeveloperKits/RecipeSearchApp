package com.akashdas.recipesearchapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashdas.recipesearchapp.Repository.RecipeRepository
import com.akashdas.recipesearchapp.model.Recipe
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {
    val recipes = MutableLiveData<List<Recipe>>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun searchRecipes(query: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.searchRecipes(query)
                isLoading.value = false
                if (response.recipes.isNotEmpty()) {
                    recipes.value = response.recipes
                } else {
                    errorMessage.value = "No recipes found for the query: $query"
                }
            } catch (e: Exception) {
                isLoading.value = false
                errorMessage.value = e.message ?: "An error occurred"
            }
        }

    }
}
