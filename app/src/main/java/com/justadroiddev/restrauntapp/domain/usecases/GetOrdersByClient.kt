package com.justadroiddev.restrauntapp.domain.usecases

import com.justadroiddev.restrauntapp.domain.OrderResponseDomain
import com.justadroiddev.restrauntapp.domain.Repository
import com.justadroiddev.restrauntapp.domain.ResultDomain
import com.justadroiddev.restrauntapp.domain.UserConsts
import javax.inject.Inject

class GetOrdersByClient @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() : ResultDomain<List<OrderResponseDomain>> {
        return repository.getOrdersByClient(UserConsts.takeId()!!)
    }
}