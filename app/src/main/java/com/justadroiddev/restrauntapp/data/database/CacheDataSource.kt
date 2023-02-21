package com.justadroiddev.restrauntapp.data.database

import com.justadroiddev.restrauntapp.domain.DishDomain

interface CacheDataSource {

    suspend fun saveDish(dishDomain: DishDomain)

    suspend fun removeDish(dishDomain: DishDomain)

    suspend fun clear()

    suspend fun getDishes() : List<DishDomain>

    suspend fun takeOrderPrice() : Double

}