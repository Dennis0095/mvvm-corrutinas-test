package com.example.domain.repository.recipe

import com.example.domain.model.Recipe
import com.example.domain.model.RecipeHome

interface GetRecipeRepository {

    suspend fun getRecipes(): List<RecipeHome>
}