package com.example.domain.repository.recipe

import com.example.domain.model.Recipe

interface SearchRecipeRepository {

    suspend fun getRecipeId(idRecipe: Int): Recipe
}