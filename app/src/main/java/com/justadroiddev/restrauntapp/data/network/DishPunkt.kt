package com.justadroiddev.restrauntapp.data.network


import com.google.gson.annotations.SerializedName

data class DishPunkt(
    @SerializedName("dishCount")
    val dishCount: Int,
    @SerializedName("dishId")
    val dishId: Int,
    @SerializedName("usersNotes")
    val usersNotes: String
)