package com.justadroiddev.restrauntapp.presentation.ui.creationOrder

import androidx.lifecycle.*
import com.justadroiddev.restrauntapp.domain.usecases.ClearCachedDishesUseCase
import com.justadroiddev.restrauntapp.domain.usecases.GetOrderPriceUseCase
import com.justadroiddev.restrauntapp.domain.usecases.MakeOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderSubmitViewModel @Inject constructor(
    private val makeOrderUseCase: MakeOrderUseCase,
    private val clearCachedDishesUseCase: ClearCachedDishesUseCase,
    private val getOrderPriceUseCase: GetOrderPriceUseCase
) : ViewModel() {

    private val resultLiveData = MutableLiveData<Boolean?>()
    private val priceLiveData = MutableLiveData<Double?>()

    fun doneResult() {
        resultLiveData.value = null
        priceLiveData.value = null
    }

    init {
        viewModelScope.launch {
            priceLiveData.value = getOrderPriceUseCase.invoke()
        }
    }

    fun observeResult(lifecycleOwner: LifecycleOwner, observer: Observer<Boolean?>) {
        resultLiveData.observe(lifecycleOwner, observer)
    }

    fun observePrice(lifecycleOwner: LifecycleOwner, observer: Observer<Double?>){
        priceLiveData.observe(lifecycleOwner, observer)
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
        viewModelScope.launch(Dispatchers.IO) {
            clearCachedDishesUseCase.invoke()
        }
    }


}


