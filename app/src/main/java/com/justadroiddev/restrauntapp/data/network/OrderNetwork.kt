package com.justadroiddev.restrauntapp.data.network


import com.google.gson.annotations.SerializedName

data class OrderNetwork(
    @SerializedName("address")
    val address: String,
    @SerializedName("clientId")
    val clientId: Int,
    @SerializedName("deliveryDate")
    val deliveryDate: String,
    @SerializedName("dishPunkts")
    val dishPunkts: List<DishPunkt>,
    @SerializedName("isDelivery")
    val isDelivery: Boolean,
    @SerializedName("tableNumber")
    val tableNumber: Int = 0
)