package com.example.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.local.db.entity.RecipeTbl
import com.example.data.model.RecipeModel

@Dao
interface RecipeDao {

    @Insert
    suspend fun setList(listRecipe: List<RecipeTbl>)

    @Query("SELECT * FROM recipe r where r.name like '%' || :word || '%'")
    suspend fun getSearchRecipeHome(word: String) : List<RecipeTbl>


    @Query("SELECT * FROM recipe r where r.id =:idRecipe")
    suspend fun getRecipeDetail(idRecipe: Int) : RecipeTbl

    @Query("delete from recipe")
    suspend fun deleteRecipe()
}