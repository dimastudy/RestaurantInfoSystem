package com.justadroiddev.restrauntapp.data.network


import com.google.gson.annotations.SerializedName
import com.justadroiddev.restrauntapp.domain.DishDomain

data class DishNetwork(
    @SerializedName("Category")
    val category: CategoryNetwork,
    @SerializedName("CategoryId")
    val categoryId: Int,
    @SerializedName("DishName")
    val dishName: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("ImageURL")
    val imageURL: String,
    @SerializedName("Ingredients")
    val ingredients: String,
    @SerializedName("OrderedCount")
    val orderedCount: Int,
    @SerializedName("Portion")
    val portion: Int,
    @SerializedName("PortionVariant")
    val portionVariant: Int,
    @SerializedName("PricePerPortion")
    val pricePerPortion: Double,
    @SerializedName("Rating")
    val rating: Int
) {

    fun mapToDishDomain() : DishDomain =
        DishDomain(dishName, id, ingredients, orderedCount, portion, portionVariant, pricePerPortion, rating, category.mapToCategoryDomain(), imageURL)

}