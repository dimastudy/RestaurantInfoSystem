package com.justadroiddev.restrauntapp.data.database

import com.justadroiddev.restrauntapp.domain.DishDomain
import com.justadroiddev.restrauntapp.presentation.ui.creationOrder.OrderCreator
import javax.inject.Inject

class BaseCacheDataSource @Inject constructor(
    private val database: RestaurantDatabase
) : CacheDataSource{
    override suspend fun saveDish(dishDomain: DishDomain) {
        val listDishes = database.dao().getAllDishes().map { it.mapToDishDomain() }
        var isDishAdded = false
        var dishCount = 0
        for (dish in listDishes){
            if (dish.id == dishDomain.id){
                isDishAdded = true
                dishCount = dish.portion
            }
        }
        if (isDishAdded){
            val newDish = dishDomain.portionChange(dishCount + dishDomain.portion)
            newDish.noteToDish(dishDomain.takeNote())
            database.dao().insertDish(newDish.mapToDishEntity())
        } else {
            database.dao().insertDish(dishDomain.mapToDishEntity())
        }
    }

    override suspend fun removeDish(dishDomain: DishDomain) {
        database.dao().deleteDish(dishDomain.mapToDishEntity())
    }

    override suspend fun clear() {
        database.dao().clearDishes()
    }

    override suspend fun getDishes(): List<DishDomain> {
        return database.dao().getAllDishes().map {
            it.mapToDishDomain()
        }
    }

    override suspend fun takeOrderPrice(): Double {
        var price: Double = 0.0
        val dishes = database.dao().getAllDishes()
        for (dish in dishes){
            price = price.plus((dish.pricePerPortion * dish.portion))
        }
        return price
    }


}