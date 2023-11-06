package com.akashdas.recipesearchapp.db

import com.akashdas.recipesearchapp.model.RecipeDetailResponse
import com.akashdas.recipesearchapp.model.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/search")
    suspend fun searchRecipes(@Query("q") query: String): RecipeSearchResponse

    @GET("api/get")
    suspend fun getRecipeDetail(@Query("rId") recipeId: String): RecipeDetailResponse

}