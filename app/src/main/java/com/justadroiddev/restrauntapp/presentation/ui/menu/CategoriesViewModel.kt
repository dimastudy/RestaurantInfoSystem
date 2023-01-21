package com.justadroiddev.restrauntapp.presentation.ui.menu

import androidx.lifecycle.*
import com.justadroiddev.restrauntapp.domain.CategoryDomain
import com.justadroiddev.restrauntapp.domain.ResultDomain
import com.justadroiddev.restrauntapp.domain.usecases.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {


    private val categoriesLiveData = MutableLiveData<ResultDomain<List<CategoryDomain>>>()

    init {
        initList()
    }

    fun initList() = viewModelScope.launch() {
        val dataRequest = async(Dispatchers.IO) {
            getCategoriesUseCase.invoke()
        }
        val data = dataRequest.await()
        categoriesLiveData.value = data
    }

    fun observeCategories(lifecycleOwner: LifecycleOwner, observer: Observer<ResultDomain<List<CategoryDomain>>>) {
        categoriesLiveData.observe(lifecycleOwner, observer)
    }



}