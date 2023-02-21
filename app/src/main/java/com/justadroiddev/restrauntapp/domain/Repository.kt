package com.justadroiddev.restrauntapp.domain

interface Repository : OrderRequest, AccountManager{

    suspend fun getDishesByCategory(categoryId: Int): ResultDomain<List<DishDomain>>

    suspend fun getCategories(): ResultDomain<List<CategoryDomain>>

    suspend fun getCachedDishes() : List<DishDomain>

    suspend fun saveDish(dishDomain: DishDomain)

    suspend fun removeDish(dishDomain: DishDomain)

    suspend fun clearDishes()



}

interface OrderRequest {

    suspend fun makeAnOrder(
        clientId: Int,
        address: String,
        deliveryDate: String,
        isDelivery: Boolean,
        tableNumber: Int,
        listDishes: List<DishDomain>
    ): Boolean

    suspend fun getOrdersByClient(idClient: Int) : ResultDomain<List<OrderResponseDomain>>

    suspend fun getOrderPrice() : Double

}

interface AccountManager {

    suspend fun registerNewUser(user: UserDomain): Boolean

    suspend fun loginToAccount(user: UserDomain): ResultDomain<UserDomain>

}