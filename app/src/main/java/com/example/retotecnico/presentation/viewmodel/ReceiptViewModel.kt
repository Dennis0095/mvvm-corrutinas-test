package com.example.retotecnico.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.recipe.GetRecipeUseCase
import com.example.domain.interactor.recipe.SearchRecipeUseCase
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeHome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceiptViewModel @Inject constructor(
    private val getRecipeUseCase: GetRecipeUseCase,
    private val searchRecipeUseCase: SearchRecipeUseCase
    ): ViewModel() {

    val recipeModel = MutableLiveData<List<RecipeHome>>()
    val searchRecipeViewModel = MutableLiveData<Recipe>()

    fun getRecipe(){
        viewModelScope.launch {
            val result = getRecipeUseCase.getRecipes()
            if(!result.isNullOrEmpty()){
                recipeModel.postValue(result)
            }
        }
    }

    fun searchRecipe(idRecipe: Int){
        viewModelScope.launch {
            val result = searchRecipeUseCase.getRecipeId(idRecipe)
            if(result != null){
                searchRecipeViewModel.postValue(result)
            }
        }
    }


}