package com.justadroiddev.restrauntapp.data.network

import com.justadroiddev.restrauntapp.domain.OrderDomain
import com.justadroiddev.restrauntapp.domain.ResultDomain
import com.justadroiddev.restrauntapp.domain.UserDomain

interface CloudDataSource {

    suspend fun fetchCategories() : List<CategoryNetwork>

    suspend fun fetchDishes() : List<DishNetwork>

    suspend fun testCategory()

    suspend fun registerNewUser(userDomain: UserDomain)

    suspend fun loginToAccount(userDomain: UserDomain) : LoginResponseBody

    suspend fun makeAnOrder(orderDomain: OrderDomain)

    suspend fun getAllOrdersOfClient(idClient: Int) : List<OrdersResponseItem>

}