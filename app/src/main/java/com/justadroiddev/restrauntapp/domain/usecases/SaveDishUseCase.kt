package com.justadroiddev.restrauntapp.domain.usecases

import com.justadroiddev.restrauntapp.domain.DishDomain
import com.justadroiddev.restrauntapp.domain.Repository
import javax.inject.Inject

class SaveDishUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(dishDomain: DishDomain) = repository.saveDish(dishDomain)
}