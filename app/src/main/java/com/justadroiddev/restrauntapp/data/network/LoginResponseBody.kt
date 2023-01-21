package com.justadroiddev.restrauntapp.data.network


import com.google.gson.annotations.SerializedName
import com.justadroiddev.restrauntapp.domain.UserDomain

data class LoginResponseBody(
    @SerializedName("Email")
    val email: String,
    @SerializedName("FirstName")
    val firstName: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("IsAdmin")
    val isAdmin: Boolean,
    @SerializedName("LastName")
    val lastName: String,
    @SerializedName("orders")
    val orders: List<Any>,
    @SerializedName("Password")
    val password: String
) {

    fun mapToUserDomain(): UserDomain =
        UserDomain(firstName, lastName, email, password).apply {
            setId(id)
        }

}