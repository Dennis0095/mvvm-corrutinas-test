package com.example.data.network.service

import com.example.data.model.RecipeModel
import com.example.data.model.ResponseRecipeModel
import retrofit2.Response
import retrofit2.http.GET

interface IRecipeService {

    @GET("recipes")
    suspend fun getRecipe(): Response<ResponseRecipeModel>
}