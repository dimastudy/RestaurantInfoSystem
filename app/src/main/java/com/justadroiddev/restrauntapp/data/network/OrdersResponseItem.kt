package com.justadroiddev.restrauntapp.data.network


import com.google.gson.annotations.SerializedName
import com.justadroiddev.restrauntapp.data.network.DishPunktResponse
import com.justadroiddev.restrauntapp.domain.OrderResponseDomain
import com.justadroiddev.restrauntapp.domain.OrderStatus

data class OrdersResponseItem(
    @SerializedName("Address")
    val address: String,
    @SerializedName("ClientId")
    val clientId: Int,
    @SerializedName("CreatedDate")
    val createdDate: String,
    @SerializedName("DeliveryDate")
    val deliveryDate: String,
    @SerializedName("dishPunkts")
    val dishPunkts: List<DishPunktResponse>,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("IsDelivery")
    val isDelivery: Boolean,
    @SerializedName("Status")
    val status: Int,
    @SerializedName("TableNumber")
    val tableNumber: Int
) {

    fun mapToOrderResponseDomain() : OrderResponseDomain {

        val statusOrder = when(status){
            1 -> OrderStatus.Ordered
            2 -> OrderStatus.Making
            3 -> OrderStatus.Made
            4 -> OrderStatus.Delivered
            5 -> OrderStatus.Denied
            else -> OrderStatus.Ordered
        }
        return OrderResponseDomain(
            address,
            clientId,
            createdDate,
            deliveryDate,
            dishPunkts.map {
                it.mapToDishDomain()
            },
            id,
            isDelivery,
            statusOrder,
            tableNumber
        )

    }

}