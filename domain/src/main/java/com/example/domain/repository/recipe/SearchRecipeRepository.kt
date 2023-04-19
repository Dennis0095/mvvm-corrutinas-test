package com.example.domain.repository.recipe

import com.example.domain.model.Recipe
import com.example.domain.model.RecipeHome

interface SearchRecipeRepository {

    suspend fun getRecipeId(idRecipe: Int): Recipe
    suspend fun getRecipeWord(word: String): List<RecipeHome>
}