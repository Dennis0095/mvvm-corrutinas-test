package com.example.data.local.db.datasource

import com.example.data.local.db.dao.RecipeDao
import com.example.data.local.db.entity.RecipeTbl
import com.example.data.model.RecipeModel
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeHome
import com.example.domain.repository.recipe.ISearchLocalDetilRecipe
import com.example.domain.repository.recipe.ISearchLocalHomeRecipe
import javax.inject.Inject

class RecipeRoom @Inject constructor(
    private val recipeDao: RecipeDao
    ) : ISearchLocalDetilRecipe, ISearchLocalHomeRecipe {


    override suspend fun searchLocalDetaileRecipe(id: Int): Recipe {
        val recipe = Recipe()
        val recipeTbl = recipeDao.getRecipeDetail(id)
        recipeTbl?.let { itRecipe ->
            recipe.id = itRecipe.id
            recipe.linkIcon = itRecipe.linkIcon
            recipe.linkImage = itRecipe.linkImage
            recipe.description = itRecipe.description
            recipe.name = itRecipe.name
            recipe.latitude = itRecipe.latitude
            recipe.longitude = itRecipe.longitude
        }

        return recipe
    }

    override suspend fun searchLocalHomeRecipe(word: String): List<RecipeHome> {
        val listRecipes = recipeDao.getSearchRecipeHome(word)
        val listOutput = ArrayList<RecipeHome>()
        for (entity in listRecipes){
            val recipe = RecipeHome()
            recipe.id = entity.id
            recipe.linkImage = entity.linkImage
            recipe.name = entity.name
            listOutput.add(recipe)
        }
        return listOutput
    }

    suspend fun saveRecipeLocal(list: List<RecipeModel>) {
        val output = ArrayList<RecipeTbl>()
        for (entity in list){
            var recipe = RecipeTbl (
                id = entity.id,
                name = entity.name,
                linkImage = entity.linkImage,
                linkIcon = entity.linkIcon,
                latitude = entity.latitude,
                longitude = entity.longitude,
                description = entity.description,
            )
            output.add(recipe)

        }
        recipeDao.setList(output)
    }

    suspend fun deleteRecipeLocal(){
        recipeDao.deleteRecipe()
    }

}