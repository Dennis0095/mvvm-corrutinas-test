package com.example.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.RecipeModel
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeHome

@Entity(tableName = "recipe")
data class RecipeTbl(

    @PrimaryKey()
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "linkImage")
    var linkImage: String,

    @ColumnInfo(name = "linkIcon")
    var linkIcon: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "latitude")
    var latitude: String,

    @ColumnInfo(name = "longitude")
    var longitude: String,
)

fun RecipeModel.toRoom() = RecipeTbl(
    id = id,
    linkImage = linkImage,
    linkIcon = linkIcon,
    name = name,
    description = description,
    latitude = latitude,
    longitude = longitude
)

