package com.justadroiddev.restrauntapp.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.justadroiddev.restrauntapp.domain.CategoryDomain
import com.justadroiddev.restrauntapp.domain.DishDomain

@Entity(tableName = "cached_dishes")
data class DishEntity(
    val dishName: String,
    @PrimaryKey val idDish: Int,
    val ingredients: String,
    val orderedCount: Int,
    val portion: Int,
    val portionVariant: Int,
    val pricePerPortion: Double,
    val rating: Int,
    @Embedded val category: CategoryDomain? = null,
    val dishImage: String
) {

    fun mapToDishDomain() : DishDomain = DishDomain(dishName, idDish, ingredients, orderedCount, portion, portionVariant, pricePerPortion, rating, category, dishImage)

}