package com.justadroiddev.restrauntapp.domain.usecases

import com.justadroiddev.restrauntapp.domain.Repository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.getCategories()
}