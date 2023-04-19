package com.example.domain.interactor.recipe

import com.example.domain.core.RequestResult
import com.example.domain.model.DetailRecipe
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeHome
import com.example.domain.repository.recipe.GetRecipeRepository
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(private val getRecipeRepository: GetRecipeRepository){

    suspend fun getRecipes(): RequestResult<List<RecipeHome>> {
        return getRecipeRepository.getRecipes()
    }

/*    fun getDetaileRecipe(): RequestResult<DetailRecipe> {
        return getRecipeRepository.getDetailRecipe()
    }*/


}