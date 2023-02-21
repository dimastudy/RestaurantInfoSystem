package com.justadroiddev.restrauntapp.presentation.ui.creationOrder

import androidx.lifecycle.*
import com.justadroiddev.restrauntapp.domain.DishDomain
import com.justadroiddev.restrauntapp.domain.usecases.GetCachedDishesUseCase
import com.justadroiddev.restrauntapp.domain.usecases.RemoveCachedDishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderCreatorViewModel @Inject constructor(
    private val getCachedDishesUseCase: GetCachedDishesUseCase,
    private val removeCachedDishUseCase: RemoveCachedDishUseCase
) : ViewModel() {

    private val orderLiveData = MutableLiveData<List<DishDomain>?>()

    fun observeOrder(lifecycleOwner: LifecycleOwner, observer: Observer<List<DishDomain>?>){
        orderLiveData.observe(lifecycleOwner, observer)
    }

    fun removeDish(dishDomain: DishDomain) {
        viewModelScope.launch {
            val removeProcess =  async(Dispatchers.IO) {
                removeCachedDishUseCase.invoke(dishDomain)
            }
            removeProcess.await()
            updateListDishes()
        }

    }

    fun updateListDishes() {
        viewModelScope.launch {
            orderLiveData.value = getCachedDishesUseCase.invoke()
        }
    }

    fun listDone(){
        orderLiveData.value = null
    }

}