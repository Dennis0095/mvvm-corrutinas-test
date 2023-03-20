package com.example.domain.repository.recipe

import com.example.domain.model.Recipe

interface ISearchLocalDetilRecipe {
    suspend fun searchLocalDetaileRecipe(id: Int) : Recipe
}