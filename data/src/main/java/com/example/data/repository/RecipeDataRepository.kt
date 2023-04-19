package com.example.data.repository

import com.example.data.local.db.datasource.RecipeRoom
import com.example.data.mapper.recipe.RecipeMapper
import com.example.data.network.service.recipe.RecipeService
import com.example.domain.core.RequestResult
import com.example.domain.model.DetailRecipe
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeHome
import com.example.domain.repository.recipe.GetRecipeRepository
import com.example.domain.repository.recipe.SearchRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RecipeDataRepository @Inject constructor(
    private val api: RecipeService,
    private val mapper: RecipeMapper,
    private val recipeLocal: RecipeRoom
    ) : GetRecipeRepository, SearchRecipeRepository{

    override suspend fun getRecipes(): RequestResult<List<RecipeHome>> {
        val response = api.getRecipes()
        recipeLocal.deleteRecipeLocal()//Eliminamos localmente la lista de recetas
        when(response){
            is RequestResult.Success -> {
                recipeLocal.saveRecipeLocal(response.value)//Guardamos localmente la lista de recetas
                return RequestResult.Success(mapper.transformHome(response.value))
            }
            is RequestResult.Error -> {
                return RequestResult.Error(response.exception)
            }
        }

    }



    override suspend fun getRecipeId(idRecipe: Int): Recipe {
        return recipeLocal.searchLocalDetaileRecipe(idRecipe)
    }

    override suspend fun getRecipeWord(word: String): List<RecipeHome> {
        return recipeLocal.searchLocalHomeRecipe(word)
    }
}