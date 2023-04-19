package com.example.domain.repository.recipe

import com.example.domain.core.RequestResult
import com.example.domain.model.DetailRecipe
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeHome
import kotlinx.coroutines.flow.Flow

interface GetRecipeRepository {

    suspend fun getRecipes(): RequestResult<List<RecipeHome>>
}