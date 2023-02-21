package com.justadroiddev.restrauntapp.domain.usecases

import com.justadroiddev.restrauntapp.domain.Repository
import com.justadroiddev.restrauntapp.domain.UserConsts
import com.justadroiddev.restrauntapp.presentation.ui.creationOrder.OrderCreator
import javax.inject.Inject

class MakeOrderUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(
        address: String,
        deliveryDate: String,
        isDelivery: Boolean,
        tableNumber: Int
    ): Boolean {
        val orderDishes = repository.getCachedDishes()
        if (orderDishes.isNotEmpty()){
            return repository.makeAnOrder(
                UserConsts.takeId()!!,
                address,
                deliveryDate,
                isDelivery,
                tableNumber,
                orderDishes
            )
        }
        return false
    }
}