package com.example.retotecnico.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.recipe.GetRecipeUseCase
import com.example.domain.interactor.recipe.SearchRecipeUseCase
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeEventResult
import com.example.retotecnico.core.launchOnIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceiptViewModel @Inject constructor(
    private val getRecipeUseCase: GetRecipeUseCase,
    private val searchRecipeUseCase: SearchRecipeUseCase
    ): ViewModel() {

    val recipeModel = MutableLiveData<RecipeEventResult>()
    val recipeModel_ : LiveData<RecipeEventResult> get() = recipeModel

    val searchRecipeViewModel = MutableLiveData<Recipe>()
    val searchRecipeTextViewModel = MutableLiveData<RecipeEventResult>()

    fun getRecipe(){
        launchOnIO(
            doTask = {
                getRecipeUseCase.getRecipes()
            },
            result = {
                recipeModel.postValue(RecipeEventResult.showListRecipe(it))
            },
            error = {
                recipeModel.postValue(RecipeEventResult.Error(it))
            }
        )

    }

/*    fun create(){
        viewModelScope.launch (Dispatchers.Main ){
            val load = LoadRecipeHomeTest()
            recipeModel.postValue(load.invoke())
        }
    }*/
    fun searchRecipeId(idRecipe: Int){
        viewModelScope.launch {
            val result = searchRecipeUseCase.getRecipeId(idRecipe)
            if(result.id != 0){
                searchRecipeViewModel.postValue(result)
            }
        }
    }

    fun searchRecipeText(word: String){
        viewModelScope.launch {
            val result = searchRecipeUseCase.getRecipeWord(word)
            if(result.isNotEmpty()){
                searchRecipeTextViewModel.postValue(RecipeEventResult.showListRecipe(result))
            }
        }
    }
}