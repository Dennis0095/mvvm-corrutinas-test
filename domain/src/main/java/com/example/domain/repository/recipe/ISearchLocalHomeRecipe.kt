package com.example.domain.repository.recipe

import com.example.domain.model.Recipe
import com.example.domain.model.RecipeHome

interface ISearchLocalHomeRecipe {

    suspend fun searchLocalHomeRecipe(word: String) : List<RecipeHome>
}