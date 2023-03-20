package com.example.data.repository

import com.example.data.local.db.datasource.RecipeRoom
import com.example.data.mapper.recipe.RecipeMapper
import com.example.data.network.service.recipe.RecipeService
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeHome
import com.example.domain.repository.recipe.GetRecipeRepository
import com.example.domain.repository.recipe.SearchRecipeRepository
import javax.inject.Inject


class RecipeDataRepository @Inject constructor(
    private val api: RecipeService,
    private val mapper: RecipeMapper,
    private val recipeLocal: RecipeRoom
    ) : GetRecipeRepository, SearchRecipeRepository{

    override suspend fun getRecipes(): List<RecipeHome> {
        val response = api.getRecipes()
        recipeLocal.deleteRecipeLocal()//Eliminamos localmente la lista de recetas
        recipeLocal.saveRecipeLocal(response)//Guardamos localmente la lista de recetas
        return mapper.transformHome(response)
    }

    override suspend fun getRecipeId(idRecipe: Int): Recipe {
        return recipeLocal.searchLocalDetaileRecipe(idRecipe)
    }
}