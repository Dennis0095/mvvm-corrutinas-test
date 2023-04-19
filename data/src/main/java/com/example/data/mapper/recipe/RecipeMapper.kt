package com.example.data.mapper.recipe

import com.example.data.model.DetailRecipeModel
import com.example.data.model.RecipeModel
import com.example.domain.model.DetailRecipe
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeHome
import javax.inject.Inject

class RecipeMapper @Inject constructor(){

    fun transformHome(input: List<RecipeModel>?) : List<RecipeHome>{
        val output : MutableList<RecipeHome> = ArrayList()
        if(input != null){
            for (entity in input){
                val recipe = RecipeHome()
                recipe.id = entity.id
                recipe.linkImage = entity.linkImage
                recipe.name = entity.name
                output.add(recipe)
            }
        }
        return output
    }

    fun transformDetail(recipeModel: RecipeModel) : Recipe{
        val recipe = Recipe()
        recipe.id = recipeModel.id
        recipe.linkImage = recipeModel.linkImage
        recipe.linkIcon = recipeModel.linkIcon
        recipe.name = recipeModel.name
        recipe.description = recipeModel.description
        recipe.latitude = recipeModel.latitude
        recipe.longitude = recipeModel.longitude
        return recipe
    }
    fun transformDetailRecipe(recipeModel: DetailRecipeModel) : DetailRecipe{
        val recipe = DetailRecipe(
            id = recipeModel.id,
            title = recipeModel.title,
            description = recipeModel.description,
            price = recipeModel.price,
        )
        return recipe
    }


}