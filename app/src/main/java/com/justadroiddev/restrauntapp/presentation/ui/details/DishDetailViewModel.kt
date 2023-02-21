package com.justadroiddev.restrauntapp.presentation.ui.details

import androidx.lifecycle.*
import com.justadroiddev.restrauntapp.domain.DishDomain
import com.justadroiddev.restrauntapp.domain.usecases.SaveDishUseCase
import com.justadroiddev.restrauntapp.presentation.ui.creationOrder.OrderCreator
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DishDetailViewModel @AssistedInject constructor(
    @Assisted private val dishDomain: DishDomain,
    private val saveDishUseCase: SaveDishUseCase
    ) : ViewModel() {

    private val dishLiveData = MutableLiveData<DishDomain>()

    fun observeDish(lifecycleOwner: LifecycleOwner, observer: Observer<DishDomain>){
        dishLiveData.observe(lifecycleOwner, observer)
    }

    fun orderDish(count: Int, message: String){
        val dishOrdered = dishDomain.portionChange(count)
        dishOrdered.noteToDish(message)
        viewModelScope.launch(Dispatchers.IO) {
            saveDishUseCase.invoke(dishOrdered)
        }
    }

    init {
        dishLiveData.value = dishDomain
    }

    fun dishDataDone() {
        dishLiveData.value = null
    }

    companion object {
        fun getFactory(
            dishDomain: DishDomain,
            factory: AssistedDishFactory
        ) : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(DishDetailViewModel::class.java)){
                    return factory.getViewModel(dishDomain) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedDishFactory {
        fun getViewModel(dishDomain: DishDomain) : DishDetailViewModel
    }

}