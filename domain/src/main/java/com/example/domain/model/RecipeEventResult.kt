package com.example.domain.model

import java.lang.Exception

sealed class RecipeEventResult {
    data class showListRecipe(val data :List<RecipeHome>): RecipeEventResult()
    data class Error(val error: Exception): RecipeEventResult()
}