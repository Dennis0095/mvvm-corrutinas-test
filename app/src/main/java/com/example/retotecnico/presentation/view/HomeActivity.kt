package com.example.retotecnico.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.model.RecipeEventResult
import com.example.domain.model.RecipeHome
import com.example.retotecnico.core.Constants.Companion.ID_RECIPE
import com.example.retotecnico.core.hideKey
import com.example.retotecnico.core.isConnected
import com.example.retotecnico.databinding.ActivityHomeBinding
import com.example.retotecnico.presentation.view.adapter.AdapterRecipe
import com.example.retotecnico.presentation.view.detail.DetailRecipeActivity
import com.example.retotecnico.presentation.viewmodel.ReceiptViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), AdapterRecipe.ClickListenerRecipe {

    private val recipeViewModel: ReceiptViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideKey(this)

        if (isConnected()) {
            recipeViewModel.getRecipe()

            recipeViewModel.recipeModel.observe(this) {
                when (it) {
                    is RecipeEventResult.showListRecipe -> {

                        val adapter = AdapterRecipe(it.data, this)
                        binding.adapterRecipe = adapter
                    }
                    is RecipeEventResult.Error -> {
                        Toast.makeText(this, " mensaje error", Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            Toast.makeText(this, "Verifique su conexión a internet, por favor.", Toast.LENGTH_LONG)
                .show()
        }

        recipeViewModel.searchRecipeTextViewModel.observe(this) { itEventResult ->
            when (itEventResult) {
                is RecipeEventResult.showListRecipe -> {
                    binding.adapterRecipe?.let { itAdapter ->
                        itAdapter.updateListRecipe(itEventResult.data)
                        itAdapter.notifyDataSetChanged()
                    }

                }
                is RecipeEventResult.Error -> {}
            }
        }

    }

    fun searchRecipe(view: View) {
        if (binding.etSearch.text.isNotEmpty()) {
            recipeViewModel.searchRecipeText(binding.etSearch.text.toString())
        }
    }

    override fun clickDetail(recipe: RecipeHome) {
        val intent = Intent(this, DetailRecipeActivity::class.java)
        intent.putExtra(ID_RECIPE, recipe.id)
        startActivity(intent)
    }
}