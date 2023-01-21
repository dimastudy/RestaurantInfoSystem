package com.justadroiddev.restrauntapp.presentation.ui.creationOrder

import androidx.lifecycle.*
import com.justadroiddev.restrauntapp.domain.usecases.MakeOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderSubmitViewModel @Inject constructor(
    private val makeOrderUseCase: MakeOrderUseCase
) : ViewModel() {

    private val resultLiveData = MutableLiveData<Boolean?>()

    fun doneResult() {
        resultLiveData.value = null
    }

    fun observeResult(lifecycleOwner: LifecycleOwner, observer: Observer<Boolean?>) {
        resultLiveData.observe(lifecycleOwner, observer)
    }

    fun makeOrder(
        address: String,
        deliveryDate: String,
        isDelivery: Boolean,
        tableNumber: Int
    ) = viewModelScope.launch {
        val requestOrder = async(Dispatchers.IO) {
            makeOrderUseCase.invoke(address, deliveryDate, isDelivery, tableNumber)
        }
        resultLiveData.value = requestOrder.await()
    }

    fun orderDone(){
        OrderCreator.clearDishes()
    }


}


