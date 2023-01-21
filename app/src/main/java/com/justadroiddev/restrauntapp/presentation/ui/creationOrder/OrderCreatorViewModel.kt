package com.justadroiddev.restrauntapp.presentation.ui.creationOrder

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.justadroiddev.restrauntapp.domain.DishDomain

class OrderCreatorViewModel : ViewModel() {

    private val orderLiveData = MutableLiveData<List<DishDomain>?>()

    fun observeOrder(lifecycleOwner: LifecycleOwner, observer: Observer<List<DishDomain>?>){
        orderLiveData.observe(lifecycleOwner, observer)
    }

    fun removeDish(dishDomain: DishDomain) {
        OrderCreator.removeDish(dishDomain)
        updateListDishes()
    }

    fun updateListDishes() {
        orderLiveData.value = OrderCreator.takeDishes()
    }

    fun listDone(){
        orderLiveData.value = null
    }

}