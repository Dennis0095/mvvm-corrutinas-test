package com.example.retotecnico.presentation.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.example.domain.model.RecipeHome
import com.example.retotecnico.core.ImageUtil
import com.example.retotecnico.databinding.LayoutItemRecipeBinding


class AdapterRecipe (
    val dataModelList: List<RecipeHome>,
    val listener: ClickListenerRecipe
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutItemRecipeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CardRecipeViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val subcategory = dataModelList.get(position)
        val viewHolder = holder as CardRecipeViewHolder
        viewHolder.bind(subcategory)
    }

    override fun getItemCount(): Int {
        return dataModelList.size
    }

    class CardRecipeViewHolder(
        private val binding: LayoutItemRecipeBinding,
        private val listener_: ClickListenerRecipe?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeHome) {
            binding.itemClickListener = listener_
            binding.recipe = recipe
            recipe.linkImage?.let {
                ImageUtil.load(
                    binding.root.context, binding.ibRecipe, it,
                    android.R.color.transparent, android.R.color.transparent,
                    RequestOptions().fitCenter()
                )
            }
            Log.d("DMA_LECTOR", " name = " + recipe.name)
        }
    }

    interface ClickListenerRecipe {
        fun clickDetail(recipe: RecipeHome)
    }
}