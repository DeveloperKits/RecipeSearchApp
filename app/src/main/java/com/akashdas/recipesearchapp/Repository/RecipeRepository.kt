package com.akashdas.recipesearchapp.Repository

import com.akashdas.recipesearchapp.db.ApiService

class RecipeRepository(private val apiService: ApiService) {
    suspend fun searchRecipes(query: String) = apiService.searchRecipes(query)
    suspend fun getRecipeDetail(recipeId: String) = apiService.getRecipeDetail(recipeId)
}
