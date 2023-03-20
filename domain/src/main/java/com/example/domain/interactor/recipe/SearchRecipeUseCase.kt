package com.example.domain.interactor.recipe

import com.example.domain.model.Recipe
import com.example.domain.repository.recipe.SearchRecipeRepository
import javax.inject.Inject

class SearchRecipeUseCase @Inject constructor(private val searchRecipeRepository: SearchRecipeRepository){

    suspend fun getRecipeId(idRecipe: Int): Recipe {
        return searchRecipeRepository.getRecipeId(idRecipe)
    }
}