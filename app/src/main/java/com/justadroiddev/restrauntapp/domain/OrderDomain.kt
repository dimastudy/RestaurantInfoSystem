package com.justadroiddev.restrauntapp.domain

import com.google.gson.annotations.SerializedName
import com.justadroiddev.restrauntapp.data.network.DishPunkt
import com.justadroiddev.restrauntapp.data.network.OrderNetwork

data class OrderDomain(
    val address: String,
    val clientId: Int,
    val deliveryDate: String,
    val dishPunkts: List<DishPunkt>,
    val isDelivery: Boolean,
    val tableNumber: Int = 0
) {

    fun mapToOrderNetwork() = OrderNetwork(address, clientId, deliveryDate, dishPunkts, isDelivery, tableNumber)


}