package com.justadroiddev.restrauntapp.domain.usecases

import com.justadroiddev.restrauntapp.domain.Repository
import javax.inject.Inject

class GetDishByCategoryUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(categoryId: Int) = repository.getDishesByCategory(categoryId)
}