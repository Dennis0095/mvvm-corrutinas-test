package com.example.data.network.service.recipe

import com.example.data.core.Constants.Companion.URI_service
import com.example.data.model.RecipeModel
import com.example.data.network.RestApi
import com.example.data.network.service.IRecipeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeService @Inject constructor(){

    private val restApi = RestApi.create(URI_service, IRecipeService::class.java)

    suspend fun getRecipes() : List<RecipeModel>{
        return withContext(Dispatchers.IO){
            val response = restApi.getRecipe()
            response.body()?.data?: emptyList()
        }
    }
}