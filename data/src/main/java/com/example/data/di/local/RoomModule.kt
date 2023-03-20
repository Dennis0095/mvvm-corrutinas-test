package com.example.data.di.local

import android.content.Context
import androidx.room.Room
import com.example.data.local.AppDataBase
import com.example.data.local.db.dao.RecipeDao
import com.example.data.local.db.datasource.RecipeRoom
import com.example.domain.repository.recipe.ISearchLocalDetilRecipe
import com.example.domain.repository.recipe.ISearchLocalHomeRecipe
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        synchronized(AppDataBase::class) {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java, AppDataBase.APP_NAME_DATABASE
            ).fallbackToDestructiveMigration().build()
        }
    }

    @Singleton
    @Provides
    fun provideRecipeDetailRoom(recipeDao: RecipeDao) : ISearchLocalDetilRecipe{
        return RecipeRoom(recipeDao)
    }

    @Singleton
    @Provides
    fun provideRecipeHomeRoom(recipeDao: RecipeDao) : ISearchLocalHomeRecipe {
        return RecipeRoom(recipeDao)
    }

    @Singleton
    @Provides
    fun provideRecipeDao(db: AppDataBase): RecipeDao{
        return db.recipeDao()
    }


}