package com.example.data.model

import com.example.domain.model.DetailRecipe

data class DetailRecipeModel(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double
)

fun DetailRecipeModel.toEntity() = DetailRecipe(
    id = id,
    title = title,
    description = description,
    price = price,
)