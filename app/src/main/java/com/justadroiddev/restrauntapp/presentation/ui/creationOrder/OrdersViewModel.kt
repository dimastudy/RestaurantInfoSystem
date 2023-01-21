package com.justadroiddev.restrauntapp.presentation.ui.creationOrder

import androidx.lifecycle.*
import com.justadroiddev.restrauntapp.domain.OrderResponseDomain
import com.justadroiddev.restrauntapp.domain.ResultDomain
import com.justadroiddev.restrauntapp.domain.usecases.GetOrdersByClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val getOrdersByClient: GetOrdersByClient
) : ViewModel() {

    private val ordersLiveData = MutableLiveData<ResultDomain<List<OrderResponseDomain>>>()


    fun observeOrders(lifecycleOwner: LifecycleOwner, observer: Observer<ResultDomain<List<OrderResponseDomain>>>){
        ordersLiveData.observe(lifecycleOwner, observer)
    }

    init {
        initOrders()
    }

    fun initOrders() = viewModelScope.launch {
        val requestOrders = async(Dispatchers.IO) {
            getOrdersByClient.invoke()
        }
        ordersLiveData.value = requestOrders.await()
    }

}