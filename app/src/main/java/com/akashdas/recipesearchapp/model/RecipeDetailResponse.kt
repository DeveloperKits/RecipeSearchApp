package com.akashdas.recipesearchapp.model

import com.google.gson.annotations.SerializedName

data class RecipeDetailResponse(
    @SerializedName("recipe") val recipe: RecipeDetail
)

data class RecipeDetail(
    @SerializedName("publisher") val publisher: String,
    @SerializedName("ingredients") val ingredients: List<String>,
    @SerializedName("source_url") val sourceUrl: String,
    @SerializedName("recipe_id") val recipeId: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("social_rank") val socialRank: Double, // Changed to Double for higher precision
    @SerializedName("publisher_url") val publisherUrl: String,
    @SerializedName("title") val title: String
)
