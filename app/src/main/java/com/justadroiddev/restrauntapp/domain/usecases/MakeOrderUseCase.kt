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
        if (!OrderCreator.takeDishes().isNullOrEmpty()){
            return repository.makeAnOrder(
                UserConsts.takeId()!!,
                address,
                deliveryDate,
                isDelivery,
                tableNumber,
                OrderCreator.takeDishes()
            )
        }
        return false
    }
}