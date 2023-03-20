package com.example.retotecnico.di

import com.example.data.repository.RecipeDataRepository
import com.example.domain.repository.recipe.GetRecipeRepository
import com.example.domain.repository.recipe.SearchRecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeModule {

    @Singleton
    @Provides
    fun providesGetRecipeRepository(recipeDataRepository: RecipeDataRepository) : GetRecipeRepository{
        return recipeDataRepository
    }

    @Singleton
    @Provides
    fun providesSearchRecipeRepository(recipeDataRepository: RecipeDataRepository) : SearchRecipeRepository{
        return recipeDataRepository
    }
}