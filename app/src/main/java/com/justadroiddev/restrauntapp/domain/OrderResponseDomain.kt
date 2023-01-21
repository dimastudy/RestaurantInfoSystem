package com.justadroiddev.restrauntapp.domain


import com.justadroiddev.restrauntapp.data.network.DishPunktResponse

data class OrderResponseDomain(
    val address: String,
    val clientId: Int,
    val createdDate: String,
    val deliveryDate: String,
    val dishes: List<DishDomain>,
    val id: Int,
    val isDelivery: Boolean,
    val status: OrderStatus,
    val tableNumber: Int
) {


}

enum class OrderStatus {
    Ordered,
    Making,
    Made,
    Delivered,
    Denied
}