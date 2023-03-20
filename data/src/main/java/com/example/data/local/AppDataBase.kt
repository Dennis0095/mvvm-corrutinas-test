package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.db.dao.RecipeDao
import com.example.data.local.db.entity.RecipeTbl

@Database(
    version = 2,
    entities = [RecipeTbl::class]
)
abstract class AppDataBase : RoomDatabase() {

    companion object{
        const val APP_NAME_DATABASE = "RetoAppDatabase.db"
    }

    abstract fun recipeDao(): RecipeDao
}