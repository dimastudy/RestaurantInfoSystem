package com.justadroiddev.restrauntapp.data.network

import android.util.Log
import com.justadroiddev.restrauntapp.domain.OrderDomain
import com.justadroiddev.restrauntapp.domain.UserDomain
import javax.inject.Inject

class BaseCloudDataSource @Inject constructor(
    private val restaurantApi: RestaurantApi
) : CloudDataSource {


    override suspend fun fetchCategories(): List<CategoryNetwork> =
        restaurantApi.getCategoryList()


    override suspend fun fetchDishes(): List<DishNetwork> =
        restaurantApi.getMenuList()

    override suspend fun testCategory() {
        val data = restaurantApi.getMenuList()
        Log.e("DataSource",data.first().dishName)
    }

    override suspend fun registerNewUser(userDomain: UserDomain) {
        restaurantApi.registerNewUser(userDomain.mapToRequestRegBody())
    }

    override suspend fun loginToAccount(userDomain: UserDomain): LoginResponseBody =
        restaurantApi.loginIntoAccount(userDomain.mapToRequestLoginBody())

    override suspend fun makeAnOrder(orderDomain: OrderDomain) {
        restaurantApi.makeAnOrder(orderDomain.mapToOrderNetwork())
    }

    override suspend fun getAllOrdersOfClient(idClient: Int): List<OrdersResponseItem> =
        restaurantApi.getOrdersByClient(idClient)


}