package com.justadroiddev.restrauntapp.domain.usecases

import com.justadroiddev.restrauntapp.domain.Repository
import javax.inject.Inject

class GetOrderPriceUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.getOrderPrice()
}