package com.justadroiddev.restrauntapp.domain

import com.justadroiddev.restrauntapp.data.network.LoginRequestBody
import com.justadroiddev.restrauntapp.data.network.RegistrationRequestBody

data class UserDomain(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
) {
    private var id: Int = -1
    fun setId(idNumber: Int){
        id = idNumber
    }
    fun takeId() = if (id != -1) id else null

    private var listOrders = ArrayList<OrderDomain>()

    fun mapToRequestRegBody() : RegistrationRequestBody = RegistrationRequestBody(email, firstName, lastName, password)

    fun mapToRequestLoginBody() : LoginRequestBody = LoginRequestBody(email, password)

}