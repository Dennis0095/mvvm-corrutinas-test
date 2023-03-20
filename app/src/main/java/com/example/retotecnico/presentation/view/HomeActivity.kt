package com.example.retotecnico.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.domain.model.RecipeHome
import com.example.retotecnico.core.Constants.Companion.ID_RECIPE
import com.example.retotecnico.databinding.ActivityHomeBinding
import com.example.retotecnico.presentation.view.adapter.AdapterRecipe
import com.example.retotecnico.presentation.viewmodel.ReceiptViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() , AdapterRecipe.ClickListenerRecipe {

    private val recipeViewModel : ReceiptViewModel by viewModels()
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeViewModel.getRecipe()

        recipeViewModel.recipeModel.observe(this) { itList ->
            //Log.d("DMA_LECTOR", " quote = " + it.)
            //Log.d("DMA_LECTOR", " author = " + it.author)

            for (entity in itList) {
                Log.d("DMA_LECTOR", " name = " + entity.name)
                Log.d("DMA_LECTOR", " id = " + entity.id)
                Log.d("DMA_LECTOR", " linkImage = " + entity.linkImage)
            }

            val adapter: AdapterRecipe = AdapterRecipe(itList, this)
            binding.adapterRecipe = adapter
            binding.rvRecipes.adapter = adapter
            //recipeViewModel.searchRecipe(1)
        }


        recipeViewModel.searchRecipeViewModel.observe(this) {
            Log.d("DMA_LECTOR", " name = " + it.name)
            Log.d("DMA_LECTOR", " id = " + it.id)
            Log.d("DMA_LECTOR", " linkImage = " + it.linkImage)
        }


    }

    override fun clickDetail(recipe: RecipeHome) {
        /*val intent = Intent(this, DetailRecipeActivity::class.java)
        intent.putExtra(ID_RECIPE, recipe.id)
        startActivity(intent)*/
    }
}