package com.justadroiddev.restrauntapp.data.network


import com.google.gson.annotations.SerializedName
import com.justadroiddev.restrauntapp.domain.DishDomain

data class DishPunktResponse(
    @SerializedName("Dish")
    val dish: DishNetwork,
    @SerializedName("DishCount")
    val dishCount: Int,
    @SerializedName("DishId")
    val dishId: Int,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("OrderId")
    val orderId: Int,
    @SerializedName("UsersNotes")
    val usersNotes: String
) {

    fun mapToDishDomain() = DishDomain(
        dish.dishName,
        dish.id,
        dish.ingredients,
        dish.orderedCount,
        dishCount,
        dish.portionVariant,
        dish.pricePerPortion,
        dish.rating,
        null,
        dish.imageURL
    )

}