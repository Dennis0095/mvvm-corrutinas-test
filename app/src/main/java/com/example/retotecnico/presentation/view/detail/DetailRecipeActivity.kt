package com.example.retotecnico.presentation.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.request.RequestOptions
import com.example.domain.model.Recipe
import com.example.retotecnico.core.Constants
import com.example.retotecnico.core.ImageUtil
import com.example.retotecnico.databinding.ActivityDetailRecipeBinding
import com.example.retotecnico.presentation.view.maps.MapsActivity
import com.example.retotecnico.presentation.viewmodel.ReceiptViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailRecipeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailRecipeBinding
    private val viewModel : ReceiptViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idRecipe = intent.getIntExtra("ID_RECIPE", 0)
        getDetailRecipe(idRecipe)

    }

    private fun getDetailRecipe(idRecipe: Int){
        viewModel.searchRecipeId(idRecipe)
        viewModel.searchRecipeViewModel.observe(this) { recipe_ ->
            binding.recipe = recipe_
            loadImage(recipe_)
        }
    }

    private fun loadImage(recipe: Recipe){
        recipe.linkImage?.let {
            ImageUtil.load(
                binding.root.context, binding.ibRecipeDetail, it,
                android.R.color.transparent, android.R.color.transparent,
                RequestOptions().fitCenter()
            )
        }
    }

    fun viewMaps(view: View){
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra(Constants.LATITUDE, binding.recipe?.latitude)
        intent.putExtra(Constants.LONGITUDE, binding.recipe?.longitude)
        intent.putExtra(Constants.NAME, binding.recipe?.name)
        intent.putExtra(Constants.LINK_ICON, binding.recipe?.linkIcon)
        startActivity(intent)
    }
}