package com.justadroiddev.restrauntapp.data.network


import com.google.gson.annotations.SerializedName

data class RegistrationRequestBody(
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("password")
    val password: String
)