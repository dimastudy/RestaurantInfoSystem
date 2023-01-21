package com.justadroiddev.restrauntapp.domain

interface Repository {

    suspend fun getDishesByCategory(categoryId: Int): ResultDomain<List<DishDomain>>

    suspend fun getCategories(): ResultDomain<List<CategoryDomain>>

    suspend fun registerNewUser(user: UserDomain): Boolean

    suspend fun loginToAccount(user: UserDomain): ResultDomain<UserDomain>

    suspend fun makeAnOrder(
        clientId: Int,
        address: String,
        deliveryDate: String,
        isDelivery: Boolean,
        tableNumber: Int,
        listDishes: List<DishDomain>
    ): Boolean

    suspend fun getOrdersByClient(idClient: Int) : ResultDomain<List<OrderResponseDomain>>

}