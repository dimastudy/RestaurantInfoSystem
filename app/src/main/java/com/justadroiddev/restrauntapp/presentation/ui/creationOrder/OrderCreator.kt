package com.justadroiddev.restrauntapp.presentation.ui.creationOrder

import com.justadroiddev.restrauntapp.domain.DishDomain

object OrderCreator {

    private val listDishes = ArrayList<DishDomain>()

    fun addDish(dishDomain: DishDomain){
        var isDishAdded = false
        var dishCount = 0
        var dishIndex = 0
        var dishExisted: DishDomain? = null
        for (dish in listDishes){
            if (dish.id == dishDomain.id){
                isDishAdded = true
                dishCount = dish.portion
                dishExisted = dish
                dishIndex = listDishes.indexOf(dish)
            }
        }
        if (isDishAdded){
            listDishes.remove(dishExisted)
            val newDish = dishDomain.portionChange(dishCount + dishDomain.portion)
            newDish.noteToDish(dishDomain.takeNote())
            listDishes.add(dishIndex ,newDish)
        } else {
            listDishes.add(dishDomain)
        }
    }

    fun removeDish(dishDomain: DishDomain){
        listDishes.remove(dishDomain)
    }

    fun clearDishes() =
        listDishes.clear()


    fun takeDishes() = listDishes

    fun takeOrderPrice() : String {
        var price: Double = 0.0
        for (dish in listDishes){
            price += dish.pricePerPortion * dish.portion
        }
        return price.toString()
    }

}