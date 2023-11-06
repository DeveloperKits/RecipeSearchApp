package com.akashdas.recipesearchapp.model

import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("recipes") val recipes: List<Recipe>
)

data class Recipe(
    @SerializedName("publisher") val publisher: String,
    @SerializedName("title") val title: String,
    @SerializedName("source_url") val sourceUrl: String,
    @SerializedName("recipe_id") val recipeId: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("social_rank") val socialRank: Float,
    @SerializedName("publisher_url") val publisherUrl: String
)







