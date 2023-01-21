package com.justadroiddev.restrauntapp.presentation.ui.details

import androidx.lifecycle.*
import com.justadroiddev.restrauntapp.domain.DishDomain
import com.justadroiddev.restrauntapp.presentation.ui.creationOrder.OrderCreator

class DishDetailViewModel(
    private val dishDomain: DishDomain
) : ViewModel() {

    private val dishLiveData = MutableLiveData<DishDomain>()

    fun observeDish(lifecycleOwner: LifecycleOwner, observer: Observer<DishDomain>){
        dishLiveData.observe(lifecycleOwner, observer)
    }

    fun orderDish(count: Int, message: String){
        val dishOrdered = dishDomain.portionChange(count)
        dishOrdered.noteToDish(message)
        OrderCreator.addDish(dishOrdered)
    }

    init {
        dishLiveData.value = dishDomain
    }

    fun dishDataDone() {
        dishLiveData.value = null
    }

    class Factory(private val dishDomain: DishDomain) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DishDetailViewModel::class.java)){
                return DishDetailViewModel(dishDomain) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}